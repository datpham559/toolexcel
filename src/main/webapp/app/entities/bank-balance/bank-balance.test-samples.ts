import dayjs from 'dayjs/esm';

import { IBankBalance, NewBankBalance } from './bank-balance.model';

export const sampleWithRequiredData: IBankBalance = {
  id: 93664,
};

export const sampleWithPartialData: IBankBalance = {
  id: 45548,
  debt_incurred: 32814,
  created_date: dayjs('2022-12-16'),
};

export const sampleWithFullData: IBankBalance = {
  id: 10958,
  bank_account: 'haptic Programmable',
  bank_name: 'cultivate Salad Administrator',
  branch: 'Bedfordshire',
  opening_balance: 6039,
  debt_incurred: 59204,
  incurred: 5335,
  ending_balance: 86418,
  created_date: dayjs('2022-12-15'),
  keyUUID: 'Tunnel generate',
};

export const sampleWithNewData: NewBankBalance = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
