import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { CongcudungcuComponent } from './list/congcudungcu.component';
import { CongcudungcuDetailComponent } from './detail/congcudungcu-detail.component';
import { CongcudungcuUpdateComponent } from './update/congcudungcu-update.component';
import { CongcudungcuDeleteDialogComponent } from './delete/congcudungcu-delete-dialog.component';
import { CongcudungcuRoutingModule } from './route/congcudungcu-routing.module';

@NgModule({
  imports: [SharedModule, CongcudungcuRoutingModule],
  declarations: [CongcudungcuComponent, CongcudungcuDetailComponent, CongcudungcuUpdateComponent, CongcudungcuDeleteDialogComponent],
})
export class CongcudungcuModule {}
