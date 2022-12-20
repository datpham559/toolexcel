import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { BeginningInventoryFormService, BeginningInventoryFormGroup } from './beginning-inventory-form.service';
import { IBeginningInventory } from '../beginning-inventory.model';
import { BeginningInventoryService } from '../service/beginning-inventory.service';

@Component({
  selector: 'jhi-beginning-inventory-update',
  templateUrl: './beginning-inventory-update.component.html',
})
export class BeginningInventoryUpdateComponent implements OnInit {
  isSaving = false;
  beginningInventory: IBeginningInventory | null = null;

  editForm: BeginningInventoryFormGroup = this.beginningInventoryFormService.createBeginningInventoryFormGroup();

  constructor(
    protected beginningInventoryService: BeginningInventoryService,
    protected beginningInventoryFormService: BeginningInventoryFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ beginningInventory }) => {
      this.beginningInventory = beginningInventory;
      if (beginningInventory) {
        this.updateForm(beginningInventory);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const beginningInventory = this.beginningInventoryFormService.getBeginningInventory(this.editForm);
    if (beginningInventory.id !== null) {
      this.subscribeToSaveResponse(this.beginningInventoryService.update(beginningInventory));
    } else {
      this.subscribeToSaveResponse(this.beginningInventoryService.create(beginningInventory));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBeginningInventory>>): void {
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

  protected updateForm(beginningInventory: IBeginningInventory): void {
    this.beginningInventory = beginningInventory;
    this.beginningInventoryFormService.resetForm(this.editForm, beginningInventory);
  }
}
