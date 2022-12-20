import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'customer',
        data: { pageTitle: 'Customers' },
        loadChildren: () => import('./customer/customer.module').then(m => m.CustomerModule),
      },
      {
        path: 'synthetic',
        data: { pageTitle: 'Synthetics' },
        loadChildren: () => import('./synthetic/synthetic.module').then(m => m.SyntheticModule),
      },
      {
        path: 'dm-ncc',
        data: { pageTitle: 'DmNCCS' },
        loadChildren: () => import('./dm-ncc/dm-ncc.module').then(m => m.DmNCCModule),
      },
      {
        path: 'bank-balance',
        data: { pageTitle: 'BankBalances' },
        loadChildren: () => import('./bank-balance/bank-balance.module').then(m => m.BankBalanceModule),
      },
      {
        path: 'merchandise',
        data: { pageTitle: 'Merchandises' },
        loadChildren: () => import('./merchandise/merchandise.module').then(m => m.MerchandiseModule),
      },
      {
        path: 'beginning-inventory',
        data: { pageTitle: 'BeginningInventories' },
        loadChildren: () => import('./beginning-inventory/beginning-inventory.module').then(m => m.BeginningInventoryModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
