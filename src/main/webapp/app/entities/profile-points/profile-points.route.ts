import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ProfilePoints } from 'app/shared/model/profile-points.model';
import { ProfilePointsService } from './profile-points.service';
import { ProfilePointsComponent } from './profile-points.component';
import { ProfilePointsDetailComponent } from './profile-points-detail.component';
import { ProfilePointsUpdateComponent } from './profile-points-update.component';
import { ProfilePointsDeletePopupComponent } from './profile-points-delete-dialog.component';
import { IProfilePoints } from 'app/shared/model/profile-points.model';

@Injectable({ providedIn: 'root' })
export class ProfilePointsResolve implements Resolve<IProfilePoints> {
    constructor(private service: ProfilePointsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IProfilePoints> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ProfilePoints>) => response.ok),
                map((profilePoints: HttpResponse<ProfilePoints>) => profilePoints.body)
            );
        }
        return of(new ProfilePoints());
    }
}

export const profilePointsRoute: Routes = [
    {
        path: '',
        component: ProfilePointsComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProfilePoints'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ProfilePointsDetailComponent,
        resolve: {
            profilePoints: ProfilePointsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProfilePoints'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ProfilePointsUpdateComponent,
        resolve: {
            profilePoints: ProfilePointsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProfilePoints'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ProfilePointsUpdateComponent,
        resolve: {
            profilePoints: ProfilePointsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProfilePoints'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const profilePointsPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ProfilePointsDeletePopupComponent,
        resolve: {
            profilePoints: ProfilePointsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProfilePoints'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
