import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CongcudungcuComponent } from '../list/congcudungcu.component';
import { CongcudungcuDetailComponent } from '../detail/congcudungcu-detail.component';
import { CongcudungcuUpdateComponent } from '../update/congcudungcu-update.component';
import { CongcudungcuRoutingResolveService } from './congcudungcu-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const congcudungcuRoute: Routes = [
  {
    path: '',
    component: CongcudungcuComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CongcudungcuDetailComponent,
    resolve: {
      congcudungcu: CongcudungcuRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CongcudungcuUpdateComponent,
    resolve: {
      congcudungcu: CongcudungcuRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CongcudungcuUpdateComponent,
    resolve: {
      congcudungcu: CongcudungcuRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(congcudungcuRoute)],
  exports: [RouterModule],
})
export class CongcudungcuRoutingModule {}
