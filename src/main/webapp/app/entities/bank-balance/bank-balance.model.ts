import dayjs from 'dayjs/esm';

export interface IBankBalance {
  id: number;
  bank_account?: string | null;
  bank_name?: string | null;
  branch?: string | null;
  opening_balance?: number | null;
  debt_incurred?: number | null;
  incurred?: number | null;
  ending_balance?: number | null;
  created_date?: dayjs.Dayjs | null;
  keyUUID?: string | null;
}

export type NewBankBalance = Omit<IBankBalance, 'id'> & { id: null };
