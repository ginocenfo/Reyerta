/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ReyertaTestModule } from '../../../test.module';
import { ProfilePointsDeleteDialogComponent } from 'app/entities/profile-points/profile-points-delete-dialog.component';
import { ProfilePointsService } from 'app/entities/profile-points/profile-points.service';

describe('Component Tests', () => {
    describe('ProfilePoints Management Delete Component', () => {
        let comp: ProfilePointsDeleteDialogComponent;
        let fixture: ComponentFixture<ProfilePointsDeleteDialogComponent>;
        let service: ProfilePointsService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReyertaTestModule],
                declarations: [ProfilePointsDeleteDialogComponent]
            })
                .overrideTemplate(ProfilePointsDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProfilePointsDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProfilePointsService);
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
