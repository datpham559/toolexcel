import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../beginning-inventory.test-samples';

import { BeginningInventoryFormService } from './beginning-inventory-form.service';

describe('BeginningInventory Form Service', () => {
  let service: BeginningInventoryFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BeginningInventoryFormService);
  });

  describe('Service methods', () => {
    describe('createBeginningInventoryFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createBeginningInventoryFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            account_number: expect.any(Object),
            account_name: expect.any(Object),
            first_debt: expect.any(Object),
            first_yes: expect.any(Object),
            debt_arises: expect.any(Object),
            arises_yes: expect.any(Object),
            accumulated_debt: expect.any(Object),
            accumulated_yes: expect.any(Object),
            last_debt: expect.any(Object),
            last_yes: expect.any(Object),
            created_date: expect.any(Object),
            keyUUID: expect.any(Object),
          })
        );
      });

      it('passing IBeginningInventory should create a new form with FormGroup', () => {
        const formGroup = service.createBeginningInventoryFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            account_number: expect.any(Object),
            account_name: expect.any(Object),
            first_debt: expect.any(Object),
            first_yes: expect.any(Object),
            debt_arises: expect.any(Object),
            arises_yes: expect.any(Object),
            accumulated_debt: expect.any(Object),
            accumulated_yes: expect.any(Object),
            last_debt: expect.any(Object),
            last_yes: expect.any(Object),
            created_date: expect.any(Object),
            keyUUID: expect.any(Object),
          })
        );
      });
    });

    describe('getBeginningInventory', () => {
      it('should return NewBeginningInventory for default BeginningInventory initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createBeginningInventoryFormGroup(sampleWithNewData);

        const beginningInventory = service.getBeginningInventory(formGroup) as any;

        expect(beginningInventory).toMatchObject(sampleWithNewData);
      });

      it('should return NewBeginningInventory for empty BeginningInventory initial value', () => {
        const formGroup = service.createBeginningInventoryFormGroup();

        const beginningInventory = service.getBeginningInventory(formGroup) as any;

        expect(beginningInventory).toMatchObject({});
      });

      it('should return IBeginningInventory', () => {
        const formGroup = service.createBeginningInventoryFormGroup(sampleWithRequiredData);

        const beginningInventory = service.getBeginningInventory(formGroup) as any;

        expect(beginningInventory).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IBeginningInventory should not enable id FormControl', () => {
        const formGroup = service.createBeginningInventoryFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewBeginningInventory should disable id FormControl', () => {
        const formGroup = service.createBeginningInventoryFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
