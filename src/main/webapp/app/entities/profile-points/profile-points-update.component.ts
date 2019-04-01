import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IProfilePoints } from 'app/shared/model/profile-points.model';
import { ProfilePointsService } from './profile-points.service';

@Component({
    selector: 'jhi-profile-points-update',
    templateUrl: './profile-points-update.component.html'
})
export class ProfilePointsUpdateComponent implements OnInit {
    profilePoints: IProfilePoints;
    isSaving: boolean;

    constructor(protected profilePointsService: ProfilePointsService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ profilePoints }) => {
            this.profilePoints = profilePoints;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.profilePoints.id !== undefined) {
            this.subscribeToSaveResponse(this.profilePointsService.update(this.profilePoints));
        } else {
            this.subscribeToSaveResponse(this.profilePointsService.create(this.profilePoints));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IProfilePoints>>) {
        result.subscribe((res: HttpResponse<IProfilePoints>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
