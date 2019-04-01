/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReyertaTestModule } from '../../../test.module';
import { ArmaDetailComponent } from 'app/entities/arma/arma-detail.component';
import { Arma } from 'app/shared/model/arma.model';

describe('Component Tests', () => {
    describe('Arma Management Detail Component', () => {
        let comp: ArmaDetailComponent;
        let fixture: ComponentFixture<ArmaDetailComponent>;
        const route = ({ data: of({ arma: new Arma(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [ArmaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ArmaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ArmaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.arma).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
