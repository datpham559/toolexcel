import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { TonkhodaukyFormService, TonkhodaukyFormGroup } from './tonkhodauky-form.service';
import { ITonkhodauky } from '../tonkhodauky.model';
import { TonkhodaukyService } from '../service/tonkhodauky.service';

@Component({
  selector: 'jhi-tonkhodauky-update',
  templateUrl: './tonkhodauky-update.component.html',
})
export class TonkhodaukyUpdateComponent implements OnInit {
  isSaving = false;
  tonkhodauky: ITonkhodauky | null = null;

  editForm: TonkhodaukyFormGroup = this.tonkhodaukyFormService.createTonkhodaukyFormGroup();

  constructor(
    protected tonkhodaukyService: TonkhodaukyService,
    protected tonkhodaukyFormService: TonkhodaukyFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tonkhodauky }) => {
      this.tonkhodauky = tonkhodauky;
      if (tonkhodauky) {
        this.updateForm(tonkhodauky);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tonkhodauky = this.tonkhodaukyFormService.getTonkhodauky(this.editForm);
    if (tonkhodauky.id !== null) {
      this.subscribeToSaveResponse(this.tonkhodaukyService.update(tonkhodauky));
    } else {
      this.subscribeToSaveResponse(this.tonkhodaukyService.create(tonkhodauky));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITonkhodauky>>): void {
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

  protected updateForm(tonkhodauky: ITonkhodauky): void {
    this.tonkhodauky = tonkhodauky;
    this.tonkhodaukyFormService.resetForm(this.editForm, tonkhodauky);
  }
}
