/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReyertaTestModule } from '../../../test.module';
import { PersonajeDetailComponent } from 'app/entities/personaje/personaje-detail.component';
import { Personaje } from 'app/shared/model/personaje.model';

describe('Component Tests', () => {
    describe('Personaje Management Detail Component', () => {
        let comp: PersonajeDetailComponent;
        let fixture: ComponentFixture<PersonajeDetailComponent>;
        const route = ({ data: of({ personaje: new Personaje(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [PersonajeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PersonajeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PersonajeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.personaje).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
