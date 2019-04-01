import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReyertaSharedModule } from 'app/shared';
import {
    PersonajeComponent,
    PersonajeDetailComponent,
    PersonajeUpdateComponent,
    PersonajeDeletePopupComponent,
    PersonajeDeleteDialogComponent,
    personajeRoute,
    personajePopupRoute
} from './';

const ENTITY_STATES = [...personajeRoute, ...personajePopupRoute];

@NgModule({
    imports: [ReyertaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PersonajeComponent,
        PersonajeDetailComponent,
        PersonajeUpdateComponent,
        PersonajeDeleteDialogComponent,
        PersonajeDeletePopupComponent
    ],
    entryComponents: [PersonajeComponent, PersonajeUpdateComponent, PersonajeDeleteDialogComponent, PersonajeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ReyertaPersonajeModule {}
