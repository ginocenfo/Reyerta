import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IArmadura } from 'app/shared/model/armadura.model';
import { ArmaduraService } from './armadura.service';
import { IDado } from 'app/shared/model/dado.model';
import { DadoService } from 'app/entities/dado';
import { IPersonaje } from 'app/shared/model/personaje.model';
import { PersonajeService } from 'app/entities/personaje';

@Component({
    selector: 'jhi-armadura-update',
    templateUrl: './armadura-update.component.html'
})
export class ArmaduraUpdateComponent implements OnInit {
    armadura: IArmadura;
    isSaving: boolean;

    damagebyshields: IDado[];

    personajes: IPersonaje[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected armaduraService: ArmaduraService,
        protected dadoService: DadoService,
        protected personajeService: PersonajeService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ armadura }) => {
            this.armadura = armadura;
        });
        this.dadoService
            .query({ filter: 'armadura-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IDado[]>) => mayBeOk.ok),
                map((response: HttpResponse<IDado[]>) => response.body)
            )
            .subscribe(
                (res: IDado[]) => {
                    if (!this.armadura.damageByShield || !this.armadura.damageByShield.id) {
                        this.damagebyshields = res;
                    } else {
                        this.dadoService
                            .find(this.armadura.damageByShield.id)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IDado>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IDado>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IDado) => (this.damagebyshields = [subRes].concat(res)),
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
        if (this.armadura.id !== undefined) {
            this.subscribeToSaveResponse(this.armaduraService.update(this.armadura));
        } else {
            this.subscribeToSaveResponse(this.armaduraService.create(this.armadura));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IArmadura>>) {
        result.subscribe((res: HttpResponse<IArmadura>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackPersonajeById(index: number, item: IPersonaje) {
        return item.id;
    }
}
