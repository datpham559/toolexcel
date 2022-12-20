import dayjs from 'dayjs/esm';

import { IBeginningInventory, NewBeginningInventory } from './beginning-inventory.model';

export const sampleWithRequiredData: IBeginningInventory = {
  id: 92489,
};

export const sampleWithPartialData: IBeginningInventory = {
  id: 87931,
  account_number: 'Principal well-modulated multi-tasking',
  account_name: 'Auto Loan Account',
  arises_yes: 62684,
  accumulated_debt: 74266,
  last_debt: 90577,
  created_date: dayjs('2022-12-15'),
  keyUUID: 'metrics Buckinghamshire',
};

export const sampleWithFullData: IBeginningInventory = {
  id: 57695,
  account_number: 'Usability withdrawal revolutionize',
  account_name: 'Auto Loan Account',
  first_debt: 83852,
  first_yes: 40362,
  debt_arises: 3795,
  arises_yes: 31626,
  accumulated_debt: 95212,
  accumulated_yes: 29494,
  last_debt: 48518,
  last_yes: 47068,
  created_date: dayjs('2022-12-16'),
  keyUUID: 'facilitate Consultant mobile',
};

export const sampleWithNewData: NewBeginningInventory = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
