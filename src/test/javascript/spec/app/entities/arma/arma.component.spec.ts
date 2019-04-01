/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ReyertaTestModule } from '../../../test.module';
import { ArmaComponent } from 'app/entities/arma/arma.component';
import { ArmaService } from 'app/entities/arma/arma.service';
import { Arma } from 'app/shared/model/arma.model';

describe('Component Tests', () => {
    describe('Arma Management Component', () => {
        let comp: ArmaComponent;
        let fixture: ComponentFixture<ArmaComponent>;
        let service: ArmaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [ArmaComponent],
                providers: []
            })
                .overrideTemplate(ArmaComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ArmaComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ArmaService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Arma(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.armas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
