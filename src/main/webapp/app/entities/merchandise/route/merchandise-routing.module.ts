import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { MerchandiseComponent } from '../list/merchandise.component';
import { MerchandiseDetailComponent } from '../detail/merchandise-detail.component';
import { MerchandiseUpdateComponent } from '../update/merchandise-update.component';
import { MerchandiseRoutingResolveService } from './merchandise-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const merchandiseRoute: Routes = [
  {
    path: '',
    component: MerchandiseComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MerchandiseDetailComponent,
    resolve: {
      merchandise: MerchandiseRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MerchandiseUpdateComponent,
    resolve: {
      merchandise: MerchandiseRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MerchandiseUpdateComponent,
    resolve: {
      merchandise: MerchandiseRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(merchandiseRoute)],
  exports: [RouterModule],
})
export class MerchandiseRoutingModule {}
