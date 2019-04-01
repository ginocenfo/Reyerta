import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Aic } from 'app/shared/model/aic.model';
import { AicService } from './aic.service';
import { AicComponent } from './aic.component';
import { AicDetailComponent } from './aic-detail.component';
import { AicUpdateComponent } from './aic-update.component';
import { AicDeletePopupComponent } from './aic-delete-dialog.component';
import { IAic } from 'app/shared/model/aic.model';

@Injectable({ providedIn: 'root' })
export class AicResolve implements Resolve<IAic> {
    constructor(private service: AicService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAic> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Aic>) => response.ok),
                map((aic: HttpResponse<Aic>) => aic.body)
            );
        }
        return of(new Aic());
    }
}

export const aicRoute: Routes = [
    {
        path: '',
        component: AicComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Aics'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AicDetailComponent,
        resolve: {
            aic: AicResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Aics'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AicUpdateComponent,
        resolve: {
            aic: AicResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Aics'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AicUpdateComponent,
        resolve: {
            aic: AicResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Aics'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const aicPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AicDeletePopupComponent,
        resolve: {
            aic: AicResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Aics'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
