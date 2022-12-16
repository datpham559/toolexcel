import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ICongcudungcu } from '../congcudungcu.model';
import { CongcudungcuService } from '../service/congcudungcu.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './congcudungcu-delete-dialog.component.html',
})
export class CongcudungcuDeleteDialogComponent {
  congcudungcu?: ICongcudungcu;

  constructor(protected congcudungcuService: CongcudungcuService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.congcudungcuService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
