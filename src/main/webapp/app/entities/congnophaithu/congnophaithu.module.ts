import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { CongnophaithuComponent } from './list/congnophaithu.component';
import { CongnophaithuDetailComponent } from './detail/congnophaithu-detail.component';
import { CongnophaithuUpdateComponent } from './update/congnophaithu-update.component';
import { CongnophaithuDeleteDialogComponent } from './delete/congnophaithu-delete-dialog.component';
import { CongnophaithuRoutingModule } from './route/congnophaithu-routing.module';

@NgModule({
  imports: [SharedModule, CongnophaithuRoutingModule],
  declarations: [CongnophaithuComponent, CongnophaithuDetailComponent, CongnophaithuUpdateComponent, CongnophaithuDeleteDialogComponent],
})
export class CongnophaithuModule {}
