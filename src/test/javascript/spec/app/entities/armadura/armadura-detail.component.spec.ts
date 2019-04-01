/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReyertaTestModule } from '../../../test.module';
import { ArmaduraDetailComponent } from 'app/entities/armadura/armadura-detail.component';
import { Armadura } from 'app/shared/model/armadura.model';

describe('Component Tests', () => {
    describe('Armadura Management Detail Component', () => {
        let comp: ArmaduraDetailComponent;
        let fixture: ComponentFixture<ArmaduraDetailComponent>;
        const route = ({ data: of({ armadura: new Armadura(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [ArmaduraDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ArmaduraDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ArmaduraDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.armadura).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
