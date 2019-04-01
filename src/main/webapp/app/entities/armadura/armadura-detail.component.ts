import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IArmadura } from 'app/shared/model/armadura.model';

@Component({
    selector: 'jhi-armadura-detail',
    templateUrl: './armadura-detail.component.html'
})
export class ArmaduraDetailComponent implements OnInit {
    armadura: IArmadura;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ armadura }) => {
            this.armadura = armadura;
        });
    }

    previousState() {
        window.history.back();
    }
}
