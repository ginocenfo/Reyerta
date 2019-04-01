/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ReyertaTestModule } from '../../../test.module';
import { AicUpdateComponent } from 'app/entities/aic/aic-update.component';
import { AicService } from 'app/entities/aic/aic.service';
import { Aic } from 'app/shared/model/aic.model';

describe('Component Tests', () => {
    describe('Aic Management Update Component', () => {
        let comp: AicUpdateComponent;
        let fixture: ComponentFixture<AicUpdateComponent>;
        let service: AicService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [AicUpdateComponent]
            })
                .overrideTemplate(AicUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AicUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AicService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Aic(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.aic = entity;
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
                    const entity = new Aic();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.aic = entity;
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
