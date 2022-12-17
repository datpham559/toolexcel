import dayjs from 'dayjs/esm';

import { ISynthetic, NewSynthetic } from './synthetic.model';

export const sampleWithRequiredData: ISynthetic = {
  id: 35799,
};

export const sampleWithPartialData: ISynthetic = {
  id: 17288,
  voucherTypeNo: 83774,
  voucherNo: 'Avon deposit Latvia',
  voucherDate: dayjs('2022-12-15'),
  invoiceNo: 'deliverables Planner Persistent',
  currencyType: 'Consultant connecting interfaces',
  currency: 74220,
  materialGoodCode: 'Table Iceland',
  storageIn: 'Sausages Louisiana Dakota',
  caculationUnit: 'Metal standardization',
  amount: 40502,
  price: 42889,
  tranferRate: 82659,
  moneyTranfer: 85581,
  fixedAssetsCode: 'array',
  toolsCode: 'Solutions Corner Strategist',
  unit: 'Stand-alone',
  purchaseOrder: 'transform',
  buyOrder: 'Secured plum',
  explanationDetail: 'Nevada Organic',
  recordStatus: 'reinvent auxiliary',
  keyUUID: 'Pound Fresh Kids',
};

export const sampleWithFullData: ISynthetic = {
  id: 15028,
  voucherType: 'Vista Alabama',
  voucherTypeNo: 50519,
  voucherNo: 'Intelligent',
  voucherDate: dayjs('2022-12-15'),
  accountingDate: dayjs('2022-12-15'),
  invoiceNo: 'Handmade',
  invoiceDate: dayjs('2022-12-15'),
  debitAccount: 'Saint Specialist Won',
  creditAccount: 'synthesizing',
  currencyType: 'invoice Arkansas feed',
  currency: 39573,
  materialGoodCode: 'Liberian',
  materialGoodName: 'Drives',
  storageIn: 'Manager copying',
  storageOut: 'magnetic Dynamic',
  caculationUnit: 'Small Beauty Center',
  amount: 16076,
  price: 2483,
  tranferRate: 29434,
  moneyTranfer: 31389,
  fixedAssetsType: 'Borders technologies',
  fixedAssetsCode: 'engineer Operative invoice',
  toolsCode: 'eyeballs definition',
  debitObject: 'Small applications',
  creditObject: 'microchip',
  unit: 'Global Nevada tan',
  employee: 'Kwacha invoice modular',
  bankAccount: 'RAM SCSI bypass',
  itemCost: 'Kenya',
  construction: 'Market THX Ariary',
  costSet: 'uniform Jewelery',
  purchaseOrder: 'Frozen Utah',
  buyOrder: 'responsive',
  purchaseContract: 'Barbados Optimized',
  saleContract: 'Enterprise-wide Barbados panel',
  statsCode: 'Account',
  explanation: 'Designer Tasty',
  explanationDetail: 'Forward Direct',
  recordStatus: 'Granite Investment communities',
  createdDate: dayjs('2022-12-15'),
  keyUUID: 'Assimilated Refined',
};

export const sampleWithNewData: NewSynthetic = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
