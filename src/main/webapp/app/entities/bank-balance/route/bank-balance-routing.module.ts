import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { BankBalanceComponent } from '../list/bank-balance.component';
import { BankBalanceDetailComponent } from '../detail/bank-balance-detail.component';
import { BankBalanceUpdateComponent } from '../update/bank-balance-update.component';
import { BankBalanceRoutingResolveService } from './bank-balance-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const bankBalanceRoute: Routes = [
  {
    path: '',
    component: BankBalanceComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BankBalanceDetailComponent,
    resolve: {
      bankBalance: BankBalanceRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BankBalanceUpdateComponent,
    resolve: {
      bankBalance: BankBalanceRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BankBalanceUpdateComponent,
    resolve: {
      bankBalance: BankBalanceRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(bankBalanceRoute)],
  exports: [RouterModule],
})
export class BankBalanceRoutingModule {}
