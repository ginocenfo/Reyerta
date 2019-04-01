import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReyertaSharedModule } from 'app/shared';
import {
    ArmaduraComponent,
    ArmaduraDetailComponent,
    ArmaduraUpdateComponent,
    ArmaduraDeletePopupComponent,
    ArmaduraDeleteDialogComponent,
    armaduraRoute,
    armaduraPopupRoute
} from './';

const ENTITY_STATES = [...armaduraRoute, ...armaduraPopupRoute];

@NgModule({
    imports: [ReyertaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ArmaduraComponent,
        ArmaduraDetailComponent,
        ArmaduraUpdateComponent,
        ArmaduraDeleteDialogComponent,
        ArmaduraDeletePopupComponent
    ],
    entryComponents: [ArmaduraComponent, ArmaduraUpdateComponent, ArmaduraDeleteDialogComponent, ArmaduraDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ReyertaArmaduraModule {}
