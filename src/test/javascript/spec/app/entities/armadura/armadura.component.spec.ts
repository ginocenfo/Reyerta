/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ReyertaTestModule } from '../../../test.module';
import { ArmaduraComponent } from 'app/entities/armadura/armadura.component';
import { ArmaduraService } from 'app/entities/armadura/armadura.service';
import { Armadura } from 'app/shared/model/armadura.model';

describe('Component Tests', () => {
    describe('Armadura Management Component', () => {
        let comp: ArmaduraComponent;
        let fixture: ComponentFixture<ArmaduraComponent>;
        let service: ArmaduraService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [ArmaduraComponent],
                providers: []
            })
                .overrideTemplate(ArmaduraComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ArmaduraComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ArmaduraService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Armadura(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.armaduras[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
