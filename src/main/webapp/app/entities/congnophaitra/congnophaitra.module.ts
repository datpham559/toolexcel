import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { CongnophaitraComponent } from './list/congnophaitra.component';
import { CongnophaitraDetailComponent } from './detail/congnophaitra-detail.component';
import { CongnophaitraUpdateComponent } from './update/congnophaitra-update.component';
import { CongnophaitraDeleteDialogComponent } from './delete/congnophaitra-delete-dialog.component';
import { CongnophaitraRoutingModule } from './route/congnophaitra-routing.module';

@NgModule({
  imports: [SharedModule, CongnophaitraRoutingModule],
  declarations: [CongnophaitraComponent, CongnophaitraDetailComponent, CongnophaitraUpdateComponent, CongnophaitraDeleteDialogComponent],
})
export class CongnophaitraModule {}
