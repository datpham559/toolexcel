import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../congcudungcu.test-samples';

import { CongcudungcuFormService } from './congcudungcu-form.service';

describe('Congcudungcu Form Service', () => {
  let service: CongcudungcuFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CongcudungcuFormService);
  });

  describe('Service methods', () => {
    describe('createCongcudungcuFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCongcudungcuFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            ma_ccdc: expect.any(Object),
            ten_ccdc: expect.any(Object),
            loai_ccdc: expect.any(Object),
            ly_do_ghi_tang: expect.any(Object),
            ngay_ghi_tang: expect.any(Object),
            so_ct_ghi_tang: expect.any(Object),
            so_ky_phan_bo: expect.any(Object),
            so_ky_pb_con_lai: expect.any(Object),
            dvt: expect.any(Object),
            sl_ghi_tang: expect.any(Object),
            luy_ke_sl_da_giam: expect.any(Object),
            sl_con_lai: expect.any(Object),
            gia_tri_ccdc: expect.any(Object),
            gia_tri_PB_hang_ky: expect.any(Object),
            pb_trong_ky: expect.any(Object),
            luy_ke_da_pb: expect.any(Object),
            gia_tri_con_lai: expect.any(Object),
            tk_cho_phan_bo: expect.any(Object),
            createdDate: expect.any(Object),
            key_uuid: expect.any(Object),
          })
        );
      });

      it('passing ICongcudungcu should create a new form with FormGroup', () => {
        const formGroup = service.createCongcudungcuFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            ma_ccdc: expect.any(Object),
            ten_ccdc: expect.any(Object),
            loai_ccdc: expect.any(Object),
            ly_do_ghi_tang: expect.any(Object),
            ngay_ghi_tang: expect.any(Object),
            so_ct_ghi_tang: expect.any(Object),
            so_ky_phan_bo: expect.any(Object),
            so_ky_pb_con_lai: expect.any(Object),
            dvt: expect.any(Object),
            sl_ghi_tang: expect.any(Object),
            luy_ke_sl_da_giam: expect.any(Object),
            sl_con_lai: expect.any(Object),
            gia_tri_ccdc: expect.any(Object),
            gia_tri_PB_hang_ky: expect.any(Object),
            pb_trong_ky: expect.any(Object),
            luy_ke_da_pb: expect.any(Object),
            gia_tri_con_lai: expect.any(Object),
            tk_cho_phan_bo: expect.any(Object),
            createdDate: expect.any(Object),
            key_uuid: expect.any(Object),
          })
        );
      });
    });

    describe('getCongcudungcu', () => {
      it('should return NewCongcudungcu for default Congcudungcu initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createCongcudungcuFormGroup(sampleWithNewData);

        const congcudungcu = service.getCongcudungcu(formGroup) as any;

        expect(congcudungcu).toMatchObject(sampleWithNewData);
      });

      it('should return NewCongcudungcu for empty Congcudungcu initial value', () => {
        const formGroup = service.createCongcudungcuFormGroup();

        const congcudungcu = service.getCongcudungcu(formGroup) as any;

        expect(congcudungcu).toMatchObject({});
      });

      it('should return ICongcudungcu', () => {
        const formGroup = service.createCongcudungcuFormGroup(sampleWithRequiredData);

        const congcudungcu = service.getCongcudungcu(formGroup) as any;

        expect(congcudungcu).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICongcudungcu should not enable id FormControl', () => {
        const formGroup = service.createCongcudungcuFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCongcudungcu should disable id FormControl', () => {
        const formGroup = service.createCongcudungcuFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
