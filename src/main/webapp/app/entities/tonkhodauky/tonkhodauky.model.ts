import dayjs from 'dayjs/esm';

export interface ITonkhodauky {
  id: number;
  ten_kho?: string | null;
  ma_hang?: string | null;
  ten_hang?: string | null;
  dvt?: string | null;
  dau_ky_so_luong?: number | null;
  dau_ky_gia_tri?: number | null;
  nhap_kho_so_luong?: number | null;
  nhap_kho_gia_tri?: number | null;
  xuat_kho_so_luong?: number | null;
  xuat_kho_gia_tri?: number | null;
  cuoi_ky_so_luong?: number | null;
  cuoi_ky_gia_tri?: number | null;
  don_gia_binh_quan?: number | null;
  createdDate?: dayjs.Dayjs | null;
  key_uuid?: string | null;
}

export type NewTonkhodauky = Omit<ITonkhodauky, 'id'> & { id: null };
