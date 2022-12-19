import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../congnophaithu.test-samples';

import { CongnophaithuFormService } from './congnophaithu-form.service';

describe('Congnophaithu Form Service', () => {
  let service: CongnophaithuFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CongnophaithuFormService);
  });

  describe('Service methods', () => {
    describe('createCongnophaithuFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCongnophaithuFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            makh: expect.any(Object),
            tenkh: expect.any(Object),
            tkcongno: expect.any(Object),
            sodunodauky: expect.any(Object),
            soducodauky: expect.any(Object),
            sonophatsinh: expect.any(Object),
            socophatsinh: expect.any(Object),
            sodunocuoiky: expect.any(Object),
            soducocuoiky: expect.any(Object),
            createdDate: expect.any(Object),
            key_uuid: expect.any(Object),
          })
        );
      });

      it('passing ICongnophaithu should create a new form with FormGroup', () => {
        const formGroup = service.createCongnophaithuFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            makh: expect.any(Object),
            tenkh: expect.any(Object),
            tkcongno: expect.any(Object),
            sodunodauky: expect.any(Object),
            soducodauky: expect.any(Object),
            sonophatsinh: expect.any(Object),
            socophatsinh: expect.any(Object),
            sodunocuoiky: expect.any(Object),
            soducocuoiky: expect.any(Object),
            createdDate: expect.any(Object),
            key_uuid: expect.any(Object),
          })
        );
      });
    });

    describe('getCongnophaithu', () => {
      it('should return NewCongnophaithu for default Congnophaithu initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createCongnophaithuFormGroup(sampleWithNewData);

        const congnophaithu = service.getCongnophaithu(formGroup) as any;

        expect(congnophaithu).toMatchObject(sampleWithNewData);
      });

      it('should return NewCongnophaithu for empty Congnophaithu initial value', () => {
        const formGroup = service.createCongnophaithuFormGroup();

        const congnophaithu = service.getCongnophaithu(formGroup) as any;

        expect(congnophaithu).toMatchObject({});
      });

      it('should return ICongnophaithu', () => {
        const formGroup = service.createCongnophaithuFormGroup(sampleWithRequiredData);

        const congnophaithu = service.getCongnophaithu(formGroup) as any;

        expect(congnophaithu).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICongnophaithu should not enable id FormControl', () => {
        const formGroup = service.createCongnophaithuFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCongnophaithu should disable id FormControl', () => {
        const formGroup = service.createCongnophaithuFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
