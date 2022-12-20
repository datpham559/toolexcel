import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { BankBalanceComponent } from './list/bank-balance.component';
import { BankBalanceDetailComponent } from './detail/bank-balance-detail.component';
import { BankBalanceUpdateComponent } from './update/bank-balance-update.component';
import { BankBalanceDeleteDialogComponent } from './delete/bank-balance-delete-dialog.component';
import { BankBalanceRoutingModule } from './route/bank-balance-routing.module';

@NgModule({
  imports: [SharedModule, BankBalanceRoutingModule],
  declarations: [BankBalanceComponent, BankBalanceDetailComponent, BankBalanceUpdateComponent, BankBalanceDeleteDialogComponent],
})
export class BankBalanceModule {}
