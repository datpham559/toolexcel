import dayjs from 'dayjs/esm';

import { ITonkhodauky, NewTonkhodauky } from './tonkhodauky.model';

export const sampleWithRequiredData: ITonkhodauky = {
  id: 32538,
};

export const sampleWithPartialData: ITonkhodauky = {
  id: 13816,
  ten_kho: 'withdrawal Personal',
  ma_hang: 'leverage Birr',
  dvt: 'system Response Mouse',
  dau_ky_so_luong: 71894,
  xuat_kho_so_luong: 86288,
  xuat_kho_gia_tri: 44801,
  cuoi_ky_gia_tri: 7772,
  createdDate: dayjs('2022-12-15'),
  key_uuid: 'Wells',
};

export const sampleWithFullData: ITonkhodauky = {
  id: 48120,
  ten_kho: 'markets Money Jewelery',
  ma_hang: 'Route',
  ten_hang: 'azure Specialist',
  dvt: 'Executive withdrawal',
  dau_ky_so_luong: 570,
  dau_ky_gia_tri: 29246,
  nhap_kho_so_luong: 84440,
  nhap_kho_gia_tri: 7484,
  xuat_kho_so_luong: 91862,
  xuat_kho_gia_tri: 3214,
  cuoi_ky_so_luong: 43920,
  cuoi_ky_gia_tri: 21046,
  don_gia_binh_quan: 19107,
  createdDate: dayjs('2022-12-16'),
  key_uuid: 'bluetooth supply-chains copy',
};

export const sampleWithNewData: NewTonkhodauky = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
