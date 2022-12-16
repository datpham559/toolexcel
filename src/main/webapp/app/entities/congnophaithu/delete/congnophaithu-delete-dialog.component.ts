import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ICongnophaithu } from '../congnophaithu.model';
import { CongnophaithuService } from '../service/congnophaithu.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './congnophaithu-delete-dialog.component.html',
})
export class CongnophaithuDeleteDialogComponent {
  congnophaithu?: ICongnophaithu;

  constructor(protected congnophaithuService: CongnophaithuService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.congnophaithuService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
