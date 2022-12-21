import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../congnophaitra.test-samples';

import { CongnophaitraFormService } from './congnophaitra-form.service';

describe('Congnophaitra Form Service', () => {
  let service: CongnophaitraFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CongnophaitraFormService);
  });

  describe('Service methods', () => {
    describe('createCongnophaitraFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCongnophaitraFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            ma_ncc: expect.any(Object),
            ten_ncc: expect.any(Object),
            tk_congno: expect.any(Object),
            so_du_no_dau_ky: expect.any(Object),
            so_du_co_dau_ky: expect.any(Object),
            phat_sinh_no: expect.any(Object),
            phat_sinh_co: expect.any(Object),
            so_du_no_cuoi_ky: expect.any(Object),
            so_du_co_cuoi_ky: expect.any(Object),
            created_Date: expect.any(Object),
            key_uuid: expect.any(Object),
          })
        );
      });

      it('passing ICongnophaitra should create a new form with FormGroup', () => {
        const formGroup = service.createCongnophaitraFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            ma_ncc: expect.any(Object),
            ten_ncc: expect.any(Object),
            tk_congno: expect.any(Object),
            so_du_no_dau_ky: expect.any(Object),
            so_du_co_dau_ky: expect.any(Object),
            phat_sinh_no: expect.any(Object),
            phat_sinh_co: expect.any(Object),
            so_du_no_cuoi_ky: expect.any(Object),
            so_du_co_cuoi_ky: expect.any(Object),
            created_Date: expect.any(Object),
            key_uuid: expect.any(Object),
          })
        );
      });
    });

    describe('getCongnophaitra', () => {
      it('should return NewCongnophaitra for default Congnophaitra initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createCongnophaitraFormGroup(sampleWithNewData);

        const congnophaitra = service.getCongnophaitra(formGroup) as any;

        expect(congnophaitra).toMatchObject(sampleWithNewData);
      });

      it('should return NewCongnophaitra for empty Congnophaitra initial value', () => {
        const formGroup = service.createCongnophaitraFormGroup();

        const congnophaitra = service.getCongnophaitra(formGroup) as any;

        expect(congnophaitra).toMatchObject({});
      });

      it('should return ICongnophaitra', () => {
        const formGroup = service.createCongnophaitraFormGroup(sampleWithRequiredData);

        const congnophaitra = service.getCongnophaitra(formGroup) as any;

        expect(congnophaitra).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICongnophaitra should not enable id FormControl', () => {
        const formGroup = service.createCongnophaitraFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCongnophaitra should disable id FormControl', () => {
        const formGroup = service.createCongnophaitraFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
