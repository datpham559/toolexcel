import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { TaisancodinhComponent } from './list/taisancodinh.component';
import { TaisancodinhDetailComponent } from './detail/taisancodinh-detail.component';
import { TaisancodinhUpdateComponent } from './update/taisancodinh-update.component';
import { TaisancodinhDeleteDialogComponent } from './delete/taisancodinh-delete-dialog.component';
import { TaisancodinhRoutingModule } from './route/taisancodinh-routing.module';

@NgModule({
  imports: [SharedModule, TaisancodinhRoutingModule],
  declarations: [TaisancodinhComponent, TaisancodinhDetailComponent, TaisancodinhUpdateComponent, TaisancodinhDeleteDialogComponent],
})
export class TaisancodinhModule {}
