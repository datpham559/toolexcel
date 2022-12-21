import dayjs from 'dayjs/esm';

export interface ICongcudungcu {
  id: number;
  ma_ccdc?: string | null;
  ten_ccdc?: string | null;
  loai_ccdc?: string | null;
  ly_do_ghi_tang?: string | null;
  ngay_ghi_tang?: dayjs.Dayjs | null;
  so_ct_ghi_tang?: string | null;
  so_ky_phan_bo?: number | null;
  so_ky_pb_con_lai?: number | null;
  dvt?: string | null;
  sl_ghi_tang?: number | null;
  luy_ke_sl_da_giam?: number | null;
  sl_con_lai?: number | null;
  gia_tri_ccdc?: number | null;
  gia_tri_PB_hang_ky?: number | null;
  pb_trong_ky?: number | null;
  luy_ke_da_pb?: number | null;
  gia_tri_con_lai?: number | null;
  tk_cho_phan_bo?: string | null;
  createdDate?: dayjs.Dayjs | null;
  key_uuid?: string | null;
}

export type NewCongcudungcu = Omit<ICongcudungcu, 'id'> & { id: null };
