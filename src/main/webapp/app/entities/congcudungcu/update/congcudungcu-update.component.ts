import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { CongcudungcuFormService, CongcudungcuFormGroup } from './congcudungcu-form.service';
import { ICongcudungcu } from '../congcudungcu.model';
import { CongcudungcuService } from '../service/congcudungcu.service';

@Component({
  selector: 'jhi-congcudungcu-update',
  templateUrl: './congcudungcu-update.component.html',
})
export class CongcudungcuUpdateComponent implements OnInit {
  isSaving = false;
  congcudungcu: ICongcudungcu | null = null;

  editForm: CongcudungcuFormGroup = this.congcudungcuFormService.createCongcudungcuFormGroup();

  constructor(
    protected congcudungcuService: CongcudungcuService,
    protected congcudungcuFormService: CongcudungcuFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ congcudungcu }) => {
      this.congcudungcu = congcudungcu;
      if (congcudungcu) {
        this.updateForm(congcudungcu);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const congcudungcu = this.congcudungcuFormService.getCongcudungcu(this.editForm);
    if (congcudungcu.id !== null) {
      this.subscribeToSaveResponse(this.congcudungcuService.update(congcudungcu));
    } else {
      this.subscribeToSaveResponse(this.congcudungcuService.create(congcudungcu));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICongcudungcu>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(congcudungcu: ICongcudungcu): void {
    this.congcudungcu = congcudungcu;
    this.congcudungcuFormService.resetForm(this.editForm, congcudungcu);
  }
}
