import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPersonaje } from 'app/shared/model/personaje.model';

@Component({
    selector: 'jhi-personaje-detail',
    templateUrl: './personaje-detail.component.html'
})
export class PersonajeDetailComponent implements OnInit {
    personaje: IPersonaje;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ personaje }) => {
            this.personaje = personaje;
        });
    }

    previousState() {
        window.history.back();
    }
}
