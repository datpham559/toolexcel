import dayjs from 'dayjs/esm';

export interface IBeginningInventory {
  id: number;
  account_number?: string | null;
  account_name?: string | null;
  first_debt?: number | null;
  first_yes?: number | null;
  debt_arises?: number | null;
  arises_yes?: number | null;
  accumulated_debt?: number | null;
  accumulated_yes?: number | null;
  last_debt?: number | null;
  last_yes?: number | null;
  created_date?: dayjs.Dayjs | null;
  keyUUID?: string | null;
}

export type NewBeginningInventory = Omit<IBeginningInventory, 'id'> & { id: null };
