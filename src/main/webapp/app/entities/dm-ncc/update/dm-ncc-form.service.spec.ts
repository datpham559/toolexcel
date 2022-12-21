import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../dm-ncc.test-samples';

import { DmNCCFormService } from './dm-ncc-form.service';

describe('DmNCC Form Service', () => {
  let service: DmNCCFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DmNCCFormService);
  });

  describe('Service methods', () => {
    describe('createDmNCCFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createDmNCCFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            supplier_code: expect.any(Object),
            supplier_name: expect.any(Object),
            address: expect.any(Object),
            group_kh_ncc: expect.any(Object),
            tax_code: expect.any(Object),
            phone_number: expect.any(Object),
            unfollow: expect.any(Object),
            created_date: expect.any(Object),
            keyUUID: expect.any(Object),
          })
        );
      });

      it('passing IDmNCC should create a new form with FormGroup', () => {
        const formGroup = service.createDmNCCFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            supplier_code: expect.any(Object),
            supplier_name: expect.any(Object),
            address: expect.any(Object),
            group_kh_ncc: expect.any(Object),
            tax_code: expect.any(Object),
            phone_number: expect.any(Object),
            unfollow: expect.any(Object),
            created_date: expect.any(Object),
            keyUUID: expect.any(Object),
          })
        );
      });
    });

    describe('getDmNCC', () => {
      it('should return NewDmNCC for default DmNCC initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createDmNCCFormGroup(sampleWithNewData);

        const dmNCC = service.getDmNCC(formGroup) as any;

        expect(dmNCC).toMatchObject(sampleWithNewData);
      });

      it('should return NewDmNCC for empty DmNCC initial value', () => {
        const formGroup = service.createDmNCCFormGroup();

        const dmNCC = service.getDmNCC(formGroup) as any;

        expect(dmNCC).toMatchObject({});
      });

      it('should return IDmNCC', () => {
        const formGroup = service.createDmNCCFormGroup(sampleWithRequiredData);

        const dmNCC = service.getDmNCC(formGroup) as any;

        expect(dmNCC).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IDmNCC should not enable id FormControl', () => {
        const formGroup = service.createDmNCCFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewDmNCC should disable id FormControl', () => {
        const formGroup = service.createDmNCCFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
