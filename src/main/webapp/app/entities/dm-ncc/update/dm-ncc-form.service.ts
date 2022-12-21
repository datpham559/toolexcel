import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IDmNCC, NewDmNCC } from '../dm-ncc.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IDmNCC for edit and NewDmNCCFormGroupInput for create.
 */
type DmNCCFormGroupInput = IDmNCC | PartialWithRequiredKeyOf<NewDmNCC>;

type DmNCCFormDefaults = Pick<NewDmNCC, 'id' | 'unfollow'>;

type DmNCCFormGroupContent = {
  id: FormControl<IDmNCC['id'] | NewDmNCC['id']>;
  supplier_code: FormControl<IDmNCC['supplier_code']>;
  supplier_name: FormControl<IDmNCC['supplier_name']>;
  address: FormControl<IDmNCC['address']>;
  group_kh_ncc: FormControl<IDmNCC['group_kh_ncc']>;
  tax_code: FormControl<IDmNCC['tax_code']>;
  phone_number: FormControl<IDmNCC['phone_number']>;
  unfollow: FormControl<IDmNCC['unfollow']>;
  created_date: FormControl<IDmNCC['created_date']>;
  keyUUID: FormControl<IDmNCC['keyUUID']>;
};

export type DmNCCFormGroup = FormGroup<DmNCCFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class DmNCCFormService {
  createDmNCCFormGroup(dmNCC: DmNCCFormGroupInput = { id: null }): DmNCCFormGroup {
    const dmNCCRawValue = {
      ...this.getFormDefaults(),
      ...dmNCC,
    };
    return new FormGroup<DmNCCFormGroupContent>({
      id: new FormControl(
        { value: dmNCCRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      supplier_code: new FormControl(dmNCCRawValue.supplier_code),
      supplier_name: new FormControl(dmNCCRawValue.supplier_name),
      address: new FormControl(dmNCCRawValue.address),
      group_kh_ncc: new FormControl(dmNCCRawValue.group_kh_ncc),
      tax_code: new FormControl(dmNCCRawValue.tax_code),
      phone_number: new FormControl(dmNCCRawValue.phone_number),
      unfollow: new FormControl(dmNCCRawValue.unfollow),
      created_date: new FormControl(dmNCCRawValue.created_date),
      keyUUID: new FormControl(dmNCCRawValue.keyUUID),
    });
  }

  getDmNCC(form: DmNCCFormGroup): IDmNCC | NewDmNCC {
    return form.getRawValue() as IDmNCC | NewDmNCC;
  }

  resetForm(form: DmNCCFormGroup, dmNCC: DmNCCFormGroupInput): void {
    const dmNCCRawValue = { ...this.getFormDefaults(), ...dmNCC };
    form.reset(
      {
        ...dmNCCRawValue,
        id: { value: dmNCCRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): DmNCCFormDefaults {
    return {
      id: null,
      unfollow: false,
    };
  }
}
