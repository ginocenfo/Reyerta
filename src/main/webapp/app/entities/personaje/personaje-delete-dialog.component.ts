import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPersonaje } from 'app/shared/model/personaje.model';
import { PersonajeService } from './personaje.service';

@Component({
    selector: 'jhi-personaje-delete-dialog',
    templateUrl: './personaje-delete-dialog.component.html'
})
export class PersonajeDeleteDialogComponent {
    personaje: IPersonaje;

    constructor(
        protected personajeService: PersonajeService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.personajeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'personajeListModification',
                content: 'Deleted an personaje'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-personaje-delete-popup',
    template: ''
})
export class PersonajeDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ personaje }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PersonajeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.personaje = personaje;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/personaje', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/personaje', { outlets: { popup: null } }]);
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
