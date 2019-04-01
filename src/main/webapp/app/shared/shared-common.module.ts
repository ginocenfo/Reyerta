import { NgModule } from '@angular/core';

import { ReyertaSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [ReyertaSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [ReyertaSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class ReyertaSharedCommonModule {}
