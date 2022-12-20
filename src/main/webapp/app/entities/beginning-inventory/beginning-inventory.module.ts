import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { BeginningInventoryComponent } from './list/beginning-inventory.component';
import { BeginningInventoryDetailComponent } from './detail/beginning-inventory-detail.component';
import { BeginningInventoryUpdateComponent } from './update/beginning-inventory-update.component';
import { BeginningInventoryDeleteDialogComponent } from './delete/beginning-inventory-delete-dialog.component';
import { BeginningInventoryRoutingModule } from './route/beginning-inventory-routing.module';

@NgModule({
  imports: [SharedModule, BeginningInventoryRoutingModule],
  declarations: [
    BeginningInventoryComponent,
    BeginningInventoryDetailComponent,
    BeginningInventoryUpdateComponent,
    BeginningInventoryDeleteDialogComponent,
  ],
})
export class BeginningInventoryModule {}
