import dayjs from 'dayjs/esm';

import { IDmNCC, NewDmNCC } from './dm-ncc.model';

export const sampleWithRequiredData: IDmNCC = {
  id: 28443,
};

export const sampleWithPartialData: IDmNCC = {
  id: 40137,
  address: 'Metrics optical',
  tax_code: 'Response portals',
  created_date: dayjs('2022-12-16'),
  keyUUID: 'Phần',
};

export const sampleWithFullData: IDmNCC = {
  id: 64705,
  supplier_code: 'Loan Executive Colorado',
  supplier_name: 'Global Fresh',
  address: 'Incredible',
  group_kh_ncc: 'Rial multi-byte',
  tax_code: 'web-enabled interface Internal',
  phone_number: 'Plastic Wooden',
  unfollow: true,
  created_date: dayjs('2022-12-15'),
  keyUUID: 'Fresh navigating deposit',
};

export const sampleWithNewData: NewDmNCC = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
