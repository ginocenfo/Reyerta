import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReyertaSharedModule } from 'app/shared';
import {
    DadoComponent,
    DadoDetailComponent,
    DadoUpdateComponent,
    DadoDeletePopupComponent,
    DadoDeleteDialogComponent,
    dadoRoute,
    dadoPopupRoute
} from './';

const ENTITY_STATES = [...dadoRoute, ...dadoPopupRoute];

@NgModule({
    imports: [ReyertaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [DadoComponent, DadoDetailComponent, DadoUpdateComponent, DadoDeleteDialogComponent, DadoDeletePopupComponent],
    entryComponents: [DadoComponent, DadoUpdateComponent, DadoDeleteDialogComponent, DadoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ReyertaDadoModule {}
