/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ReyertaTestModule } from '../../../test.module';
import { ArmaduraDeleteDialogComponent } from 'app/entities/armadura/armadura-delete-dialog.component';
import { ArmaduraService } from 'app/entities/armadura/armadura.service';

describe('Component Tests', () => {
    describe('Armadura Management Delete Component', () => {
        let comp: ArmaduraDeleteDialogComponent;
        let fixture: ComponentFixture<ArmaduraDeleteDialogComponent>;
        let service: ArmaduraService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [ArmaduraDeleteDialogComponent]
            })
                .overrideTemplate(ArmaduraDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ArmaduraDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ArmaduraService);
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
