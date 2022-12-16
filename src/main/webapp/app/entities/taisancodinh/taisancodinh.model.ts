import dayjs from 'dayjs/esm';

export interface ITaisancodinh {
  id: number;
  ma_tscd?: string | null;
  ten_tscd?: string | null;
  loai_tscd?: string | null;
  don_vi_su_dung?: string | null;
  ngay_ghi_tang?: dayjs.Dayjs | null;
  so_ct_ghi_tang?: string | null;
  ngay_bat_dau_tinh_kh?: dayjs.Dayjs | null;
  thoi_gian_su_dung?: number | null;
  thoi_gian_su_dung_con_lai?: number | null;
  nguyen_gia?: number | null;
  gia_tri_tinh_kh?: number | null;
  hao_mon_trong_ky?: number | null;
  hao_mon_luy_ke?: number | null;
  gia_tri_con_lai?: number | null;
  gia_tri_KH_thang?: number | null;
  tk_nguyen_gia?: string | null;
  tk_khau_hao?: string | null;
  createdDate?: dayjs.Dayjs | null;
  key_uuid?: string | null;
}

export type NewTaisancodinh = Omit<ITaisancodinh, 'id'> & { id: null };
