import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITonkhodauky } from '../tonkhodauky.model';
import { TonkhodaukyService } from '../service/tonkhodauky.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './tonkhodauky-delete-dialog.component.html',
})
export class TonkhodaukyDeleteDialogComponent {
  tonkhodauky?: ITonkhodauky;

  constructor(protected tonkhodaukyService: TonkhodaukyService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tonkhodaukyService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
