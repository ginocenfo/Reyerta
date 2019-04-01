import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAic } from 'app/shared/model/aic.model';
import { AicService } from './aic.service';

@Component({
    selector: 'jhi-aic-delete-dialog',
    templateUrl: './aic-delete-dialog.component.html'
})
export class AicDeleteDialogComponent {
    aic: IAic;

    constructor(protected aicService: AicService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.aicService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'aicListModification',
                content: 'Deleted an aic'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-aic-delete-popup',
    template: ''
})
export class AicDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ aic }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AicDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.aic = aic;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/aic', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/aic', { outlets: { popup: null } }]);
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
