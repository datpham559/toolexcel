import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { DmNCCFormService, DmNCCFormGroup } from './dm-ncc-form.service';
import { IDmNCC } from '../dm-ncc.model';
import { DmNCCService } from '../service/dm-ncc.service';

@Component({
  selector: 'jhi-dm-ncc-update',
  templateUrl: './dm-ncc-update.component.html',
})
export class DmNCCUpdateComponent implements OnInit {
  isSaving = false;
  dmNCC: IDmNCC | null = null;

  editForm: DmNCCFormGroup = this.dmNCCFormService.createDmNCCFormGroup();

  constructor(
    protected dmNCCService: DmNCCService,
    protected dmNCCFormService: DmNCCFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dmNCC }) => {
      this.dmNCC = dmNCC;
      if (dmNCC) {
        this.updateForm(dmNCC);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dmNCC = this.dmNCCFormService.getDmNCC(this.editForm);
    if (dmNCC.id !== null) {
      this.subscribeToSaveResponse(this.dmNCCService.update(dmNCC));
    } else {
      this.subscribeToSaveResponse(this.dmNCCService.create(dmNCC));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDmNCC>>): void {
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

  protected updateForm(dmNCC: IDmNCC): void {
    this.dmNCC = dmNCC;
    this.dmNCCFormService.resetForm(this.editForm, dmNCC);
  }
}
