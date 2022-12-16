import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { TonkhodaukyComponent } from './list/tonkhodauky.component';
import { TonkhodaukyDetailComponent } from './detail/tonkhodauky-detail.component';
import { TonkhodaukyUpdateComponent } from './update/tonkhodauky-update.component';
import { TonkhodaukyDeleteDialogComponent } from './delete/tonkhodauky-delete-dialog.component';
import { TonkhodaukyRoutingModule } from './route/tonkhodauky-routing.module';

@NgModule({
  imports: [SharedModule, TonkhodaukyRoutingModule],
  declarations: [TonkhodaukyComponent, TonkhodaukyDetailComponent, TonkhodaukyUpdateComponent, TonkhodaukyDeleteDialogComponent],
})
export class TonkhodaukyModule {}
