import dayjs from 'dayjs/esm';

import { ICongnophaitra, NewCongnophaitra } from './congnophaitra.model';

export const sampleWithRequiredData: ICongnophaitra = {
  id: 47465,
};

export const sampleWithPartialData: ICongnophaitra = {
  id: 29071,
  ma_ncc: 'Krone',
  so_du_no_dau_ky: 39220,
  so_du_co_dau_ky: 37517,
  phat_sinh_no: 82235,
  so_du_no_cuoi_ky: 99073,
  created_Date: dayjs('2022-12-15'),
  key_uuid: 'Division Cameroon',
};

export const sampleWithFullData: ICongnophaitra = {
  id: 64680,
  ma_ncc: 'Table Ridge',
  ten_ncc: 'plum multimedia',
  tk_congno: 'Wells',
  so_du_no_dau_ky: 50999,
  so_du_co_dau_ky: 31,
  phat_sinh_no: 32419,
  phat_sinh_co: 76995,
  so_du_no_cuoi_ky: 90891,
  so_du_co_cuoi_ky: 81156,
  created_Date: dayjs('2022-12-15'),
  key_uuid: 'Secured Walk',
};

export const sampleWithNewData: NewCongnophaitra = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
