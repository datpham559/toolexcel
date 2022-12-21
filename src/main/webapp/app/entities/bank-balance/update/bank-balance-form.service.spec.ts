import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../bank-balance.test-samples';

import { BankBalanceFormService } from './bank-balance-form.service';

describe('BankBalance Form Service', () => {
  let service: BankBalanceFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BankBalanceFormService);
  });

  describe('Service methods', () => {
    describe('createBankBalanceFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createBankBalanceFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            bank_account: expect.any(Object),
            bank_name: expect.any(Object),
            branch: expect.any(Object),
            opening_balance: expect.any(Object),
            debt_incurred: expect.any(Object),
            incurred: expect.any(Object),
            ending_balance: expect.any(Object),
            created_date: expect.any(Object),
            keyUUID: expect.any(Object),
          })
        );
      });

      it('passing IBankBalance should create a new form with FormGroup', () => {
        const formGroup = service.createBankBalanceFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            bank_account: expect.any(Object),
            bank_name: expect.any(Object),
            branch: expect.any(Object),
            opening_balance: expect.any(Object),
            debt_incurred: expect.any(Object),
            incurred: expect.any(Object),
            ending_balance: expect.any(Object),
            created_date: expect.any(Object),
            keyUUID: expect.any(Object),
          })
        );
      });
    });

    describe('getBankBalance', () => {
      it('should return NewBankBalance for default BankBalance initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createBankBalanceFormGroup(sampleWithNewData);

        const bankBalance = service.getBankBalance(formGroup) as any;

        expect(bankBalance).toMatchObject(sampleWithNewData);
      });

      it('should return NewBankBalance for empty BankBalance initial value', () => {
        const formGroup = service.createBankBalanceFormGroup();

        const bankBalance = service.getBankBalance(formGroup) as any;

        expect(bankBalance).toMatchObject({});
      });

      it('should return IBankBalance', () => {
        const formGroup = service.createBankBalanceFormGroup(sampleWithRequiredData);

        const bankBalance = service.getBankBalance(formGroup) as any;

        expect(bankBalance).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IBankBalance should not enable id FormControl', () => {
        const formGroup = service.createBankBalanceFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewBankBalance should disable id FormControl', () => {
        const formGroup = service.createBankBalanceFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
