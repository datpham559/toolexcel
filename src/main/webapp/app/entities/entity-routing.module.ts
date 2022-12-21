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
        path: 'congnophaithu',
        data: { pageTitle: 'Congnophaithus' },
        loadChildren: () => import('./congnophaithu/congnophaithu.module').then(m => m.CongnophaithuModule),
      },
      {
        path: 'congnophaitra',
        data: { pageTitle: 'Congnophaitras' },
        loadChildren: () => import('./congnophaitra/congnophaitra.module').then(m => m.CongnophaitraModule),
      },
      {
        path: 'congcudungcu',
        data: { pageTitle: 'Congcudungcus' },
        loadChildren: () => import('./congcudungcu/congcudungcu.module').then(m => m.CongcudungcuModule),
      },
      {
        path: 'taisancodinh',
        data: { pageTitle: 'Taisancodinhs' },
        loadChildren: () => import('./taisancodinh/taisancodinh.module').then(m => m.TaisancodinhModule),
      },
      {
        path: 'tonkhodauky',
        data: { pageTitle: 'Tonkhodaukies' },
        loadChildren: () => import('./tonkhodauky/tonkhodauky.module').then(m => m.TonkhodaukyModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
