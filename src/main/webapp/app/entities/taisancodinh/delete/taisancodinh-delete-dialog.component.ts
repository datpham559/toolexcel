import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITaisancodinh } from '../taisancodinh.model';
import { TaisancodinhService } from '../service/taisancodinh.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './taisancodinh-delete-dialog.component.html',
})
export class TaisancodinhDeleteDialogComponent {
  taisancodinh?: ITaisancodinh;

  constructor(protected taisancodinhService: TaisancodinhService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.taisancodinhService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
