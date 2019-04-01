import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Arma } from 'app/shared/model/arma.model';
import { ArmaService } from './arma.service';
import { ArmaComponent } from './arma.component';
import { ArmaDetailComponent } from './arma-detail.component';
import { ArmaUpdateComponent } from './arma-update.component';
import { ArmaDeletePopupComponent } from './arma-delete-dialog.component';
import { IArma } from 'app/shared/model/arma.model';

@Injectable({ providedIn: 'root' })
export class ArmaResolve implements Resolve<IArma> {
    constructor(private service: ArmaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IArma> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Arma>) => response.ok),
                map((arma: HttpResponse<Arma>) => arma.body)
            );
        }
        return of(new Arma());
    }
}

export const armaRoute: Routes = [
    {
        path: '',
        component: ArmaComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Armas'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ArmaDetailComponent,
        resolve: {
            arma: ArmaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Armas'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ArmaUpdateComponent,
        resolve: {
            arma: ArmaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Armas'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ArmaUpdateComponent,
        resolve: {
            arma: ArmaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Armas'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const armaPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ArmaDeletePopupComponent,
        resolve: {
            arma: ArmaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Armas'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
