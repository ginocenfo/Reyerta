import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IArmadura } from 'app/shared/model/armadura.model';
import { AccountService } from 'app/core';
import { ArmaduraService } from './armadura.service';

@Component({
    selector: 'jhi-armadura',
    templateUrl: './armadura.component.html'
})
export class ArmaduraComponent implements OnInit, OnDestroy {
    armaduras: IArmadura[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected armaduraService: ArmaduraService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.armaduraService
            .query()
            .pipe(
                filter((res: HttpResponse<IArmadura[]>) => res.ok),
                map((res: HttpResponse<IArmadura[]>) => res.body)
            )
            .subscribe(
                (res: IArmadura[]) => {
                    this.armaduras = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInArmaduras();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IArmadura) {
        return item.id;
    }

    registerChangeInArmaduras() {
        this.eventSubscriber = this.eventManager.subscribe('armaduraListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
