import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDado } from 'app/shared/model/dado.model';
import { DadoService } from './dado.service';

@Component({
    selector: 'jhi-dado-delete-dialog',
    templateUrl: './dado-delete-dialog.component.html'
})
export class DadoDeleteDialogComponent {
    dado: IDado;

    constructor(protected dadoService: DadoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.dadoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'dadoListModification',
                content: 'Deleted an dado'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-dado-delete-popup',
    template: ''
})
export class DadoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dado }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DadoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.dado = dado;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/dado', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/dado', { outlets: { popup: null } }]);
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
