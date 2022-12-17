import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SyntheticComponent } from '../list/synthetic.component';
import { SyntheticDetailComponent } from '../detail/synthetic-detail.component';
import { SyntheticUpdateComponent } from '../update/synthetic-update.component';
import { SyntheticRoutingResolveService } from './synthetic-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const syntheticRoute: Routes = [
  {
    path: '',
    component: SyntheticComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SyntheticDetailComponent,
    resolve: {
      synthetic: SyntheticRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SyntheticUpdateComponent,
    resolve: {
      synthetic: SyntheticRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SyntheticUpdateComponent,
    resolve: {
      synthetic: SyntheticRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(syntheticRoute)],
  exports: [RouterModule],
})
export class SyntheticRoutingModule {}
