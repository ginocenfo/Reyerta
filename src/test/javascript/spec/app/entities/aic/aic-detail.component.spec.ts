/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReyertaTestModule } from '../../../test.module';
import { AicDetailComponent } from 'app/entities/aic/aic-detail.component';
import { Aic } from 'app/shared/model/aic.model';

describe('Component Tests', () => {
    describe('Aic Management Detail Component', () => {
        let comp: AicDetailComponent;
        let fixture: ComponentFixture<AicDetailComponent>;
        const route = ({ data: of({ aic: new Aic(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [AicDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AicDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AicDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.aic).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
