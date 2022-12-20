import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IBankBalance, NewBankBalance } from '../bank-balance.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IBankBalance for edit and NewBankBalanceFormGroupInput for create.
 */
type BankBalanceFormGroupInput = IBankBalance | PartialWithRequiredKeyOf<NewBankBalance>;

type BankBalanceFormDefaults = Pick<NewBankBalance, 'id'>;

type BankBalanceFormGroupContent = {
  id: FormControl<IBankBalance['id'] | NewBankBalance['id']>;
  bank_account: FormControl<IBankBalance['bank_account']>;
  bank_name: FormControl<IBankBalance['bank_name']>;
  branch: FormControl<IBankBalance['branch']>;
  opening_balance: FormControl<IBankBalance['opening_balance']>;
  debt_incurred: FormControl<IBankBalance['debt_incurred']>;
  incurred: FormControl<IBankBalance['incurred']>;
  ending_balance: FormControl<IBankBalance['ending_balance']>;
  created_date: FormControl<IBankBalance['created_date']>;
  keyUUID: FormControl<IBankBalance['keyUUID']>;
};

export type BankBalanceFormGroup = FormGroup<BankBalanceFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class BankBalanceFormService {
  createBankBalanceFormGroup(bankBalance: BankBalanceFormGroupInput = { id: null }): BankBalanceFormGroup {
    const bankBalanceRawValue = {
      ...this.getFormDefaults(),
      ...bankBalance,
    };
    return new FormGroup<BankBalanceFormGroupContent>({
      id: new FormControl(
        { value: bankBalanceRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      bank_account: new FormControl(bankBalanceRawValue.bank_account),
      bank_name: new FormControl(bankBalanceRawValue.bank_name),
      branch: new FormControl(bankBalanceRawValue.branch),
      opening_balance: new FormControl(bankBalanceRawValue.opening_balance),
      debt_incurred: new FormControl(bankBalanceRawValue.debt_incurred),
      incurred: new FormControl(bankBalanceRawValue.incurred),
      ending_balance: new FormControl(bankBalanceRawValue.ending_balance),
      created_date: new FormControl(bankBalanceRawValue.created_date),
      keyUUID: new FormControl(bankBalanceRawValue.keyUUID),
    });
  }

  getBankBalance(form: BankBalanceFormGroup): IBankBalance | NewBankBalance {
    return form.getRawValue() as IBankBalance | NewBankBalance;
  }

  resetForm(form: BankBalanceFormGroup, bankBalance: BankBalanceFormGroupInput): void {
    const bankBalanceRawValue = { ...this.getFormDefaults(), ...bankBalance };
    form.reset(
      {
        ...bankBalanceRawValue,
        id: { value: bankBalanceRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): BankBalanceFormDefaults {
    return {
      id: null,
    };
  }
}
