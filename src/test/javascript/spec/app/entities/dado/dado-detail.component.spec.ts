/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReyertaTestModule } from '../../../test.module';
import { DadoDetailComponent } from 'app/entities/dado/dado-detail.component';
import { Dado } from 'app/shared/model/dado.model';

describe('Component Tests', () => {
    describe('Dado Management Detail Component', () => {
        let comp: DadoDetailComponent;
        let fixture: ComponentFixture<DadoDetailComponent>;
        const route = ({ data: of({ dado: new Dado(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [DadoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DadoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DadoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.dado).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
