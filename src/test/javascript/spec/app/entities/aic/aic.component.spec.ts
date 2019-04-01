/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ReyertaTestModule } from '../../../test.module';
import { AicComponent } from 'app/entities/aic/aic.component';
import { AicService } from 'app/entities/aic/aic.service';
import { Aic } from 'app/shared/model/aic.model';

describe('Component Tests', () => {
    describe('Aic Management Component', () => {
        let comp: AicComponent;
        let fixture: ComponentFixture<AicComponent>;
        let service: AicService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [AicComponent],
                providers: []
            })
                .overrideTemplate(AicComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AicComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AicService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Aic(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.aics[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
