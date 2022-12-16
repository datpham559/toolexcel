import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICongcudungcu, NewCongcudungcu } from '../congcudungcu.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICongcudungcu for edit and NewCongcudungcuFormGroupInput for create.
 */
type CongcudungcuFormGroupInput = ICongcudungcu | PartialWithRequiredKeyOf<NewCongcudungcu>;

type CongcudungcuFormDefaults = Pick<NewCongcudungcu, 'id'>;

type CongcudungcuFormGroupContent = {
  id: FormControl<ICongcudungcu['id'] | NewCongcudungcu['id']>;
  ma_ccdc: FormControl<ICongcudungcu['ma_ccdc']>;
  ten_ccdc: FormControl<ICongcudungcu['ten_ccdc']>;
  loai_ccdc: FormControl<ICongcudungcu['loai_ccdc']>;
  ly_do_ghi_tang: FormControl<ICongcudungcu['ly_do_ghi_tang']>;
  ngay_ghi_tang: FormControl<ICongcudungcu['ngay_ghi_tang']>;
  so_ct_ghi_tang: FormControl<ICongcudungcu['so_ct_ghi_tang']>;
  so_ky_phan_bo: FormControl<ICongcudungcu['so_ky_phan_bo']>;
  so_ky_pb_con_lai: FormControl<ICongcudungcu['so_ky_pb_con_lai']>;
  dvt: FormControl<ICongcudungcu['dvt']>;
  sl_ghi_tang: FormControl<ICongcudungcu['sl_ghi_tang']>;
  luy_ke_sl_da_giam: FormControl<ICongcudungcu['luy_ke_sl_da_giam']>;
  sl_con_lai: FormControl<ICongcudungcu['sl_con_lai']>;
  gia_tri_ccdc: FormControl<ICongcudungcu['gia_tri_ccdc']>;
  gia_tri_PB_hang_ky: FormControl<ICongcudungcu['gia_tri_PB_hang_ky']>;
  pb_trong_ky: FormControl<ICongcudungcu['pb_trong_ky']>;
  luy_ke_da_pb: FormControl<ICongcudungcu['luy_ke_da_pb']>;
  gia_tri_con_lai: FormControl<ICongcudungcu['gia_tri_con_lai']>;
  tk_cho_phan_bo: FormControl<ICongcudungcu['tk_cho_phan_bo']>;
  createdDate: FormControl<ICongcudungcu['createdDate']>;
  key_uuid: FormControl<ICongcudungcu['key_uuid']>;
};

export type CongcudungcuFormGroup = FormGroup<CongcudungcuFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CongcudungcuFormService {
  createCongcudungcuFormGroup(congcudungcu: CongcudungcuFormGroupInput = { id: null }): CongcudungcuFormGroup {
    const congcudungcuRawValue = {
      ...this.getFormDefaults(),
      ...congcudungcu,
    };
    return new FormGroup<CongcudungcuFormGroupContent>({
      id: new FormControl(
        { value: congcudungcuRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      ma_ccdc: new FormControl(congcudungcuRawValue.ma_ccdc),
      ten_ccdc: new FormControl(congcudungcuRawValue.ten_ccdc),
      loai_ccdc: new FormControl(congcudungcuRawValue.loai_ccdc),
      ly_do_ghi_tang: new FormControl(congcudungcuRawValue.ly_do_ghi_tang),
      ngay_ghi_tang: new FormControl(congcudungcuRawValue.ngay_ghi_tang),
      so_ct_ghi_tang: new FormControl(congcudungcuRawValue.so_ct_ghi_tang),
      so_ky_phan_bo: new FormControl(congcudungcuRawValue.so_ky_phan_bo),
      so_ky_pb_con_lai: new FormControl(congcudungcuRawValue.so_ky_pb_con_lai),
      dvt: new FormControl(congcudungcuRawValue.dvt),
      sl_ghi_tang: new FormControl(congcudungcuRawValue.sl_ghi_tang),
      luy_ke_sl_da_giam: new FormControl(congcudungcuRawValue.luy_ke_sl_da_giam),
      sl_con_lai: new FormControl(congcudungcuRawValue.sl_con_lai),
      gia_tri_ccdc: new FormControl(congcudungcuRawValue.gia_tri_ccdc),
      gia_tri_PB_hang_ky: new FormControl(congcudungcuRawValue.gia_tri_PB_hang_ky),
      pb_trong_ky: new FormControl(congcudungcuRawValue.pb_trong_ky),
      luy_ke_da_pb: new FormControl(congcudungcuRawValue.luy_ke_da_pb),
      gia_tri_con_lai: new FormControl(congcudungcuRawValue.gia_tri_con_lai),
      tk_cho_phan_bo: new FormControl(congcudungcuRawValue.tk_cho_phan_bo),
      createdDate: new FormControl(congcudungcuRawValue.createdDate),
      key_uuid: new FormControl(congcudungcuRawValue.key_uuid),
    });
  }

  getCongcudungcu(form: CongcudungcuFormGroup): ICongcudungcu | NewCongcudungcu {
    return form.getRawValue() as ICongcudungcu | NewCongcudungcu;
  }

  resetForm(form: CongcudungcuFormGroup, congcudungcu: CongcudungcuFormGroupInput): void {
    const congcudungcuRawValue = { ...this.getFormDefaults(), ...congcudungcu };
    form.reset(
      {
        ...congcudungcuRawValue,
        id: { value: congcudungcuRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CongcudungcuFormDefaults {
    return {
      id: null,
    };
  }
}
