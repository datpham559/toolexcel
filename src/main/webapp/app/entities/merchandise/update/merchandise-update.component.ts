import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { MerchandiseFormService, MerchandiseFormGroup } from './merchandise-form.service';
import { IMerchandise } from '../merchandise.model';
import { MerchandiseService } from '../service/merchandise.service';

@Component({
  selector: 'jhi-merchandise-update',
  templateUrl: './merchandise-update.component.html',
})
export class MerchandiseUpdateComponent implements OnInit {
  isSaving = false;
  merchandise: IMerchandise | null = null;

  editForm: MerchandiseFormGroup = this.merchandiseFormService.createMerchandiseFormGroup();

  constructor(
    protected merchandiseService: MerchandiseService,
    protected merchandiseFormService: MerchandiseFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ merchandise }) => {
      this.merchandise = merchandise;
      if (merchandise) {
        this.updateForm(merchandise);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const merchandise = this.merchandiseFormService.getMerchandise(this.editForm);
    if (merchandise.id !== null) {
      this.subscribeToSaveResponse(this.merchandiseService.update(merchandise));
    } else {
      this.subscribeToSaveResponse(this.merchandiseService.create(merchandise));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMerchandise>>): void {
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

  protected updateForm(merchandise: IMerchandise): void {
    this.merchandise = merchandise;
    this.merchandiseFormService.resetForm(this.editForm, merchandise);
  }
}
