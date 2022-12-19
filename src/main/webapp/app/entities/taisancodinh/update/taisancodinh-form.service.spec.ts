import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../taisancodinh.test-samples';

import { TaisancodinhFormService } from './taisancodinh-form.service';

describe('Taisancodinh Form Service', () => {
  let service: TaisancodinhFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TaisancodinhFormService);
  });

  describe('Service methods', () => {
    describe('createTaisancodinhFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createTaisancodinhFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            ma_tscd: expect.any(Object),
            ten_tscd: expect.any(Object),
            loai_tscd: expect.any(Object),
            don_vi_su_dung: expect.any(Object),
            ngay_ghi_tang: expect.any(Object),
            so_ct_ghi_tang: expect.any(Object),
            ngay_bat_dau_tinh_kh: expect.any(Object),
            thoi_gian_su_dung: expect.any(Object),
            thoi_gian_su_dung_con_lai: expect.any(Object),
            nguyen_gia: expect.any(Object),
            gia_tri_tinh_kh: expect.any(Object),
            hao_mon_trong_ky: expect.any(Object),
            hao_mon_luy_ke: expect.any(Object),
            gia_tri_con_lai: expect.any(Object),
            gia_tri_KH_thang: expect.any(Object),
            tk_nguyen_gia: expect.any(Object),
            tk_khau_hao: expect.any(Object),
            createdDate: expect.any(Object),
            key_uuid: expect.any(Object),
          })
        );
      });

      it('passing ITaisancodinh should create a new form with FormGroup', () => {
        const formGroup = service.createTaisancodinhFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            ma_tscd: expect.any(Object),
            ten_tscd: expect.any(Object),
            loai_tscd: expect.any(Object),
            don_vi_su_dung: expect.any(Object),
            ngay_ghi_tang: expect.any(Object),
            so_ct_ghi_tang: expect.any(Object),
            ngay_bat_dau_tinh_kh: expect.any(Object),
            thoi_gian_su_dung: expect.any(Object),
            thoi_gian_su_dung_con_lai: expect.any(Object),
            nguyen_gia: expect.any(Object),
            gia_tri_tinh_kh: expect.any(Object),
            hao_mon_trong_ky: expect.any(Object),
            hao_mon_luy_ke: expect.any(Object),
            gia_tri_con_lai: expect.any(Object),
            gia_tri_KH_thang: expect.any(Object),
            tk_nguyen_gia: expect.any(Object),
            tk_khau_hao: expect.any(Object),
            createdDate: expect.any(Object),
            key_uuid: expect.any(Object),
          })
        );
      });
    });

    describe('getTaisancodinh', () => {
      it('should return NewTaisancodinh for default Taisancodinh initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createTaisancodinhFormGroup(sampleWithNewData);

        const taisancodinh = service.getTaisancodinh(formGroup) as any;

        expect(taisancodinh).toMatchObject(sampleWithNewData);
      });

      it('should return NewTaisancodinh for empty Taisancodinh initial value', () => {
        const formGroup = service.createTaisancodinhFormGroup();

        const taisancodinh = service.getTaisancodinh(formGroup) as any;

        expect(taisancodinh).toMatchObject({});
      });

      it('should return ITaisancodinh', () => {
        const formGroup = service.createTaisancodinhFormGroup(sampleWithRequiredData);

        const taisancodinh = service.getTaisancodinh(formGroup) as any;

        expect(taisancodinh).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ITaisancodinh should not enable id FormControl', () => {
        const formGroup = service.createTaisancodinhFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewTaisancodinh should disable id FormControl', () => {
        const formGroup = service.createTaisancodinhFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
