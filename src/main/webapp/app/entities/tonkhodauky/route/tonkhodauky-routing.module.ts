import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TonkhodaukyComponent } from '../list/tonkhodauky.component';
import { TonkhodaukyDetailComponent } from '../detail/tonkhodauky-detail.component';
import { TonkhodaukyUpdateComponent } from '../update/tonkhodauky-update.component';
import { TonkhodaukyRoutingResolveService } from './tonkhodauky-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const tonkhodaukyRoute: Routes = [
  {
    path: '',
    component: TonkhodaukyComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TonkhodaukyDetailComponent,
    resolve: {
      tonkhodauky: TonkhodaukyRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TonkhodaukyUpdateComponent,
    resolve: {
      tonkhodauky: TonkhodaukyRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TonkhodaukyUpdateComponent,
    resolve: {
      tonkhodauky: TonkhodaukyRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tonkhodaukyRoute)],
  exports: [RouterModule],
})
export class TonkhodaukyRoutingModule {}
