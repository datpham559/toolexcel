import dayjs from 'dayjs/esm';

export interface IMerchandise {
  id: number;
  code?: string | null;
  name?: string | null;
  nature?: string | null;
  group_vthh?: string | null;
  describe?: string | null;
  explain_buy?: string | null;
  explain_sell?: string | null;
  main_dvt?: string | null;
  warranty_period?: string | null;
  quantity_inventory?: number | null;
  source?: string | null;
  implicitly_repository?: string | null;
  warehouse_account?: string | null;
  expense_account?: string | null;
  income_account?: string | null;
  discount_account?: string | null;
  sale_account?: string | null;
  return_account?: string | null;
  rate_ckmh?: number | null;
  fixed_purchase_price?: number | null;
  latest_purchase_price?: number | null;
  unit_price_sell_1?: number | null;
  unit_price_sell_2?: number | null;
  unit_price_sell_3?: number | null;
  fixed_unit_price?: number | null;
  unit_price_after_tax?: number | null;
  tax_rate_gtgt?: string | null;
  tax_rate_nk?: number | null;
  tax_rate_xk?: number | null;
  group_hhdv_taxable_ttdb?: string | null;
  unfollow?: number | null;
  inventory_number?: number | null;
  characteristic?: string | null;
  inventory_value?: number | null;
  follow?: number | null;
  discount?: number | null;
  from_amount?: string | null;
  to_amount?: string | null;
  percent_discount?: number | null;
  discount_amount?: number | null;
  conversion_unit?: string | null;
  primary_unit_conversion_rate?: number | null;
  calculation?: string | null;
  describe1?: string | null;
  unit_price_1?: number | null;
  unit_price_2?: number | null;
  unit_price_3?: number | null;
  fixed_unit_price1?: number | null;
  material_code?: string | null;
  material_name?: string | null;
  dvt?: string | null;
  amount?: string | null;
  specification_code?: string | null;
  display_name?: string | null;
  allow_same?: string | null;
  created_date?: dayjs.Dayjs | null;
  keyUUID?: string | null;
}

export type NewMerchandise = Omit<IMerchandise, 'id'> & { id: null };