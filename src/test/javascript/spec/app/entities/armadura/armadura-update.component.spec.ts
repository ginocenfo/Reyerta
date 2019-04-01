/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ReyertaTestModule } from '../../../test.module';
import { ArmaduraUpdateComponent } from 'app/entities/armadura/armadura-update.component';
import { ArmaduraService } from 'app/entities/armadura/armadura.service';
import { Armadura } from 'app/shared/model/armadura.model';

describe('Component Tests', () => {
    describe('Armadura Management Update Component', () => {
        let comp: ArmaduraUpdateComponent;
        let fixture: ComponentFixture<ArmaduraUpdateComponent>;
        let service: ArmaduraService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [ArmaduraUpdateComponent]
            })
                .overrideTemplate(ArmaduraUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ArmaduraUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ArmaduraService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Armadura(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.armadura = entity;
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
                    const entity = new Armadura();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.armadura = entity;
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
