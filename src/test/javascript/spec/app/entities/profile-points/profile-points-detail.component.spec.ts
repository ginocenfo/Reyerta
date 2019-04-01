/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReyertaTestModule } from '../../../test.module';
import { ProfilePointsDetailComponent } from 'app/entities/profile-points/profile-points-detail.component';
import { ProfilePoints } from 'app/shared/model/profile-points.model';

describe('Component Tests', () => {
    describe('ProfilePoints Management Detail Component', () => {
        let comp: ProfilePointsDetailComponent;
        let fixture: ComponentFixture<ProfilePointsDetailComponent>;
        const route = ({ data: of({ profilePoints: new ProfilePoints(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [ProfilePointsDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ProfilePointsDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProfilePointsDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.profilePoints).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
