import dayjs from 'dayjs/esm';

export interface ICongnophaithu {
  id: number;
  makh?: string | null;
  tenkh?: string | null;
  tkcongno?: string | null;
  sodunodauky?: number | null;
  soducodauky?: number | null;
  sonophatsinh?: number | null;
  socophatsinh?: number | null;
  sodunocuoiky?: number | null;
  soducocuoiky?: number | null;
  createdDate?: dayjs.Dayjs | null;
  key_uuid?: string | null;
}

export type NewCongnophaithu = Omit<ICongnophaithu, 'id'> & { id: null };
