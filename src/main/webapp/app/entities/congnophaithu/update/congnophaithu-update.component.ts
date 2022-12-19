import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { CongnophaithuFormService, CongnophaithuFormGroup } from './congnophaithu-form.service';
import { ICongnophaithu } from '../congnophaithu.model';
import { CongnophaithuService } from '../service/congnophaithu.service';

@Component({
  selector: 'jhi-congnophaithu-update',
  templateUrl: './congnophaithu-update.component.html',
})
export class CongnophaithuUpdateComponent implements OnInit {
  isSaving = false;
  congnophaithu: ICongnophaithu | null = null;

  editForm: CongnophaithuFormGroup = this.congnophaithuFormService.createCongnophaithuFormGroup();

  constructor(
    protected congnophaithuService: CongnophaithuService,
    protected congnophaithuFormService: CongnophaithuFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ congnophaithu }) => {
      this.congnophaithu = congnophaithu;
      if (congnophaithu) {
        this.updateForm(congnophaithu);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const congnophaithu = this.congnophaithuFormService.getCongnophaithu(this.editForm);
    if (congnophaithu.id !== null) {
      this.subscribeToSaveResponse(this.congnophaithuService.update(congnophaithu));
    } else {
      this.subscribeToSaveResponse(this.congnophaithuService.create(congnophaithu));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICongnophaithu>>): void {
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

  protected updateForm(congnophaithu: ICongnophaithu): void {
    this.congnophaithu = congnophaithu;
    this.congnophaithuFormService.resetForm(this.editForm, congnophaithu);
  }
}
