import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Dado } from 'app/shared/model/dado.model';
import { DadoService } from './dado.service';
import { DadoComponent } from './dado.component';
import { DadoDetailComponent } from './dado-detail.component';
import { DadoUpdateComponent } from './dado-update.component';
import { DadoDeletePopupComponent } from './dado-delete-dialog.component';
import { IDado } from 'app/shared/model/dado.model';

@Injectable({ providedIn: 'root' })
export class DadoResolve implements Resolve<IDado> {
    constructor(private service: DadoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IDado> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Dado>) => response.ok),
                map((dado: HttpResponse<Dado>) => dado.body)
            );
        }
        return of(new Dado());
    }
}

export const dadoRoute: Routes = [
    {
        path: '',
        component: DadoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Dados'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: DadoDetailComponent,
        resolve: {
            dado: DadoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Dados'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: DadoUpdateComponent,
        resolve: {
            dado: DadoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Dados'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: DadoUpdateComponent,
        resolve: {
            dado: DadoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Dados'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const dadoPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: DadoDeletePopupComponent,
        resolve: {
            dado: DadoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Dados'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
