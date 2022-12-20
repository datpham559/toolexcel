import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../synthetic.test-samples';

import { SyntheticFormService } from './synthetic-form.service';

describe('Synthetic Form Service', () => {
  let service: SyntheticFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SyntheticFormService);
  });

  describe('Service methods', () => {
    describe('createSyntheticFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSyntheticFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            voucherType: expect.any(Object),
            voucherTypeNo: expect.any(Object),
            voucherNo: expect.any(Object),
            voucherDate: expect.any(Object),
            accountingDate: expect.any(Object),
            invoiceNo: expect.any(Object),
            invoiceDate: expect.any(Object),
            debitAccount: expect.any(Object),
            creditAccount: expect.any(Object),
            currencyType: expect.any(Object),
            currency: expect.any(Object),
            materialGoodCode: expect.any(Object),
            materialGoodName: expect.any(Object),
            storageIn: expect.any(Object),
            storageOut: expect.any(Object),
            caculationUnit: expect.any(Object),
            amount: expect.any(Object),
            price: expect.any(Object),
            tranferRate: expect.any(Object),
            moneyTranfer: expect.any(Object),
            fixedAssetsType: expect.any(Object),
            fixedAssetsCode: expect.any(Object),
            toolsCode: expect.any(Object),
            debitObject: expect.any(Object),
            creditObject: expect.any(Object),
            unit: expect.any(Object),
            employee: expect.any(Object),
            bankAccount: expect.any(Object),
            itemCost: expect.any(Object),
            construction: expect.any(Object),
            costSet: expect.any(Object),
            purchaseOrder: expect.any(Object),
            buyOrder: expect.any(Object),
            purchaseContract: expect.any(Object),
            saleContract: expect.any(Object),
            statsCode: expect.any(Object),
            explanation: expect.any(Object),
            explanationDetail: expect.any(Object),
            recordStatus: expect.any(Object),
            createdDate: expect.any(Object),
            keyUUID: expect.any(Object),
          })
        );
      });

      it('passing ISynthetic should create a new form with FormGroup', () => {
        const formGroup = service.createSyntheticFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            voucherType: expect.any(Object),
            voucherTypeNo: expect.any(Object),
            voucherNo: expect.any(Object),
            voucherDate: expect.any(Object),
            accountingDate: expect.any(Object),
            invoiceNo: expect.any(Object),
            invoiceDate: expect.any(Object),
            debitAccount: expect.any(Object),
            creditAccount: expect.any(Object),
            currencyType: expect.any(Object),
            currency: expect.any(Object),
            materialGoodCode: expect.any(Object),
            materialGoodName: expect.any(Object),
            storageIn: expect.any(Object),
            storageOut: expect.any(Object),
            caculationUnit: expect.any(Object),
            amount: expect.any(Object),
            price: expect.any(Object),
            tranferRate: expect.any(Object),
            moneyTranfer: expect.any(Object),
            fixedAssetsType: expect.any(Object),
            fixedAssetsCode: expect.any(Object),
            toolsCode: expect.any(Object),
            debitObject: expect.any(Object),
            creditObject: expect.any(Object),
            unit: expect.any(Object),
            employee: expect.any(Object),
            bankAccount: expect.any(Object),
            itemCost: expect.any(Object),
            construction: expect.any(Object),
            costSet: expect.any(Object),
            purchaseOrder: expect.any(Object),
            buyOrder: expect.any(Object),
            purchaseContract: expect.any(Object),
            saleContract: expect.any(Object),
            statsCode: expect.any(Object),
            explanation: expect.any(Object),
            explanationDetail: expect.any(Object),
            recordStatus: expect.any(Object),
            createdDate: expect.any(Object),
            keyUUID: expect.any(Object),
          })
        );
      });
    });

    describe('getSynthetic', () => {
      it('should return NewSynthetic for default Synthetic initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createSyntheticFormGroup(sampleWithNewData);

        const synthetic = service.getSynthetic(formGroup) as any;

        expect(synthetic).toMatchObject(sampleWithNewData);
      });

      it('should return NewSynthetic for empty Synthetic initial value', () => {
        const formGroup = service.createSyntheticFormGroup();

        const synthetic = service.getSynthetic(formGroup) as any;

        expect(synthetic).toMatchObject({});
      });

      it('should return ISynthetic', () => {
        const formGroup = service.createSyntheticFormGroup(sampleWithRequiredData);

        const synthetic = service.getSynthetic(formGroup) as any;

        expect(synthetic).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISynthetic should not enable id FormControl', () => {
        const formGroup = service.createSyntheticFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSynthetic should disable id FormControl', () => {
        const formGroup = service.createSyntheticFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
