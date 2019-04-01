import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAic } from 'app/shared/model/aic.model';

@Component({
    selector: 'jhi-aic-detail',
    templateUrl: './aic-detail.component.html'
})
export class AicDetailComponent implements OnInit {
    aic: IAic;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ aic }) => {
            this.aic = aic;
        });
    }

    previousState() {
        window.history.back();
    }
}
