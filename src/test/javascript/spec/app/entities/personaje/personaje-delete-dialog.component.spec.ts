/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ReyertaTestModule } from '../../../test.module';
import { PersonajeDeleteDialogComponent } from 'app/entities/personaje/personaje-delete-dialog.component';
import { PersonajeService } from 'app/entities/personaje/personaje.service';

describe('Component Tests', () => {
    describe('Personaje Management Delete Component', () => {
        let comp: PersonajeDeleteDialogComponent;
        let fixture: ComponentFixture<PersonajeDeleteDialogComponent>;
        let service: PersonajeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [PersonajeDeleteDialogComponent]
            })
                .overrideTemplate(PersonajeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PersonajeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PersonajeService);
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
