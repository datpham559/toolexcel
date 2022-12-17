import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { SyntheticComponent } from './list/synthetic.component';
import { SyntheticDetailComponent } from './detail/synthetic-detail.component';
import { SyntheticUpdateComponent } from './update/synthetic-update.component';
import { SyntheticDeleteDialogComponent } from './delete/synthetic-delete-dialog.component';
import { SyntheticRoutingModule } from './route/synthetic-routing.module';

@NgModule({
  imports: [SharedModule, SyntheticRoutingModule],
  declarations: [SyntheticComponent, SyntheticDetailComponent, SyntheticUpdateComponent, SyntheticDeleteDialogComponent],
})
export class SyntheticModule {}
