import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { MerchandiseComponent } from './list/merchandise.component';
import { MerchandiseDetailComponent } from './detail/merchandise-detail.component';
import { MerchandiseUpdateComponent } from './update/merchandise-update.component';
import { MerchandiseDeleteDialogComponent } from './delete/merchandise-delete-dialog.component';
import { MerchandiseRoutingModule } from './route/merchandise-routing.module';

@NgModule({
  imports: [SharedModule, MerchandiseRoutingModule],
  declarations: [MerchandiseComponent, MerchandiseDetailComponent, MerchandiseUpdateComponent, MerchandiseDeleteDialogComponent],
})
export class MerchandiseModule {}
