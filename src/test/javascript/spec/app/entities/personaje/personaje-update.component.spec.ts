/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ReyertaTestModule } from '../../../test.module';
import { PersonajeUpdateComponent } from 'app/entities/personaje/personaje-update.component';
import { PersonajeService } from 'app/entities/personaje/personaje.service';
import { Personaje } from 'app/shared/model/personaje.model';

describe('Component Tests', () => {
    describe('Personaje Management Update Component', () => {
        let comp: PersonajeUpdateComponent;
        let fixture: ComponentFixture<PersonajeUpdateComponent>;
        let service: PersonajeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [PersonajeUpdateComponent]
            })
                .overrideTemplate(PersonajeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PersonajeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PersonajeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Personaje(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.personaje = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Personaje();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.personaje = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
