import dayjs from 'dayjs/esm';

export interface ISynthetic {
  id: number;
  voucherType?: string | null;
  voucherTypeNo?: number | null;
  voucherNo?: string | null;
  voucherDate?: dayjs.Dayjs | null;
  accountingDate?: dayjs.Dayjs | null;
  invoiceNo?: string | null;
  invoiceDate?: dayjs.Dayjs | null;
  debitAccount?: string | null;
  creditAccount?: string | null;
  currencyType?: string | null;
  currency?: number | null;
  materialGoodCode?: string | null;
  materialGoodName?: string | null;
  storageIn?: string | null;
  storageOut?: string | null;
  caculationUnit?: string | null;
  amount?: number | null;
  price?: number | null;
  tranferRate?: number | null;
  moneyTranfer?: number | null;
  fixedAssetsType?: string | null;
  fixedAssetsCode?: string | null;
  toolsCode?: string | null;
  debitObject?: string | null;
  creditObject?: string | null;
  unit?: string | null;
  employee?: string | null;
  bankAccount?: string | null;
  itemCost?: string | null;
  construction?: string | null;
  costSet?: string | null;
  purchaseOrder?: string | null;
  buyOrder?: string | null;
  purchaseContract?: string | null;
  saleContract?: string | null;
  statsCode?: string | null;
  explanation?: string | null;
  explanationDetail?: string | null;
  recordStatus?: string | null;
  createdDate?: dayjs.Dayjs | null;
  keyUUID?: string | null;
}

export type NewSynthetic = Omit<ISynthetic, 'id'> & { id: null };
