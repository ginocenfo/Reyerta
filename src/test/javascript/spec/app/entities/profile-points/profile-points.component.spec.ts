/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ReyertaTestModule } from '../../../test.module';
import { ProfilePointsComponent } from 'app/entities/profile-points/profile-points.component';
import { ProfilePointsService } from 'app/entities/profile-points/profile-points.service';
import { ProfilePoints } from 'app/shared/model/profile-points.model';

describe('Component Tests', () => {
    describe('ProfilePoints Management Component', () => {
        let comp: ProfilePointsComponent;
        let fixture: ComponentFixture<ProfilePointsComponent>;
        let service: ProfilePointsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [ProfilePointsComponent],
                providers: []
            })
                .overrideTemplate(ProfilePointsComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProfilePointsComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProfilePointsService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ProfilePoints(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.profilePoints[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
