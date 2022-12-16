import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../tonkhodauky.test-samples';

import { TonkhodaukyFormService } from './tonkhodauky-form.service';

describe('Tonkhodauky Form Service', () => {
  let service: TonkhodaukyFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TonkhodaukyFormService);
  });

  describe('Service methods', () => {
    describe('createTonkhodaukyFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createTonkhodaukyFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            ten_kho: expect.any(Object),
            ma_hang: expect.any(Object),
            ten_hang: expect.any(Object),
            dvt: expect.any(Object),
            dau_ky_so_luong: expect.any(Object),
            dau_ky_gia_tri: expect.any(Object),
            nhap_kho_so_luong: expect.any(Object),
            nhap_kho_gia_tri: expect.any(Object),
            xuat_kho_so_luong: expect.any(Object),
            xuat_kho_gia_tri: expect.any(Object),
            cuoi_ky_so_luong: expect.any(Object),
            cuoi_ky_gia_tri: expect.any(Object),
            don_gia_binh_quan: expect.any(Object),
            createdDate: expect.any(Object),
            key_uuid: expect.any(Object),
          })
        );
      });

      it('passing ITonkhodauky should create a new form with FormGroup', () => {
        const formGroup = service.createTonkhodaukyFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            ten_kho: expect.any(Object),
            ma_hang: expect.any(Object),
            ten_hang: expect.any(Object),
            dvt: expect.any(Object),
            dau_ky_so_luong: expect.any(Object),
            dau_ky_gia_tri: expect.any(Object),
            nhap_kho_so_luong: expect.any(Object),
            nhap_kho_gia_tri: expect.any(Object),
            xuat_kho_so_luong: expect.any(Object),
            xuat_kho_gia_tri: expect.any(Object),
            cuoi_ky_so_luong: expect.any(Object),
            cuoi_ky_gia_tri: expect.any(Object),
            don_gia_binh_quan: expect.any(Object),
            createdDate: expect.any(Object),
            key_uuid: expect.any(Object),
          })
        );
      });
    });

    describe('getTonkhodauky', () => {
      it('should return NewTonkhodauky for default Tonkhodauky initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createTonkhodaukyFormGroup(sampleWithNewData);

        const tonkhodauky = service.getTonkhodauky(formGroup) as any;

        expect(tonkhodauky).toMatchObject(sampleWithNewData);
      });

      it('should return NewTonkhodauky for empty Tonkhodauky initial value', () => {
        const formGroup = service.createTonkhodaukyFormGroup();

        const tonkhodauky = service.getTonkhodauky(formGroup) as any;

        expect(tonkhodauky).toMatchObject({});
      });

      it('should return ITonkhodauky', () => {
        const formGroup = service.createTonkhodaukyFormGroup(sampleWithRequiredData);

        const tonkhodauky = service.getTonkhodauky(formGroup) as any;

        expect(tonkhodauky).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ITonkhodauky should not enable id FormControl', () => {
        const formGroup = service.createTonkhodaukyFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewTonkhodauky should disable id FormControl', () => {
        const formGroup = service.createTonkhodaukyFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
