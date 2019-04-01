import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IArmadura } from 'app/shared/model/armadura.model';
import { ArmaduraService } from './armadura.service';

@Component({
    selector: 'jhi-armadura-delete-dialog',
    templateUrl: './armadura-delete-dialog.component.html'
})
export class ArmaduraDeleteDialogComponent {
    armadura: IArmadura;

    constructor(protected armaduraService: ArmaduraService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.armaduraService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'armaduraListModification',
                content: 'Deleted an armadura'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-armadura-delete-popup',
    template: ''
})
export class ArmaduraDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ armadura }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ArmaduraDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.armadura = armadura;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/armadura', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/armadura', { outlets: { popup: null } }]);
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
