import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICongnophaitra, NewCongnophaitra } from '../congnophaitra.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICongnophaitra for edit and NewCongnophaitraFormGroupInput for create.
 */
type CongnophaitraFormGroupInput = ICongnophaitra | PartialWithRequiredKeyOf<NewCongnophaitra>;

type CongnophaitraFormDefaults = Pick<NewCongnophaitra, 'id'>;

type CongnophaitraFormGroupContent = {
  id: FormControl<ICongnophaitra['id'] | NewCongnophaitra['id']>;
  ma_ncc: FormControl<ICongnophaitra['ma_ncc']>;
  ten_ncc: FormControl<ICongnophaitra['ten_ncc']>;
  tk_congno: FormControl<ICongnophaitra['tk_congno']>;
  so_du_no_dau_ky: FormControl<ICongnophaitra['so_du_no_dau_ky']>;
  so_du_co_dau_ky: FormControl<ICongnophaitra['so_du_co_dau_ky']>;
  phat_sinh_no: FormControl<ICongnophaitra['phat_sinh_no']>;
  phat_sinh_co: FormControl<ICongnophaitra['phat_sinh_co']>;
  so_du_no_cuoi_ky: FormControl<ICongnophaitra['so_du_no_cuoi_ky']>;
  so_du_co_cuoi_ky: FormControl<ICongnophaitra['so_du_co_cuoi_ky']>;
  created_Date: FormControl<ICongnophaitra['created_Date']>;
  key_uuid: FormControl<ICongnophaitra['key_uuid']>;
};

export type CongnophaitraFormGroup = FormGroup<CongnophaitraFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CongnophaitraFormService {
  createCongnophaitraFormGroup(congnophaitra: CongnophaitraFormGroupInput = { id: null }): CongnophaitraFormGroup {
    const congnophaitraRawValue = {
      ...this.getFormDefaults(),
      ...congnophaitra,
    };
    return new FormGroup<CongnophaitraFormGroupContent>({
      id: new FormControl(
        { value: congnophaitraRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      ma_ncc: new FormControl(congnophaitraRawValue.ma_ncc),
      ten_ncc: new FormControl(congnophaitraRawValue.ten_ncc),
      tk_congno: new FormControl(congnophaitraRawValue.tk_congno),
      so_du_no_dau_ky: new FormControl(congnophaitraRawValue.so_du_no_dau_ky),
      so_du_co_dau_ky: new FormControl(congnophaitraRawValue.so_du_co_dau_ky),
      phat_sinh_no: new FormControl(congnophaitraRawValue.phat_sinh_no),
      phat_sinh_co: new FormControl(congnophaitraRawValue.phat_sinh_co),
      so_du_no_cuoi_ky: new FormControl(congnophaitraRawValue.so_du_no_cuoi_ky),
      so_du_co_cuoi_ky: new FormControl(congnophaitraRawValue.so_du_co_cuoi_ky),
      created_Date: new FormControl(congnophaitraRawValue.created_Date),
      key_uuid: new FormControl(congnophaitraRawValue.key_uuid),
    });
  }

  getCongnophaitra(form: CongnophaitraFormGroup): ICongnophaitra | NewCongnophaitra {
    return form.getRawValue() as ICongnophaitra | NewCongnophaitra;
  }

  resetForm(form: CongnophaitraFormGroup, congnophaitra: CongnophaitraFormGroupInput): void {
    const congnophaitraRawValue = { ...this.getFormDefaults(), ...congnophaitra };
    form.reset(
      {
        ...congnophaitraRawValue,
        id: { value: congnophaitraRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CongnophaitraFormDefaults {
    return {
      id: null,
    };
  }
}
