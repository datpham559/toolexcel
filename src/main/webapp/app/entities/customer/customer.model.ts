import dayjs from 'dayjs/esm';

export interface ICustomer {
  id: number;
  customerCode?: string | null;
  customerName?: string | null;
  address?: string | null;
  customerGroup?: string | null;
  tax?: string | null;
  phoneNumber?: string | null;
  unfollow?: string | null;
  createdDate?: dayjs.Dayjs | null;
  keyUUID?: string | null;
}

export type NewCustomer = Omit<ICustomer, 'id'> & { id: null };
