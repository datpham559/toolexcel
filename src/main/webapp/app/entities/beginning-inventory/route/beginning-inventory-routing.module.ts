import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { BeginningInventoryComponent } from '../list/beginning-inventory.component';
import { BeginningInventoryDetailComponent } from '../detail/beginning-inventory-detail.component';
import { BeginningInventoryUpdateComponent } from '../update/beginning-inventory-update.component';
import { BeginningInventoryRoutingResolveService } from './beginning-inventory-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const beginningInventoryRoute: Routes = [
  {
    path: '',
    component: BeginningInventoryComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BeginningInventoryDetailComponent,
    resolve: {
      beginningInventory: BeginningInventoryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BeginningInventoryUpdateComponent,
    resolve: {
      beginningInventory: BeginningInventoryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BeginningInventoryUpdateComponent,
    resolve: {
      beginningInventory: BeginningInventoryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(beginningInventoryRoute)],
  exports: [RouterModule],
})
export class BeginningInventoryRoutingModule {}
