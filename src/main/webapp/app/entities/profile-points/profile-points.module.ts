import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReyertaSharedModule } from 'app/shared';
import {
    ProfilePointsComponent,
    ProfilePointsDetailComponent,
    ProfilePointsUpdateComponent,
    ProfilePointsDeletePopupComponent,
    ProfilePointsDeleteDialogComponent,
    profilePointsRoute,
    profilePointsPopupRoute
} from './';

const ENTITY_STATES = [...profilePointsRoute, ...profilePointsPopupRoute];

@NgModule({
    imports: [ReyertaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProfilePointsComponent,
        ProfilePointsDetailComponent,
        ProfilePointsUpdateComponent,
        ProfilePointsDeleteDialogComponent,
        ProfilePointsDeletePopupComponent
    ],
    entryComponents: [
        ProfilePointsComponent,
        ProfilePointsUpdateComponent,
        ProfilePointsDeleteDialogComponent,
        ProfilePointsDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ReyertaProfilePointsModule {}
