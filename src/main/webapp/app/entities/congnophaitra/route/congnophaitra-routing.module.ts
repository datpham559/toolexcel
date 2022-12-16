import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CongnophaitraComponent } from '../list/congnophaitra.component';
import { CongnophaitraDetailComponent } from '../detail/congnophaitra-detail.component';
import { CongnophaitraUpdateComponent } from '../update/congnophaitra-update.component';
import { CongnophaitraRoutingResolveService } from './congnophaitra-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const congnophaitraRoute: Routes = [
  {
    path: '',
    component: CongnophaitraComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CongnophaitraDetailComponent,
    resolve: {
      congnophaitra: CongnophaitraRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CongnophaitraUpdateComponent,
    resolve: {
      congnophaitra: CongnophaitraRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CongnophaitraUpdateComponent,
    resolve: {
      congnophaitra: CongnophaitraRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(congnophaitraRoute)],
  exports: [RouterModule],
})
export class CongnophaitraRoutingModule {}
