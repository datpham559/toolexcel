import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { DmNCCComponent } from '../list/dm-ncc.component';
import { DmNCCDetailComponent } from '../detail/dm-ncc-detail.component';
import { DmNCCUpdateComponent } from '../update/dm-ncc-update.component';
import { DmNCCRoutingResolveService } from './dm-ncc-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const dmNCCRoute: Routes = [
  {
    path: '',
    component: DmNCCComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DmNCCDetailComponent,
    resolve: {
      dmNCC: DmNCCRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DmNCCUpdateComponent,
    resolve: {
      dmNCC: DmNCCRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DmNCCUpdateComponent,
    resolve: {
      dmNCC: DmNCCRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(dmNCCRoute)],
  exports: [RouterModule],
})
export class DmNCCRoutingModule {}
