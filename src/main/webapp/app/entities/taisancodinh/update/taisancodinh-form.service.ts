import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ITaisancodinh, NewTaisancodinh } from '../taisancodinh.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ITaisancodinh for edit and NewTaisancodinhFormGroupInput for create.
 */
type TaisancodinhFormGroupInput = ITaisancodinh | PartialWithRequiredKeyOf<NewTaisancodinh>;

type TaisancodinhFormDefaults = Pick<NewTaisancodinh, 'id'>;

type TaisancodinhFormGroupContent = {
  id: FormControl<ITaisancodinh['id'] | NewTaisancodinh['id']>;
  ma_tscd: FormControl<ITaisancodinh['ma_tscd']>;
  ten_tscd: FormControl<ITaisancodinh['ten_tscd']>;
  loai_tscd: FormControl<ITaisancodinh['loai_tscd']>;
  don_vi_su_dung: FormControl<ITaisancodinh['don_vi_su_dung']>;
  ngay_ghi_tang: FormControl<ITaisancodinh['ngay_ghi_tang']>;
  so_ct_ghi_tang: FormControl<ITaisancodinh['so_ct_ghi_tang']>;
  ngay_bat_dau_tinh_kh: FormControl<ITaisancodinh['ngay_bat_dau_tinh_kh']>;
  thoi_gian_su_dung: FormControl<ITaisancodinh['thoi_gian_su_dung']>;
  thoi_gian_su_dung_con_lai: FormControl<ITaisancodinh['thoi_gian_su_dung_con_lai']>;
  nguyen_gia: FormControl<ITaisancodinh['nguyen_gia']>;
  gia_tri_tinh_kh: FormControl<ITaisancodinh['gia_tri_tinh_kh']>;
  hao_mon_trong_ky: FormControl<ITaisancodinh['hao_mon_trong_ky']>;
  hao_mon_luy_ke: FormControl<ITaisancodinh['hao_mon_luy_ke']>;
  gia_tri_con_lai: FormControl<ITaisancodinh['gia_tri_con_lai']>;
  gia_tri_KH_thang: FormControl<ITaisancodinh['gia_tri_KH_thang']>;
  tk_nguyen_gia: FormControl<ITaisancodinh['tk_nguyen_gia']>;
  tk_khau_hao: FormControl<ITaisancodinh['tk_khau_hao']>;
  createdDate: FormControl<ITaisancodinh['createdDate']>;
  key_uuid: FormControl<ITaisancodinh['key_uuid']>;
};

export type TaisancodinhFormGroup = FormGroup<TaisancodinhFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class TaisancodinhFormService {
  createTaisancodinhFormGroup(taisancodinh: TaisancodinhFormGroupInput = { id: null }): TaisancodinhFormGroup {
    const taisancodinhRawValue = {
      ...this.getFormDefaults(),
      ...taisancodinh,
    };
    return new FormGroup<TaisancodinhFormGroupContent>({
      id: new FormControl(
        { value: taisancodinhRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      ma_tscd: new FormControl(taisancodinhRawValue.ma_tscd),
      ten_tscd: new FormControl(taisancodinhRawValue.ten_tscd),
      loai_tscd: new FormControl(taisancodinhRawValue.loai_tscd),
      don_vi_su_dung: new FormControl(taisancodinhRawValue.don_vi_su_dung),
      ngay_ghi_tang: new FormControl(taisancodinhRawValue.ngay_ghi_tang),
      so_ct_ghi_tang: new FormControl(taisancodinhRawValue.so_ct_ghi_tang),
      ngay_bat_dau_tinh_kh: new FormControl(taisancodinhRawValue.ngay_bat_dau_tinh_kh),
      thoi_gian_su_dung: new FormControl(taisancodinhRawValue.thoi_gian_su_dung),
      thoi_gian_su_dung_con_lai: new FormControl(taisancodinhRawValue.thoi_gian_su_dung_con_lai),
      nguyen_gia: new FormControl(taisancodinhRawValue.nguyen_gia),
      gia_tri_tinh_kh: new FormControl(taisancodinhRawValue.gia_tri_tinh_kh),
      hao_mon_trong_ky: new FormControl(taisancodinhRawValue.hao_mon_trong_ky),
      hao_mon_luy_ke: new FormControl(taisancodinhRawValue.hao_mon_luy_ke),
      gia_tri_con_lai: new FormControl(taisancodinhRawValue.gia_tri_con_lai),
      gia_tri_KH_thang: new FormControl(taisancodinhRawValue.gia_tri_KH_thang),
      tk_nguyen_gia: new FormControl(taisancodinhRawValue.tk_nguyen_gia),
      tk_khau_hao: new FormControl(taisancodinhRawValue.tk_khau_hao),
      createdDate: new FormControl(taisancodinhRawValue.createdDate),
      key_uuid: new FormControl(taisancodinhRawValue.key_uuid),
    });
  }

  getTaisancodinh(form: TaisancodinhFormGroup): ITaisancodinh | NewTaisancodinh {
    return form.getRawValue() as ITaisancodinh | NewTaisancodinh;
  }

  resetForm(form: TaisancodinhFormGroup, taisancodinh: TaisancodinhFormGroupInput): void {
    const taisancodinhRawValue = { ...this.getFormDefaults(), ...taisancodinh };
    form.reset(
      {
        ...taisancodinhRawValue,
        id: { value: taisancodinhRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): TaisancodinhFormDefaults {
    return {
      id: null,
    };
  }
}
