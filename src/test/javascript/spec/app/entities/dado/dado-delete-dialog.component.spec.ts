/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ReyertaTestModule } from '../../../test.module';
import { DadoDeleteDialogComponent } from 'app/entities/dado/dado-delete-dialog.component';
import { DadoService } from 'app/entities/dado/dado.service';

describe('Component Tests', () => {
    describe('Dado Management Delete Component', () => {
        let comp: DadoDeleteDialogComponent;
        let fixture: ComponentFixture<DadoDeleteDialogComponent>;
        let service: DadoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [DadoDeleteDialogComponent]
            })
                .overrideTemplate(DadoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DadoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DadoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
