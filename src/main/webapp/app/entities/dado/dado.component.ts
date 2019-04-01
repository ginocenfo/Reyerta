import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDado } from 'app/shared/model/dado.model';
import { AccountService } from 'app/core';
import { DadoService } from './dado.service';

@Component({
    selector: 'jhi-dado',
    templateUrl: './dado.component.html'
})
export class DadoComponent implements OnInit, OnDestroy {
    dados: IDado[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected dadoService: DadoService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.dadoService
            .query()
            .pipe(
                filter((res: HttpResponse<IDado[]>) => res.ok),
                map((res: HttpResponse<IDado[]>) => res.body)
            )
            .subscribe(
                (res: IDado[]) => {
                    this.dados = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInDados();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDado) {
        return item.id;
    }

    registerChangeInDados() {
        this.eventSubscriber = this.eventManager.subscribe('dadoListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
