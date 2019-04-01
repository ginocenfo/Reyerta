/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ReyertaTestModule } from '../../../test.module';
import { ProfilePointsUpdateComponent } from 'app/entities/profile-points/profile-points-update.component';
import { ProfilePointsService } from 'app/entities/profile-points/profile-points.service';
import { ProfilePoints } from 'app/shared/model/profile-points.model';

describe('Component Tests', () => {
    describe('ProfilePoints Management Update Component', () => {
        let comp: ProfilePointsUpdateComponent;
        let fixture: ComponentFixture<ProfilePointsUpdateComponent>;
        let service: ProfilePointsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [ProfilePointsUpdateComponent]
            })
                .overrideTemplate(ProfilePointsUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProfilePointsUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProfilePointsService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ProfilePoints(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.profilePoints = entity;
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
                    const entity = new ProfilePoints();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.profilePoints = entity;
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
