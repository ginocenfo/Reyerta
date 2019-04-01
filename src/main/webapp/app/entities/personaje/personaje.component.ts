import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPersonaje } from 'app/shared/model/personaje.model';
import { AccountService } from 'app/core';
import { PersonajeService } from './personaje.service';

@Component({
    selector: 'jhi-personaje',
    templateUrl: './personaje.component.html'
})
export class PersonajeComponent implements OnInit, OnDestroy {
    personajes: IPersonaje[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected personajeService: PersonajeService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.personajeService
            .query()
            .pipe(
                filter((res: HttpResponse<IPersonaje[]>) => res.ok),
                map((res: HttpResponse<IPersonaje[]>) => res.body)
            )
            .subscribe(
                (res: IPersonaje[]) => {
                    this.personajes = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPersonajes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPersonaje) {
        return item.id;
    }

    registerChangeInPersonajes() {
        this.eventSubscriber = this.eventManager.subscribe('personajeListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
