import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IDmNCC } from '../dm-ncc.model';
import { DmNCCService } from '../service/dm-ncc.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './dm-ncc-delete-dialog.component.html',
})
export class DmNCCDeleteDialogComponent {
  dmNCC?: IDmNCC;

  constructor(protected dmNCCService: DmNCCService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.dmNCCService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
