import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IBeginningInventory, NewBeginningInventory } from '../beginning-inventory.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IBeginningInventory for edit and NewBeginningInventoryFormGroupInput for create.
 */
type BeginningInventoryFormGroupInput = IBeginningInventory | PartialWithRequiredKeyOf<NewBeginningInventory>;

type BeginningInventoryFormDefaults = Pick<NewBeginningInventory, 'id'>;

type BeginningInventoryFormGroupContent = {
  id: FormControl<IBeginningInventory['id'] | NewBeginningInventory['id']>;
  account_number: FormControl<IBeginningInventory['account_number']>;
  account_name: FormControl<IBeginningInventory['account_name']>;
  first_debt: FormControl<IBeginningInventory['first_debt']>;
  first_yes: FormControl<IBeginningInventory['first_yes']>;
  debt_arises: FormControl<IBeginningInventory['debt_arises']>;
  arises_yes: FormControl<IBeginningInventory['arises_yes']>;
  accumulated_debt: FormControl<IBeginningInventory['accumulated_debt']>;
  accumulated_yes: FormControl<IBeginningInventory['accumulated_yes']>;
  last_debt: FormControl<IBeginningInventory['last_debt']>;
  last_yes: FormControl<IBeginningInventory['last_yes']>;
  created_date: FormControl<IBeginningInventory['created_date']>;
  keyUUID: FormControl<IBeginningInventory['keyUUID']>;
};

export type BeginningInventoryFormGroup = FormGroup<BeginningInventoryFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class BeginningInventoryFormService {
  createBeginningInventoryFormGroup(beginningInventory: BeginningInventoryFormGroupInput = { id: null }): BeginningInventoryFormGroup {
    const beginningInventoryRawValue = {
      ...this.getFormDefaults(),
      ...beginningInventory,
    };
    return new FormGroup<BeginningInventoryFormGroupContent>({
      id: new FormControl(
        { value: beginningInventoryRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      account_number: new FormControl(beginningInventoryRawValue.account_number),
      account_name: new FormControl(beginningInventoryRawValue.account_name),
      first_debt: new FormControl(beginningInventoryRawValue.first_debt),
      first_yes: new FormControl(beginningInventoryRawValue.first_yes),
      debt_arises: new FormControl(beginningInventoryRawValue.debt_arises),
      arises_yes: new FormControl(beginningInventoryRawValue.arises_yes),
      accumulated_debt: new FormControl(beginningInventoryRawValue.accumulated_debt),
      accumulated_yes: new FormControl(beginningInventoryRawValue.accumulated_yes),
      last_debt: new FormControl(beginningInventoryRawValue.last_debt),
      last_yes: new FormControl(beginningInventoryRawValue.last_yes),
      created_date: new FormControl(beginningInventoryRawValue.created_date),
      keyUUID: new FormControl(beginningInventoryRawValue.keyUUID),
    });
  }

  getBeginningInventory(form: BeginningInventoryFormGroup): IBeginningInventory | NewBeginningInventory {
    return form.getRawValue() as IBeginningInventory | NewBeginningInventory;
  }

  resetForm(form: BeginningInventoryFormGroup, beginningInventory: BeginningInventoryFormGroupInput): void {
    const beginningInventoryRawValue = { ...this.getFormDefaults(), ...beginningInventory };
    form.reset(
      {
        ...beginningInventoryRawValue,
        id: { value: beginningInventoryRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): BeginningInventoryFormDefaults {
    return {
      id: null,
    };
  }
}
