import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReyertaSharedModule } from 'app/shared';
import {
    AicComponent,
    AicDetailComponent,
    AicUpdateComponent,
    AicDeletePopupComponent,
    AicDeleteDialogComponent,
    aicRoute,
    aicPopupRoute
} from './';

const ENTITY_STATES = [...aicRoute, ...aicPopupRoute];

@NgModule({
    imports: [ReyertaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [AicComponent, AicDetailComponent, AicUpdateComponent, AicDeleteDialogComponent, AicDeletePopupComponent],
    entryComponents: [AicComponent, AicUpdateComponent, AicDeleteDialogComponent, AicDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ReyertaAicModule {}
