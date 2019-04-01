import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Armadura } from 'app/shared/model/armadura.model';
import { ArmaduraService } from './armadura.service';
import { ArmaduraComponent } from './armadura.component';
import { ArmaduraDetailComponent } from './armadura-detail.component';
import { ArmaduraUpdateComponent } from './armadura-update.component';
import { ArmaduraDeletePopupComponent } from './armadura-delete-dialog.component';
import { IArmadura } from 'app/shared/model/armadura.model';

@Injectable({ providedIn: 'root' })
export class ArmaduraResolve implements Resolve<IArmadura> {
    constructor(private service: ArmaduraService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IArmadura> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Armadura>) => response.ok),
                map((armadura: HttpResponse<Armadura>) => armadura.body)
            );
        }
        return of(new Armadura());
    }
}

export const armaduraRoute: Routes = [
    {
        path: '',
        component: ArmaduraComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Armaduras'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ArmaduraDetailComponent,
        resolve: {
            armadura: ArmaduraResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Armaduras'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ArmaduraUpdateComponent,
        resolve: {
            armadura: ArmaduraResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Armaduras'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ArmaduraUpdateComponent,
        resolve: {
            armadura: ArmaduraResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Armaduras'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const armaduraPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ArmaduraDeletePopupComponent,
        resolve: {
            armadura: ArmaduraResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Armaduras'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
