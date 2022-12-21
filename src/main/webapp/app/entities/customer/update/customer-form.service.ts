import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICustomer, NewCustomer } from '../customer.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICustomer for edit and NewCustomerFormGroupInput for create.
 */
type CustomerFormGroupInput = ICustomer | PartialWithRequiredKeyOf<NewCustomer>;

type CustomerFormDefaults = Pick<NewCustomer, 'id'>;

type CustomerFormGroupContent = {
  id: FormControl<ICustomer['id'] | NewCustomer['id']>;
  customerCode: FormControl<ICustomer['customerCode']>;
  customerName: FormControl<ICustomer['customerName']>;
  address: FormControl<ICustomer['address']>;
  customerGroup: FormControl<ICustomer['customerGroup']>;
  tax: FormControl<ICustomer['tax']>;
  phoneNumber: FormControl<ICustomer['phoneNumber']>;
  unfollow: FormControl<ICustomer['unfollow']>;
  createdDate: FormControl<ICustomer['createdDate']>;
  keyUUID: FormControl<ICustomer['keyUUID']>;
};

export type CustomerFormGroup = FormGroup<CustomerFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CustomerFormService {
  createCustomerFormGroup(customer: CustomerFormGroupInput = { id: null }): CustomerFormGroup {
    const customerRawValue = {
      ...this.getFormDefaults(),
      ...customer,
    };
    return new FormGroup<CustomerFormGroupContent>({
      id: new FormControl(
        { value: customerRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      customerCode: new FormControl(customerRawValue.customerCode),
      customerName: new FormControl(customerRawValue.customerName),
      address: new FormControl(customerRawValue.address),
      customerGroup: new FormControl(customerRawValue.customerGroup),
      tax: new FormControl(customerRawValue.tax),
      phoneNumber: new FormControl(customerRawValue.phoneNumber),
      unfollow: new FormControl(customerRawValue.unfollow),
      createdDate: new FormControl(customerRawValue.createdDate),
      keyUUID: new FormControl(customerRawValue.keyUUID),
    });
  }

  getCustomer(form: CustomerFormGroup): ICustomer | NewCustomer {
    return form.getRawValue() as ICustomer | NewCustomer;
  }

  resetForm(form: CustomerFormGroup, customer: CustomerFormGroupInput): void {
    const customerRawValue = { ...this.getFormDefaults(), ...customer };
    form.reset(
      {
        ...customerRawValue,
        id: { value: customerRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CustomerFormDefaults {
    return {
      id: null,
    };
  }
}
