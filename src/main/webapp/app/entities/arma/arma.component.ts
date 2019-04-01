import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IArma } from 'app/shared/model/arma.model';
import { AccountService } from 'app/core';
import { ArmaService } from './arma.service';

@Component({
    selector: 'jhi-arma',
    templateUrl: './arma.component.html'
})
export class ArmaComponent implements OnInit, OnDestroy {
    armas: IArma[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected armaService: ArmaService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.armaService
            .query()
            .pipe(
                filter((res: HttpResponse<IArma[]>) => res.ok),
                map((res: HttpResponse<IArma[]>) => res.body)
            )
            .subscribe(
                (res: IArma[]) => {
                    this.armas = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInArmas();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IArma) {
        return item.id;
    }

    registerChangeInArmas() {
        this.eventSubscriber = this.eventManager.subscribe('armaListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
