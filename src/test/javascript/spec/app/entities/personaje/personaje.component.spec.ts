/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ReyertaTestModule } from '../../../test.module';
import { PersonajeComponent } from 'app/entities/personaje/personaje.component';
import { PersonajeService } from 'app/entities/personaje/personaje.service';
import { Personaje } from 'app/shared/model/personaje.model';

describe('Component Tests', () => {
    describe('Personaje Management Component', () => {
        let comp: PersonajeComponent;
        let fixture: ComponentFixture<PersonajeComponent>;
        let service: PersonajeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [PersonajeComponent],
                providers: []
            })
                .overrideTemplate(PersonajeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PersonajeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PersonajeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Personaje(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.personajes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
