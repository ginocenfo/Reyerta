import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDado } from 'app/shared/model/dado.model';

@Component({
    selector: 'jhi-dado-detail',
    templateUrl: './dado-detail.component.html'
})
export class DadoDetailComponent implements OnInit {
    dado: IDado;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dado }) => {
            this.dado = dado;
        });
    }

    previousState() {
        window.history.back();
    }
}
