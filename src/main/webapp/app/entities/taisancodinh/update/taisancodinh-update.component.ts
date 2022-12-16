import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { TaisancodinhFormService, TaisancodinhFormGroup } from './taisancodinh-form.service';
import { ITaisancodinh } from '../taisancodinh.model';
import { TaisancodinhService } from '../service/taisancodinh.service';

@Component({
  selector: 'jhi-taisancodinh-update',
  templateUrl: './taisancodinh-update.component.html',
})
export class TaisancodinhUpdateComponent implements OnInit {
  isSaving = false;
  taisancodinh: ITaisancodinh | null = null;

  editForm: TaisancodinhFormGroup = this.taisancodinhFormService.createTaisancodinhFormGroup();

  constructor(
    protected taisancodinhService: TaisancodinhService,
    protected taisancodinhFormService: TaisancodinhFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ taisancodinh }) => {
      this.taisancodinh = taisancodinh;
      if (taisancodinh) {
        this.updateForm(taisancodinh);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const taisancodinh = this.taisancodinhFormService.getTaisancodinh(this.editForm);
    if (taisancodinh.id !== null) {
      this.subscribeToSaveResponse(this.taisancodinhService.update(taisancodinh));
    } else {
      this.subscribeToSaveResponse(this.taisancodinhService.create(taisancodinh));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITaisancodinh>>): void {
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

  protected updateForm(taisancodinh: ITaisancodinh): void {
    this.taisancodinh = taisancodinh;
    this.taisancodinhFormService.resetForm(this.editForm, taisancodinh);
  }
}
