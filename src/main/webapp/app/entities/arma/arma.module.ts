import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReyertaSharedModule } from 'app/shared';
import {
    ArmaComponent,
    ArmaDetailComponent,
    ArmaUpdateComponent,
    ArmaDeletePopupComponent,
    ArmaDeleteDialogComponent,
    armaRoute,
    armaPopupRoute
} from './';

const ENTITY_STATES = [...armaRoute, ...armaPopupRoute];

@NgModule({
    imports: [ReyertaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [ArmaComponent, ArmaDetailComponent, ArmaUpdateComponent, ArmaDeleteDialogComponent, ArmaDeletePopupComponent],
    entryComponents: [ArmaComponent, ArmaUpdateComponent, ArmaDeleteDialogComponent, ArmaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ReyertaArmaModule {}
