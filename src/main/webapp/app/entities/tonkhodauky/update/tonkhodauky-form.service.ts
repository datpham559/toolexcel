import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ITonkhodauky, NewTonkhodauky } from '../tonkhodauky.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ITonkhodauky for edit and NewTonkhodaukyFormGroupInput for create.
 */
type TonkhodaukyFormGroupInput = ITonkhodauky | PartialWithRequiredKeyOf<NewTonkhodauky>;

type TonkhodaukyFormDefaults = Pick<NewTonkhodauky, 'id'>;

type TonkhodaukyFormGroupContent = {
  id: FormControl<ITonkhodauky['id'] | NewTonkhodauky['id']>;
  ten_kho: FormControl<ITonkhodauky['ten_kho']>;
  ma_hang: FormControl<ITonkhodauky['ma_hang']>;
  ten_hang: FormControl<ITonkhodauky['ten_hang']>;
  dvt: FormControl<ITonkhodauky['dvt']>;
  dau_ky_so_luong: FormControl<ITonkhodauky['dau_ky_so_luong']>;
  dau_ky_gia_tri: FormControl<ITonkhodauky['dau_ky_gia_tri']>;
  nhap_kho_so_luong: FormControl<ITonkhodauky['nhap_kho_so_luong']>;
  nhap_kho_gia_tri: FormControl<ITonkhodauky['nhap_kho_gia_tri']>;
  xuat_kho_so_luong: FormControl<ITonkhodauky['xuat_kho_so_luong']>;
  xuat_kho_gia_tri: FormControl<ITonkhodauky['xuat_kho_gia_tri']>;
  cuoi_ky_so_luong: FormControl<ITonkhodauky['cuoi_ky_so_luong']>;
  cuoi_ky_gia_tri: FormControl<ITonkhodauky['cuoi_ky_gia_tri']>;
  don_gia_binh_quan: FormControl<ITonkhodauky['don_gia_binh_quan']>;
  createdDate: FormControl<ITonkhodauky['createdDate']>;
  key_uuid: FormControl<ITonkhodauky['key_uuid']>;
};

export type TonkhodaukyFormGroup = FormGroup<TonkhodaukyFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class TonkhodaukyFormService {
  createTonkhodaukyFormGroup(tonkhodauky: TonkhodaukyFormGroupInput = { id: null }): TonkhodaukyFormGroup {
    const tonkhodaukyRawValue = {
      ...this.getFormDefaults(),
      ...tonkhodauky,
    };
    return new FormGroup<TonkhodaukyFormGroupContent>({
      id: new FormControl(
        { value: tonkhodaukyRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      ten_kho: new FormControl(tonkhodaukyRawValue.ten_kho),
      ma_hang: new FormControl(tonkhodaukyRawValue.ma_hang),
      ten_hang: new FormControl(tonkhodaukyRawValue.ten_hang),
      dvt: new FormControl(tonkhodaukyRawValue.dvt),
      dau_ky_so_luong: new FormControl(tonkhodaukyRawValue.dau_ky_so_luong),
      dau_ky_gia_tri: new FormControl(tonkhodaukyRawValue.dau_ky_gia_tri),
      nhap_kho_so_luong: new FormControl(tonkhodaukyRawValue.nhap_kho_so_luong),
      nhap_kho_gia_tri: new FormControl(tonkhodaukyRawValue.nhap_kho_gia_tri),
      xuat_kho_so_luong: new FormControl(tonkhodaukyRawValue.xuat_kho_so_luong),
      xuat_kho_gia_tri: new FormControl(tonkhodaukyRawValue.xuat_kho_gia_tri),
      cuoi_ky_so_luong: new FormControl(tonkhodaukyRawValue.cuoi_ky_so_luong),
      cuoi_ky_gia_tri: new FormControl(tonkhodaukyRawValue.cuoi_ky_gia_tri),
      don_gia_binh_quan: new FormControl(tonkhodaukyRawValue.don_gia_binh_quan),
      createdDate: new FormControl(tonkhodaukyRawValue.createdDate),
      key_uuid: new FormControl(tonkhodaukyRawValue.key_uuid),
    });
  }

  getTonkhodauky(form: TonkhodaukyFormGroup): ITonkhodauky | NewTonkhodauky {
    return form.getRawValue() as ITonkhodauky | NewTonkhodauky;
  }

  resetForm(form: TonkhodaukyFormGroup, tonkhodauky: TonkhodaukyFormGroupInput): void {
    const tonkhodaukyRawValue = { ...this.getFormDefaults(), ...tonkhodauky };
    form.reset(
      {
        ...tonkhodaukyRawValue,
        id: { value: tonkhodaukyRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): TonkhodaukyFormDefaults {
    return {
      id: null,
    };
  }
}
