/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ReyertaTestModule } from '../../../test.module';
import { ArmaUpdateComponent } from 'app/entities/arma/arma-update.component';
import { ArmaService } from 'app/entities/arma/arma.service';
import { Arma } from 'app/shared/model/arma.model';

describe('Component Tests', () => {
    describe('Arma Management Update Component', () => {
        let comp: ArmaUpdateComponent;
        let fixture: ComponentFixture<ArmaUpdateComponent>;
        let service: ArmaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [ArmaUpdateComponent]
            })
                .overrideTemplate(ArmaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ArmaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ArmaService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Arma(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.arma = entity;
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
                    const entity = new Arma();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.arma = entity;
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
