import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../merchandise.test-samples';

import { MerchandiseFormService } from './merchandise-form.service';

describe('Merchandise Form Service', () => {
  let service: MerchandiseFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MerchandiseFormService);
  });

  describe('Service methods', () => {
    describe('createMerchandiseFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createMerchandiseFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            name: expect.any(Object),
            nature: expect.any(Object),
            group_vthh: expect.any(Object),
            describe: expect.any(Object),
            explain_buy: expect.any(Object),
            explain_sell: expect.any(Object),
            main_dvt: expect.any(Object),
            warranty_period: expect.any(Object),
            quantity_inventory: expect.any(Object),
            source: expect.any(Object),
            implicitly_repository: expect.any(Object),
            warehouse_account: expect.any(Object),
            expense_account: expect.any(Object),
            income_account: expect.any(Object),
            discount_account: expect.any(Object),
            sale_account: expect.any(Object),
            return_account: expect.any(Object),
            rate_ckmh: expect.any(Object),
            fixed_purchase_price: expect.any(Object),
            latest_purchase_price: expect.any(Object),
            unit_price_sell_1: expect.any(Object),
            unit_price_sell_2: expect.any(Object),
            unit_price_sell_3: expect.any(Object),
            fixed_unit_price: expect.any(Object),
            unit_price_after_tax: expect.any(Object),
            tax_rate_gtgt: expect.any(Object),
            tax_rate_nk: expect.any(Object),
            tax_rate_xk: expect.any(Object),
            group_hhdv_taxable_ttdb: expect.any(Object),
            unfollow: expect.any(Object),
            inventory_number: expect.any(Object),
            characteristic: expect.any(Object),
            inventory_value: expect.any(Object),
            follow: expect.any(Object),
            discount: expect.any(Object),
            from_amount: expect.any(Object),
            to_amount: expect.any(Object),
            percent_discount: expect.any(Object),
            discount_amount: expect.any(Object),
            conversion_unit: expect.any(Object),
            primary_unit_conversion_rate: expect.any(Object),
            calculation: expect.any(Object),
            describe1: expect.any(Object),
            unit_price_1: expect.any(Object),
            unit_price_2: expect.any(Object),
            unit_price_3: expect.any(Object),
            fixed_unit_price1: expect.any(Object),
            material_code: expect.any(Object),
            material_name: expect.any(Object),
            dvt: expect.any(Object),
            amount: expect.any(Object),
            specification_code: expect.any(Object),
            display_name: expect.any(Object),
            allow_same: expect.any(Object),
            created_date: expect.any(Object),
            keyUUID: expect.any(Object),
          })
        );
      });

      it('passing IMerchandise should create a new form with FormGroup', () => {
        const formGroup = service.createMerchandiseFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            name: expect.any(Object),
            nature: expect.any(Object),
            group_vthh: expect.any(Object),
            describe: expect.any(Object),
            explain_buy: expect.any(Object),
            explain_sell: expect.any(Object),
            main_dvt: expect.any(Object),
            warranty_period: expect.any(Object),
            quantity_inventory: expect.any(Object),
            source: expect.any(Object),
            implicitly_repository: expect.any(Object),
            warehouse_account: expect.any(Object),
            expense_account: expect.any(Object),
            income_account: expect.any(Object),
            discount_account: expect.any(Object),
            sale_account: expect.any(Object),
            return_account: expect.any(Object),
            rate_ckmh: expect.any(Object),
            fixed_purchase_price: expect.any(Object),
            latest_purchase_price: expect.any(Object),
            unit_price_sell_1: expect.any(Object),
            unit_price_sell_2: expect.any(Object),
            unit_price_sell_3: expect.any(Object),
            fixed_unit_price: expect.any(Object),
            unit_price_after_tax: expect.any(Object),
            tax_rate_gtgt: expect.any(Object),
            tax_rate_nk: expect.any(Object),
            tax_rate_xk: expect.any(Object),
            group_hhdv_taxable_ttdb: expect.any(Object),
            unfollow: expect.any(Object),
            inventory_number: expect.any(Object),
            characteristic: expect.any(Object),
            inventory_value: expect.any(Object),
            follow: expect.any(Object),
            discount: expect.any(Object),
            from_amount: expect.any(Object),
            to_amount: expect.any(Object),
            percent_discount: expect.any(Object),
            discount_amount: expect.any(Object),
            conversion_unit: expect.any(Object),
            primary_unit_conversion_rate: expect.any(Object),
            calculation: expect.any(Object),
            describe1: expect.any(Object),
            unit_price_1: expect.any(Object),
            unit_price_2: expect.any(Object),
            unit_price_3: expect.any(Object),
            fixed_unit_price1: expect.any(Object),
            material_code: expect.any(Object),
            material_name: expect.any(Object),
            dvt: expect.any(Object),
            amount: expect.any(Object),
            specification_code: expect.any(Object),
            display_name: expect.any(Object),
            allow_same: expect.any(Object),
            created_date: expect.any(Object),
            keyUUID: expect.any(Object),
          })
        );
      });
    });

    describe('getMerchandise', () => {
      it('should return NewMerchandise for default Merchandise initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createMerchandiseFormGroup(sampleWithNewData);

        const merchandise = service.getMerchandise(formGroup) as any;

        expect(merchandise).toMatchObject(sampleWithNewData);
      });

      it('should return NewMerchandise for empty Merchandise initial value', () => {
        const formGroup = service.createMerchandiseFormGroup();

        const merchandise = service.getMerchandise(formGroup) as any;

        expect(merchandise).toMatchObject({});
      });

      it('should return IMerchandise', () => {
        const formGroup = service.createMerchandiseFormGroup(sampleWithRequiredData);

        const merchandise = service.getMerchandise(formGroup) as any;

        expect(merchandise).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IMerchandise should not enable id FormControl', () => {
        const formGroup = service.createMerchandiseFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewMerchandise should disable id FormControl', () => {
        const formGroup = service.createMerchandiseFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
