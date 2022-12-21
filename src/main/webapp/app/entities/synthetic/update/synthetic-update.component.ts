import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { SyntheticFormService, SyntheticFormGroup } from './synthetic-form.service';
import { ISynthetic } from '../synthetic.model';
import { SyntheticService } from '../service/synthetic.service';

@Component({
  selector: 'jhi-synthetic-update',
  templateUrl: './synthetic-update.component.html',
})
export class SyntheticUpdateComponent implements OnInit {
  isSaving = false;
  synthetic: ISynthetic | null = null;

  editForm: SyntheticFormGroup = this.syntheticFormService.createSyntheticFormGroup();

  constructor(
    protected syntheticService: SyntheticService,
    protected syntheticFormService: SyntheticFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ synthetic }) => {
      this.synthetic = synthetic;
      if (synthetic) {
        this.updateForm(synthetic);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const synthetic = this.syntheticFormService.getSynthetic(this.editForm);
    if (synthetic.id !== null) {
      this.subscribeToSaveResponse(this.syntheticService.update(synthetic));
    } else {
      this.subscribeToSaveResponse(this.syntheticService.create(synthetic));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISynthetic>>): void {
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

  protected updateForm(synthetic: ISynthetic): void {
    this.synthetic = synthetic;
    this.syntheticFormService.resetForm(this.editForm, synthetic);
  }
}
