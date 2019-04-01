import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IArma } from 'app/shared/model/arma.model';
import { ArmaService } from './arma.service';

@Component({
    selector: 'jhi-arma-delete-dialog',
    templateUrl: './arma-delete-dialog.component.html'
})
export class ArmaDeleteDialogComponent {
    arma: IArma;

    constructor(protected armaService: ArmaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.armaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'armaListModification',
                content: 'Deleted an arma'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-arma-delete-popup',
    template: ''
})
export class ArmaDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ arma }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ArmaDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.arma = arma;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/arma', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/arma', { outlets: { popup: null } }]);
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
