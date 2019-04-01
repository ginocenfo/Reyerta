/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ReyertaTestModule } from '../../../test.module';
import { DadoComponent } from 'app/entities/dado/dado.component';
import { DadoService } from 'app/entities/dado/dado.service';
import { Dado } from 'app/shared/model/dado.model';

describe('Component Tests', () => {
    describe('Dado Management Component', () => {
        let comp: DadoComponent;
        let fixture: ComponentFixture<DadoComponent>;
        let service: DadoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [DadoComponent],
                providers: []
            })
                .overrideTemplate(DadoComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DadoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DadoService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Dado(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.dados[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
