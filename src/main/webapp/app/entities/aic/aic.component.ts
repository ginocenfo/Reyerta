import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAic } from 'app/shared/model/aic.model';
import { AccountService } from 'app/core';
import { AicService } from './aic.service';

@Component({
    selector: 'jhi-aic',
    templateUrl: './aic.component.html'
})
export class AicComponent implements OnInit, OnDestroy {
    aics: IAic[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected aicService: AicService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.aicService
            .query()
            .pipe(
                filter((res: HttpResponse<IAic[]>) => res.ok),
                map((res: HttpResponse<IAic[]>) => res.body)
            )
            .subscribe(
                (res: IAic[]) => {
                    this.aics = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAics();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAic) {
        return item.id;
    }

    registerChangeInAics() {
        this.eventSubscriber = this.eventManager.subscribe('aicListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
