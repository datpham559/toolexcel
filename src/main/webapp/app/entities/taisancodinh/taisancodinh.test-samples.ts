import dayjs from 'dayjs/esm';

import { ITaisancodinh, NewTaisancodinh } from './taisancodinh.model';

export const sampleWithRequiredData: ITaisancodinh = {
  id: 50741,
};

export const sampleWithPartialData: ITaisancodinh = {
  id: 94211,
  loai_tscd: 'open-source Table',
  don_vi_su_dung: 'solid',
  ngay_bat_dau_tinh_kh: dayjs('2022-12-16'),
  thoi_gian_su_dung: 38003,
  thoi_gian_su_dung_con_lai: 32770,
  hao_mon_trong_ky: 71962,
  hao_mon_luy_ke: 61439,
  gia_tri_KH_thang: 5313,
  tk_nguyen_gia: 'conglomeration',
  tk_khau_hao: 'digital',
};

export const sampleWithFullData: ITaisancodinh = {
  id: 3693,
  ma_tscd: 'Brand RSS',
  ten_tscd: 'Latvia JSON',
  loai_tscd: 'User-friendly olive communities',
  don_vi_su_dung: 'Administrator',
  ngay_ghi_tang: dayjs('2022-12-15'),
  so_ct_ghi_tang: 'white leverage',
  ngay_bat_dau_tinh_kh: dayjs('2022-12-15'),
  thoi_gian_su_dung: 22729,
  thoi_gian_su_dung_con_lai: 37310,
  nguyen_gia: 64507,
  gia_tri_tinh_kh: 68863,
  hao_mon_trong_ky: 60066,
  hao_mon_luy_ke: 31870,
  gia_tri_con_lai: 12416,
  gia_tri_KH_thang: 55816,
  tk_nguyen_gia: 'Marketing Landing Croatian',
  tk_khau_hao: 'Concrete parallelism',
  createdDate: dayjs('2022-12-15'),
  key_uuid: 'Computer withdrawal relationships',
};

export const sampleWithNewData: NewTaisancodinh = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
