import dayjs from 'dayjs/esm';

import { ICustomer, NewCustomer } from './customer.model';

export const sampleWithRequiredData: ICustomer = {
  id: 24379,
};

export const sampleWithPartialData: ICustomer = {
  id: 8794,
  customerName: '1080p',
  address: 'Loan generating',
  phoneNumber: 'Generic',
  unfollow: 'innovative THX Đức',
  createdDate: dayjs('2022-12-14'),
};

export const sampleWithFullData: ICustomer = {
  id: 76300,
  customerCode: 'withdrawal Avon',
  customerName: 'Carolina intangible',
  address: 'Awesome',
  customerGroup: 'seize silver',
  tax: 'overriding Incredible Soft',
  phoneNumber: 'unleash program',
  unfollow: 'matrix Investor clicks-and-mortar',
  createdDate: dayjs('2022-12-14'),
  keyUUID: 'Wooden Intelligent Branding',
};

export const sampleWithNewData: NewCustomer = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
