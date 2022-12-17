import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISynthetic } from '../synthetic.model';
import { SyntheticService } from '../service/synthetic.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './synthetic-delete-dialog.component.html',
})
export class SyntheticDeleteDialogComponent {
  synthetic?: ISynthetic;

  constructor(protected syntheticService: SyntheticService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.syntheticService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
