import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IArma } from 'app/shared/model/arma.model';

@Component({
    selector: 'jhi-arma-detail',
    templateUrl: './arma-detail.component.html'
})
export class ArmaDetailComponent implements OnInit {
    arma: IArma;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ arma }) => {
            this.arma = arma;
        });
    }

    previousState() {
        window.history.back();
    }
}
