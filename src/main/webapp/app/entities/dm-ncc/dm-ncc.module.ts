import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { DmNCCComponent } from './list/dm-ncc.component';
import { DmNCCDetailComponent } from './detail/dm-ncc-detail.component';
import { DmNCCUpdateComponent } from './update/dm-ncc-update.component';
import { DmNCCDeleteDialogComponent } from './delete/dm-ncc-delete-dialog.component';
import { DmNCCRoutingModule } from './route/dm-ncc-routing.module';

@NgModule({
  imports: [SharedModule, DmNCCRoutingModule],
  declarations: [DmNCCComponent, DmNCCDetailComponent, DmNCCUpdateComponent, DmNCCDeleteDialogComponent],
})
export class DmNCCModule {}
