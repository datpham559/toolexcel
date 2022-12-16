import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CongnophaithuComponent } from '../list/congnophaithu.component';
import { CongnophaithuDetailComponent } from '../detail/congnophaithu-detail.component';
import { CongnophaithuUpdateComponent } from '../update/congnophaithu-update.component';
import { CongnophaithuRoutingResolveService } from './congnophaithu-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const congnophaithuRoute: Routes = [
  {
    path: '',
    component: CongnophaithuComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CongnophaithuDetailComponent,
    resolve: {
      congnophaithu: CongnophaithuRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CongnophaithuUpdateComponent,
    resolve: {
      congnophaithu: CongnophaithuRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CongnophaithuUpdateComponent,
    resolve: {
      congnophaithu: CongnophaithuRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(congnophaithuRoute)],
  exports: [RouterModule],
})
export class CongnophaithuRoutingModule {}
