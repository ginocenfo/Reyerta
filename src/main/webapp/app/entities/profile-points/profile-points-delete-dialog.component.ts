import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProfilePoints } from 'app/shared/model/profile-points.model';
import { ProfilePointsService } from './profile-points.service';

@Component({
    selector: 'jhi-profile-points-delete-dialog',
    templateUrl: './profile-points-delete-dialog.component.html'
})
export class ProfilePointsDeleteDialogComponent {
    profilePoints: IProfilePoints;

    constructor(
        protected profilePointsService: ProfilePointsService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.profilePointsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'profilePointsListModification',
                content: 'Deleted an profilePoints'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-profile-points-delete-popup',
    template: ''
})
export class ProfilePointsDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ profilePoints }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProfilePointsDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.profilePoints = profilePoints;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/profile-points', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/profile-points', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
