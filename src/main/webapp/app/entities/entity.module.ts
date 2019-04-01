import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'dado',
                loadChildren: './dado/dado.module#ReyertaDadoModule'
            },
            {
                path: 'aic',
                loadChildren: './aic/aic.module#ReyertaAicModule'
            },
            {
                path: 'arma',
                loadChildren: './arma/arma.module#ReyertaArmaModule'
            },
            {
                path: 'armadura',
                loadChildren: './armadura/armadura.module#ReyertaArmaduraModule'
            },
            {
                path: 'personaje',
                loadChildren: './personaje/personaje.module#ReyertaPersonajeModule'
            },
            {
                path: 'profile-points',
                loadChildren: './profile-points/profile-points.module#ReyertaProfilePointsModule'
            },
            {
                path: 'skill',
                loadChildren: './skill/skill.module#ReyertaSkillModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ReyertaEntityModule {}
