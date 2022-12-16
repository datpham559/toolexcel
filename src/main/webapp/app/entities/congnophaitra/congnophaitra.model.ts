import dayjs from 'dayjs/esm';

export interface ICongnophaitra {
  id: number;
  ma_ncc?: string | null;
  ten_ncc?: string | null;
  tk_congno?: string | null;
  so_du_no_dau_ky?: number | null;
  so_du_co_dau_ky?: number | null;
  phat_sinh_no?: number | null;
  phat_sinh_co?: number | null;
  so_du_no_cuoi_ky?: number | null;
  so_du_co_cuoi_ky?: number | null;
  created_Date?: dayjs.Dayjs | null;
  key_uuid?: string | null;
}

export type NewCongnophaitra = Omit<ICongnophaitra, 'id'> & { id: null };
