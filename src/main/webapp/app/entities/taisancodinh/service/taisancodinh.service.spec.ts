import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ITaisancodinh } from '../taisancodinh.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../taisancodinh.test-samples';

import { TaisancodinhService, RestTaisancodinh } from './taisancodinh.service';

const requireRestSample: RestTaisancodinh = {
  ...sampleWithRequiredData,
  ngay_ghi_tang: sampleWithRequiredData.ngay_ghi_tang?.format(DATE_FORMAT),
  ngay_bat_dau_tinh_kh: sampleWithRequiredData.ngay_bat_dau_tinh_kh?.format(DATE_FORMAT),
  createdDate: sampleWithRequiredData.createdDate?.format(DATE_FORMAT),
};

describe('Taisancodinh Service', () => {
  let service: TaisancodinhService;
  let httpMock: HttpTestingController;
  let expectedResult: ITaisancodinh | ITaisancodinh[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TaisancodinhService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a Taisancodinh', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const taisancodinh = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(taisancodinh).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Taisancodinh', () => {
      const taisancodinh = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(taisancodinh).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Taisancodinh', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Taisancodinh', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Taisancodinh', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addTaisancodinhToCollectionIfMissing', () => {
      it('should add a Taisancodinh to an empty array', () => {
        const taisancodinh: ITaisancodinh = sampleWithRequiredData;
        expectedResult = service.addTaisancodinhToCollectionIfMissing([], taisancodinh);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(taisancodinh);
      });

      it('should not add a Taisancodinh to an array that contains it', () => {
        const taisancodinh: ITaisancodinh = sampleWithRequiredData;
        const taisancodinhCollection: ITaisancodinh[] = [
          {
            ...taisancodinh,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addTaisancodinhToCollectionIfMissing(taisancodinhCollection, taisancodinh);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Taisancodinh to an array that doesn't contain it", () => {
        const taisancodinh: ITaisancodinh = sampleWithRequiredData;
        const taisancodinhCollection: ITaisancodinh[] = [sampleWithPartialData];
        expectedResult = service.addTaisancodinhToCollectionIfMissing(taisancodinhCollection, taisancodinh);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(taisancodinh);
      });

      it('should add only unique Taisancodinh to an array', () => {
        const taisancodinhArray: ITaisancodinh[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const taisancodinhCollection: ITaisancodinh[] = [sampleWithRequiredData];
        expectedResult = service.addTaisancodinhToCollectionIfMissing(taisancodinhCollection, ...taisancodinhArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const taisancodinh: ITaisancodinh = sampleWithRequiredData;
        const taisancodinh2: ITaisancodinh = sampleWithPartialData;
        expectedResult = service.addTaisancodinhToCollectionIfMissing([], taisancodinh, taisancodinh2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(taisancodinh);
        expect(expectedResult).toContain(taisancodinh2);
      });

      it('should accept null and undefined values', () => {
        const taisancodinh: ITaisancodinh = sampleWithRequiredData;
        expectedResult = service.addTaisancodinhToCollectionIfMissing([], null, taisancodinh, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(taisancodinh);
      });

      it('should return initial array if no Taisancodinh is added', () => {
        const taisancodinhCollection: ITaisancodinh[] = [sampleWithRequiredData];
        expectedResult = service.addTaisancodinhToCollectionIfMissing(taisancodinhCollection, undefined, null);
        expect(expectedResult).toEqual(taisancodinhCollection);
      });
    });

    describe('compareTaisancodinh', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareTaisancodinh(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareTaisancodinh(entity1, entity2);
        const compareResult2 = service.compareTaisancodinh(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareTaisancodinh(entity1, entity2);
        const compareResult2 = service.compareTaisancodinh(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareTaisancodinh(entity1, entity2);
        const compareResult2 = service.compareTaisancodinh(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
