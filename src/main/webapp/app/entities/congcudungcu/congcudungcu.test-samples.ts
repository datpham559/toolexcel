import dayjs from 'dayjs/esm';

import { ICongcudungcu, NewCongcudungcu } from './congcudungcu.model';

export const sampleWithRequiredData: ICongcudungcu = {
  id: 97864,
};

export const sampleWithPartialData: ICongcudungcu = {
  id: 82911,
  loai_ccdc: 'SQL',
  so_ct_ghi_tang: 'Cloned',
  sl_ghi_tang: 66445,
  gia_tri_PB_hang_ky: 32290,
  luy_ke_da_pb: 90469,
  gia_tri_con_lai: 3439,
  tk_cho_phan_bo: 'National fuchsia',
  createdDate: dayjs('2022-12-16'),
  key_uuid: 'optical withdrawal Jordan',
};

export const sampleWithFullData: ICongcudungcu = {
  id: 15178,
  ma_ccdc: 'Buckinghamshire Lodge',
  ten_ccdc: 'deposit e-services',
  loai_ccdc: 'mesh Rupee',
  ly_do_ghi_tang: 'hacking Tuna',
  ngay_ghi_tang: dayjs('2022-12-16'),
  so_ct_ghi_tang: 'Pizza',
  so_ky_phan_bo: 42585,
  so_ky_pb_con_lai: 51193,
  dvt: 'mindshare',
  sl_ghi_tang: 49299,
  luy_ke_sl_da_giam: 29783,
  sl_con_lai: 89567,
  gia_tri_ccdc: 4995,
  gia_tri_PB_hang_ky: 32236,
  pb_trong_ky: 83430,
  luy_ke_da_pb: 76944,
  gia_tri_con_lai: 10569,
  tk_cho_phan_bo: 'Rubber Account Internal',
  createdDate: dayjs('2022-12-15'),
  key_uuid: 'invoice Mauritius',
};

export const sampleWithNewData: NewCongcudungcu = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
