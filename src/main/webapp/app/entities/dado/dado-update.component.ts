import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IDado } from 'app/shared/model/dado.model';
import { DadoService } from './dado.service';

@Component({
    selector: 'jhi-dado-update',
    templateUrl: './dado-update.component.html'
})
export class DadoUpdateComponent implements OnInit {
    dado: IDado;
    isSaving: boolean;

    constructor(protected dadoService: DadoService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ dado }) => {
            this.dado = dado;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.dado.id !== undefined) {
            this.subscribeToSaveResponse(this.dadoService.update(this.dado));
        } else {
            this.subscribeToSaveResponse(this.dadoService.create(this.dado));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDado>>) {
        result.subscribe((res: HttpResponse<IDado>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
