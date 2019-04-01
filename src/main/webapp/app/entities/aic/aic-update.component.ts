import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IAic } from 'app/shared/model/aic.model';
import { AicService } from './aic.service';

@Component({
    selector: 'jhi-aic-update',
    templateUrl: './aic-update.component.html'
})
export class AicUpdateComponent implements OnInit {
    aic: IAic;
    isSaving: boolean;

    constructor(protected aicService: AicService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ aic }) => {
            this.aic = aic;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.aic.id !== undefined) {
            this.subscribeToSaveResponse(this.aicService.update(this.aic));
        } else {
            this.subscribeToSaveResponse(this.aicService.create(this.aic));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAic>>) {
        result.subscribe((res: HttpResponse<IAic>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
