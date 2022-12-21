import dayjs from 'dayjs/esm';

export interface IDmNCC {
  id: number;
  supplier_code?: string | null;
  supplier_name?: string | null;
  address?: string | null;
  group_kh_ncc?: string | null;
  tax_code?: string | null;
  phone_number?: string | null;
  unfollow?: boolean | null;
  created_date?: dayjs.Dayjs | null;
  keyUUID?: string | null;
}

export type NewDmNCC = Omit<IDmNCC, 'id'> & { id: null };
