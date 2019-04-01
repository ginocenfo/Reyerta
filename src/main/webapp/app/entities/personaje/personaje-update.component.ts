import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IPersonaje } from 'app/shared/model/personaje.model';
import { PersonajeService } from './personaje.service';

@Component({
    selector: 'jhi-personaje-update',
    templateUrl: './personaje-update.component.html'
})
export class PersonajeUpdateComponent implements OnInit {
    personaje: IPersonaje;
    isSaving: boolean;

    constructor(protected personajeService: PersonajeService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ personaje }) => {
            this.personaje = personaje;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.personaje.id !== undefined) {
            this.subscribeToSaveResponse(this.personajeService.update(this.personaje));
        } else {
            this.subscribeToSaveResponse(this.personajeService.create(this.personaje));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPersonaje>>) {
        result.subscribe((res: HttpResponse<IPersonaje>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
