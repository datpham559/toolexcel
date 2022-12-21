import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IBankBalance } from '../bank-balance.model';
import { BankBalanceService } from '../service/bank-balance.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './bank-balance-delete-dialog.component.html',
})
export class BankBalanceDeleteDialogComponent {
  bankBalance?: IBankBalance;

  constructor(protected bankBalanceService: BankBalanceService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bankBalanceService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
