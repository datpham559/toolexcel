import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IBeginningInventory } from '../beginning-inventory.model';
import { BeginningInventoryService } from '../service/beginning-inventory.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './beginning-inventory-delete-dialog.component.html',
})
export class BeginningInventoryDeleteDialogComponent {
  beginningInventory?: IBeginningInventory;

  constructor(protected beginningInventoryService: BeginningInventoryService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.beginningInventoryService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
