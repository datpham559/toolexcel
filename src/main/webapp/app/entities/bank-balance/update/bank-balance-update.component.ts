import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { BankBalanceFormService, BankBalanceFormGroup } from './bank-balance-form.service';
import { IBankBalance } from '../bank-balance.model';
import { BankBalanceService } from '../service/bank-balance.service';

@Component({
  selector: 'jhi-bank-balance-update',
  templateUrl: './bank-balance-update.component.html',
})
export class BankBalanceUpdateComponent implements OnInit {
  isSaving = false;
  bankBalance: IBankBalance | null = null;

  editForm: BankBalanceFormGroup = this.bankBalanceFormService.createBankBalanceFormGroup();

  constructor(
    protected bankBalanceService: BankBalanceService,
    protected bankBalanceFormService: BankBalanceFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bankBalance }) => {
      this.bankBalance = bankBalance;
      if (bankBalance) {
        this.updateForm(bankBalance);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bankBalance = this.bankBalanceFormService.getBankBalance(this.editForm);
    if (bankBalance.id !== null) {
      this.subscribeToSaveResponse(this.bankBalanceService.update(bankBalance));
    } else {
      this.subscribeToSaveResponse(this.bankBalanceService.create(bankBalance));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBankBalance>>): void {
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

  protected updateForm(bankBalance: IBankBalance): void {
    this.bankBalance = bankBalance;
    this.bankBalanceFormService.resetForm(this.editForm, bankBalance);
  }
}
