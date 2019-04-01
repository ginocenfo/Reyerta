import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IArma } from 'app/shared/model/arma.model';
import { ArmaService } from './arma.service';
import { IDado } from 'app/shared/model/dado.model';
import { DadoService } from 'app/entities/dado';
import { IAic } from 'app/shared/model/aic.model';
import { AicService } from 'app/entities/aic';
import { IPersonaje } from 'app/shared/model/personaje.model';
import { PersonajeService } from 'app/entities/personaje';

@Component({
    selector: 'jhi-arma-update',
    templateUrl: './arma-update.component.html'
})
export class ArmaUpdateComponent implements OnInit {
    arma: IArma;
    isSaving: boolean;

    dados: IDado[];

    crits: IAic[];

    personajes: IPersonaje[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected armaService: ArmaService,
        protected dadoService: DadoService,
        protected aicService: AicService,
        protected personajeService: PersonajeService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ arma }) => {
            this.arma = arma;
        });
        this.dadoService
            .query({ filter: 'arma-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IDado[]>) => mayBeOk.ok),
                map((response: HttpResponse<IDado[]>) => response.body)
            )
            .subscribe(
                (res: IDado[]) => {
                    if (!this.arma.dado || !this.arma.dado.id) {
                        this.dados = res;
                    } else {
                        this.dadoService
                            .find(this.arma.dado.id)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IDado>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IDado>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IDado) => (this.dados = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.aicService
            .query({ filter: 'arma-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IAic[]>) => mayBeOk.ok),
                map((response: HttpResponse<IAic[]>) => response.body)
            )
            .subscribe(
                (res: IAic[]) => {
                    if (!this.arma.crit || !this.arma.crit.id) {
                        this.crits = res;
                    } else {
                        this.aicService
                            .find(this.arma.crit.id)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IAic>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IAic>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IAic) => (this.crits = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.personajeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IPersonaje[]>) => mayBeOk.ok),
                map((response: HttpResponse<IPersonaje[]>) => response.body)
            )
            .subscribe((res: IPersonaje[]) => (this.personajes = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.arma.id !== undefined) {
            this.subscribeToSaveResponse(this.armaService.update(this.arma));
        } else {
            this.subscribeToSaveResponse(this.armaService.create(this.arma));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IArma>>) {
        result.subscribe((res: HttpResponse<IArma>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackDadoById(index: number, item: IDado) {
        return item.id;
    }

    trackAicById(index: number, item: IAic) {
        return item.id;
    }

    trackPersonajeById(index: number, item: IPersonaje) {
        return item.id;
    }
}
