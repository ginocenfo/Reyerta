import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProfilePoints } from 'app/shared/model/profile-points.model';

@Component({
    selector: 'jhi-profile-points-detail',
    templateUrl: './profile-points-detail.component.html'
})
export class ProfilePointsDetailComponent implements OnInit {
    profilePoints: IProfilePoints;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ profilePoints }) => {
            this.profilePoints = profilePoints;
        });
    }

    previousState() {
        window.history.back();
    }
}
