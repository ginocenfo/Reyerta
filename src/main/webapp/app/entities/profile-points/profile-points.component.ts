import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IProfilePoints } from 'app/shared/model/profile-points.model';
import { AccountService } from 'app/core';
import { ProfilePointsService } from './profile-points.service';

@Component({
    selector: 'jhi-profile-points',
    templateUrl: './profile-points.component.html'
})
export class ProfilePointsComponent implements OnInit, OnDestroy {
    profilePoints: IProfilePoints[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected profilePointsService: ProfilePointsService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.profilePointsService
            .query()
            .pipe(
                filter((res: HttpResponse<IProfilePoints[]>) => res.ok),
                map((res: HttpResponse<IProfilePoints[]>) => res.body)
            )
            .subscribe(
                (res: IProfilePoints[]) => {
                    this.profilePoints = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInProfilePoints();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IProfilePoints) {
        return item.id;
    }

    registerChangeInProfilePoints() {
        this.eventSubscriber = this.eventManager.subscribe('profilePointsListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
