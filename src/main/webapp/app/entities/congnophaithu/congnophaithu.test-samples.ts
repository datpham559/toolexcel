import dayjs from 'dayjs/esm';

import { ICongnophaithu, NewCongnophaithu } from './congnophaithu.model';

export const sampleWithRequiredData: ICongnophaithu = {
  id: 32956,
};

export const sampleWithPartialData: ICongnophaithu = {
  id: 32102,
  makh: 'fuchsia deposit',
  tenkh: 'bypassing Rubber',
  sodunodauky: 75084,
  sonophatsinh: 31554,
  sodunocuoiky: 84767,
  soducocuoiky: 74124,
  createdDate: dayjs('2022-12-15'),
};

export const sampleWithFullData: ICongnophaithu = {
  id: 39205,
  makh: 'Officer Extended Tasty',
  tenkh: 'intelligence',
  tkcongno: 'hack',
  sodunodauky: 8815,
  soducodauky: 39796,
  sonophatsinh: 975,
  socophatsinh: 42953,
  sodunocuoiky: 48523,
  soducocuoiky: 97770,
  createdDate: dayjs('2022-12-15'),
  key_uuid: 'interfaces online',
};

export const sampleWithNewData: NewCongnophaithu = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
