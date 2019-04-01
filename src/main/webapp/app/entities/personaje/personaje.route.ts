import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Personaje } from 'app/shared/model/personaje.model';
import { PersonajeService } from './personaje.service';
import { PersonajeComponent } from './personaje.component';
import { PersonajeDetailComponent } from './personaje-detail.component';
import { PersonajeUpdateComponent } from './personaje-update.component';
import { PersonajeDeletePopupComponent } from './personaje-delete-dialog.component';
import { IPersonaje } from 'app/shared/model/personaje.model';

@Injectable({ providedIn: 'root' })
export class PersonajeResolve implements Resolve<IPersonaje> {
    constructor(private service: PersonajeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPersonaje> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Personaje>) => response.ok),
                map((personaje: HttpResponse<Personaje>) => personaje.body)
            );
        }
        return of(new Personaje());
    }
}

export const personajeRoute: Routes = [
    {
        path: '',
        component: PersonajeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Personajes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: PersonajeDetailComponent,
        resolve: {
            personaje: PersonajeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Personajes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: PersonajeUpdateComponent,
        resolve: {
            personaje: PersonajeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Personajes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: PersonajeUpdateComponent,
        resolve: {
            personaje: PersonajeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Personajes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const personajePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: PersonajeDeletePopupComponent,
        resolve: {
            personaje: PersonajeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Personajes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
