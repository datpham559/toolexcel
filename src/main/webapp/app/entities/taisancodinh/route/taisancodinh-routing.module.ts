import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TaisancodinhComponent } from '../list/taisancodinh.component';
import { TaisancodinhDetailComponent } from '../detail/taisancodinh-detail.component';
import { TaisancodinhUpdateComponent } from '../update/taisancodinh-update.component';
import { TaisancodinhRoutingResolveService } from './taisancodinh-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const taisancodinhRoute: Routes = [
  {
    path: '',
    component: TaisancodinhComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TaisancodinhDetailComponent,
    resolve: {
      taisancodinh: TaisancodinhRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TaisancodinhUpdateComponent,
    resolve: {
      taisancodinh: TaisancodinhRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TaisancodinhUpdateComponent,
    resolve: {
      taisancodinh: TaisancodinhRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(taisancodinhRoute)],
  exports: [RouterModule],
})
export class TaisancodinhRoutingModule {}
