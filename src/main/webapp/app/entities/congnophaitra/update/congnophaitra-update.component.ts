import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { CongnophaitraFormService, CongnophaitraFormGroup } from './congnophaitra-form.service';
import { ICongnophaitra } from '../congnophaitra.model';
import { CongnophaitraService } from '../service/congnophaitra.service';

@Component({
  selector: 'jhi-congnophaitra-update',
  templateUrl: './congnophaitra-update.component.html',
})
export class CongnophaitraUpdateComponent implements OnInit {
  isSaving = false;
  congnophaitra: ICongnophaitra | null = null;

  editForm: CongnophaitraFormGroup = this.congnophaitraFormService.createCongnophaitraFormGroup();

  constructor(
    protected congnophaitraService: CongnophaitraService,
    protected congnophaitraFormService: CongnophaitraFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ congnophaitra }) => {
      this.congnophaitra = congnophaitra;
      if (congnophaitra) {
        this.updateForm(congnophaitra);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const congnophaitra = this.congnophaitraFormService.getCongnophaitra(this.editForm);
    if (congnophaitra.id !== null) {
      this.subscribeToSaveResponse(this.congnophaitraService.update(congnophaitra));
    } else {
      this.subscribeToSaveResponse(this.congnophaitraService.create(congnophaitra));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICongnophaitra>>): void {
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

  protected updateForm(congnophaitra: ICongnophaitra): void {
    this.congnophaitra = congnophaitra;
    this.congnophaitraFormService.resetForm(this.editForm, congnophaitra);
  }
}
