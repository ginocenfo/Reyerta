/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ReyertaTestModule } from '../../../test.module';
import { DadoUpdateComponent } from 'app/entities/dado/dado-update.component';
import { DadoService } from 'app/entities/dado/dado.service';
import { Dado } from 'app/shared/model/dado.model';

describe('Component Tests', () => {
    describe('Dado Management Update Component', () => {
        let comp: DadoUpdateComponent;
        let fixture: ComponentFixture<DadoUpdateComponent>;
        let service: DadoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [DadoUpdateComponent]
            })
                .overrideTemplate(DadoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DadoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DadoService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Dado(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.dado = entity;
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
                    const entity = new Dado();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.dado = entity;
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
