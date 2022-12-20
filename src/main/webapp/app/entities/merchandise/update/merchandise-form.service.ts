import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IMerchandise, NewMerchandise } from '../merchandise.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IMerchandise for edit and NewMerchandiseFormGroupInput for create.
 */
type MerchandiseFormGroupInput = IMerchandise | PartialWithRequiredKeyOf<NewMerchandise>;

type MerchandiseFormDefaults = Pick<NewMerchandise, 'id'>;

type MerchandiseFormGroupContent = {
  id: FormControl<IMerchandise['id'] | NewMerchandise['id']>;
  code: FormControl<IMerchandise['code']>;
  name: FormControl<IMerchandise['name']>;
  nature: FormControl<IMerchandise['nature']>;
  group_vthh: FormControl<IMerchandise['group_vthh']>;
  describe: FormControl<IMerchandise['describe']>;
  explain_buy: FormControl<IMerchandise['explain_buy']>;
  explain_sell: FormControl<IMerchandise['explain_sell']>;
  main_dvt: FormControl<IMerchandise['main_dvt']>;
  warranty_period: FormControl<IMerchandise['warranty_period']>;
  quantity_inventory: FormControl<IMerchandise['quantity_inventory']>;
  source: FormControl<IMerchandise['source']>;
  implicitly_repository: FormControl<IMerchandise['implicitly_repository']>;
  warehouse_account: FormControl<IMerchandise['warehouse_account']>;
  expense_account: FormControl<IMerchandise['expense_account']>;
  income_account: FormControl<IMerchandise['income_account']>;
  discount_account: FormControl<IMerchandise['discount_account']>;
  sale_account: FormControl<IMerchandise['sale_account']>;
  return_account: FormControl<IMerchandise['return_account']>;
  rate_ckmh: FormControl<IMerchandise['rate_ckmh']>;
  fixed_purchase_price: FormControl<IMerchandise['fixed_purchase_price']>;
  latest_purchase_price: FormControl<IMerchandise['latest_purchase_price']>;
  unit_price_sell_1: FormControl<IMerchandise['unit_price_sell_1']>;
  unit_price_sell_2: FormControl<IMerchandise['unit_price_sell_2']>;
  unit_price_sell_3: FormControl<IMerchandise['unit_price_sell_3']>;
  fixed_unit_price: FormControl<IMerchandise['fixed_unit_price']>;
  unit_price_after_tax: FormControl<IMerchandise['unit_price_after_tax']>;
  tax_rate_gtgt: FormControl<IMerchandise['tax_rate_gtgt']>;
  tax_rate_nk: FormControl<IMerchandise['tax_rate_nk']>;
  tax_rate_xk: FormControl<IMerchandise['tax_rate_xk']>;
  group_hhdv_taxable_ttdb: FormControl<IMerchandise['group_hhdv_taxable_ttdb']>;
  unfollow: FormControl<IMerchandise['unfollow']>;
  inventory_number: FormControl<IMerchandise['inventory_number']>;
  characteristic: FormControl<IMerchandise['characteristic']>;
  inventory_value: FormControl<IMerchandise['inventory_value']>;
  follow: FormControl<IMerchandise['follow']>;
  discount: FormControl<IMerchandise['discount']>;
  from_amount: FormControl<IMerchandise['from_amount']>;
  to_amount: FormControl<IMerchandise['to_amount']>;
  percent_discount: FormControl<IMerchandise['percent_discount']>;
  discount_amount: FormControl<IMerchandise['discount_amount']>;
  conversion_unit: FormControl<IMerchandise['conversion_unit']>;
  primary_unit_conversion_rate: FormControl<IMerchandise['primary_unit_conversion_rate']>;
  calculation: FormControl<IMerchandise['calculation']>;
  describe1: FormControl<IMerchandise['describe1']>;
  unit_price_1: FormControl<IMerchandise['unit_price_1']>;
  unit_price_2: FormControl<IMerchandise['unit_price_2']>;
  unit_price_3: FormControl<IMerchandise['unit_price_3']>;
  fixed_unit_price1: FormControl<IMerchandise['fixed_unit_price1']>;
  material_code: FormControl<IMerchandise['material_code']>;
  material_name: FormControl<IMerchandise['material_name']>;
  dvt: FormControl<IMerchandise['dvt']>;
  amount: FormControl<IMerchandise['amount']>;
  specification_code: FormControl<IMerchandise['specification_code']>;
  display_name: FormControl<IMerchandise['display_name']>;
  allow_same: FormControl<IMerchandise['allow_same']>;
  created_date: FormControl<IMerchandise['created_date']>;
  keyUUID: FormControl<IMerchandise['keyUUID']>;
};

export type MerchandiseFormGroup = FormGroup<MerchandiseFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class MerchandiseFormService {
  createMerchandiseFormGroup(merchandise: MerchandiseFormGroupInput = { id: null }): MerchandiseFormGroup {
    const merchandiseRawValue = {
      ...this.getFormDefaults(),
      ...merchandise,
    };
    return new FormGroup<MerchandiseFormGroupContent>({
      id: new FormControl(
        { value: merchandiseRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      code: new FormControl(merchandiseRawValue.code),
      name: new FormControl(merchandiseRawValue.name),
      nature: new FormControl(merchandiseRawValue.nature),
      group_vthh: new FormControl(merchandiseRawValue.group_vthh),
      describe: new FormControl(merchandiseRawValue.describe),
      explain_buy: new FormControl(merchandiseRawValue.explain_buy),
      explain_sell: new FormControl(merchandiseRawValue.explain_sell),
      main_dvt: new FormControl(merchandiseRawValue.main_dvt),
      warranty_period: new FormControl(merchandiseRawValue.warranty_period),
      quantity_inventory: new FormControl(merchandiseRawValue.quantity_inventory),
      source: new FormControl(merchandiseRawValue.source),
      implicitly_repository: new FormControl(merchandiseRawValue.implicitly_repository),
      warehouse_account: new FormControl(merchandiseRawValue.warehouse_account),
      expense_account: new FormControl(merchandiseRawValue.expense_account),
      income_account: new FormControl(merchandiseRawValue.income_account),
      discount_account: new FormControl(merchandiseRawValue.discount_account),
      sale_account: new FormControl(merchandiseRawValue.sale_account),
      return_account: new FormControl(merchandiseRawValue.return_account),
      rate_ckmh: new FormControl(merchandiseRawValue.rate_ckmh),
      fixed_purchase_price: new FormControl(merchandiseRawValue.fixed_purchase_price),
      latest_purchase_price: new FormControl(merchandiseRawValue.latest_purchase_price),
      unit_price_sell_1: new FormControl(merchandiseRawValue.unit_price_sell_1),
      unit_price_sell_2: new FormControl(merchandiseRawValue.unit_price_sell_2),
      unit_price_sell_3: new FormControl(merchandiseRawValue.unit_price_sell_3),
      fixed_unit_price: new FormControl(merchandiseRawValue.fixed_unit_price),
      unit_price_after_tax: new FormControl(merchandiseRawValue.unit_price_after_tax),
      tax_rate_gtgt: new FormControl(merchandiseRawValue.tax_rate_gtgt),
      tax_rate_nk: new FormControl(merchandiseRawValue.tax_rate_nk),
      tax_rate_xk: new FormControl(merchandiseRawValue.tax_rate_xk),
      group_hhdv_taxable_ttdb: new FormControl(merchandiseRawValue.group_hhdv_taxable_ttdb),
      unfollow: new FormControl(merchandiseRawValue.unfollow),
      inventory_number: new FormControl(merchandiseRawValue.inventory_number),
      characteristic: new FormControl(merchandiseRawValue.characteristic),
      inventory_value: new FormControl(merchandiseRawValue.inventory_value),
      follow: new FormControl(merchandiseRawValue.follow),
      discount: new FormControl(merchandiseRawValue.discount),
      from_amount: new FormControl(merchandiseRawValue.from_amount),
      to_amount: new FormControl(merchandiseRawValue.to_amount),
      percent_discount: new FormControl(merchandiseRawValue.percent_discount),
      discount_amount: new FormControl(merchandiseRawValue.discount_amount),
      conversion_unit: new FormControl(merchandiseRawValue.conversion_unit),
      primary_unit_conversion_rate: new FormControl(merchandiseRawValue.primary_unit_conversion_rate),
      calculation: new FormControl(merchandiseRawValue.calculation),
      describe1: new FormControl(merchandiseRawValue.describe1),
      unit_price_1: new FormControl(merchandiseRawValue.unit_price_1),
      unit_price_2: new FormControl(merchandiseRawValue.unit_price_2),
      unit_price_3: new FormControl(merchandiseRawValue.unit_price_3),
      fixed_unit_price1: new FormControl(merchandiseRawValue.fixed_unit_price1),
      material_code: new FormControl(merchandiseRawValue.material_code),
      material_name: new FormControl(merchandiseRawValue.material_name),
      dvt: new FormControl(merchandiseRawValue.dvt),
      amount: new FormControl(merchandiseRawValue.amount),
      specification_code: new FormControl(merchandiseRawValue.specification_code),
      display_name: new FormControl(merchandiseRawValue.display_name),
      allow_same: new FormControl(merchandiseRawValue.allow_same),
      created_date: new FormControl(merchandiseRawValue.created_date),
      keyUUID: new FormControl(merchandiseRawValue.keyUUID),
    });
  }

  getMerchandise(form: MerchandiseFormGroup): IMerchandise | NewMerchandise {
    return form.getRawValue() as IMerchandise | NewMerchandise;
  }

  resetForm(form: MerchandiseFormGroup, merchandise: MerchandiseFormGroupInput): void {
    const merchandiseRawValue = { ...this.getFormDefaults(), ...merchandise };
    form.reset(
      {
        ...merchandiseRawValue,
        id: { value: merchandiseRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): MerchandiseFormDefaults {
    return {
      id: null,
    };
  }
}
