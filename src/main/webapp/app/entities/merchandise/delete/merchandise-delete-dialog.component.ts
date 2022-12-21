import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IMerchandise } from '../merchandise.model';
import { MerchandiseService } from '../service/merchandise.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './merchandise-delete-dialog.component.html',
})
export class MerchandiseDeleteDialogComponent {
  merchandise?: IMerchandise;

  constructor(protected merchandiseService: MerchandiseService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.merchandiseService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
