import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ISynthetic } from '../synthetic.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../synthetic.test-samples';

import { SyntheticService, RestSynthetic } from './synthetic.service';

const requireRestSample: RestSynthetic = {
  ...sampleWithRequiredData,
  voucherDate: sampleWithRequiredData.voucherDate?.format(DATE_FORMAT),
  accountingDate: sampleWithRequiredData.accountingDate?.format(DATE_FORMAT),
  invoiceDate: sampleWithRequiredData.invoiceDate?.format(DATE_FORMAT),
  createdDate: sampleWithRequiredData.createdDate?.format(DATE_FORMAT),
};

describe('Synthetic Service', () => {
  let service: SyntheticService;
  let httpMock: HttpTestingController;
  let expectedResult: ISynthetic | ISynthetic[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SyntheticService);
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

    it('should create a Synthetic', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const synthetic = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(synthetic).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Synthetic', () => {
      const synthetic = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(synthetic).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Synthetic', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Synthetic', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Synthetic', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSyntheticToCollectionIfMissing', () => {
      it('should add a Synthetic to an empty array', () => {
        const synthetic: ISynthetic = sampleWithRequiredData;
        expectedResult = service.addSyntheticToCollectionIfMissing([], synthetic);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(synthetic);
      });

      it('should not add a Synthetic to an array that contains it', () => {
        const synthetic: ISynthetic = sampleWithRequiredData;
        const syntheticCollection: ISynthetic[] = [
          {
            ...synthetic,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSyntheticToCollectionIfMissing(syntheticCollection, synthetic);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Synthetic to an array that doesn't contain it", () => {
        const synthetic: ISynthetic = sampleWithRequiredData;
        const syntheticCollection: ISynthetic[] = [sampleWithPartialData];
        expectedResult = service.addSyntheticToCollectionIfMissing(syntheticCollection, synthetic);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(synthetic);
      });

      it('should add only unique Synthetic to an array', () => {
        const syntheticArray: ISynthetic[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const syntheticCollection: ISynthetic[] = [sampleWithRequiredData];
        expectedResult = service.addSyntheticToCollectionIfMissing(syntheticCollection, ...syntheticArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const synthetic: ISynthetic = sampleWithRequiredData;
        const synthetic2: ISynthetic = sampleWithPartialData;
        expectedResult = service.addSyntheticToCollectionIfMissing([], synthetic, synthetic2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(synthetic);
        expect(expectedResult).toContain(synthetic2);
      });

      it('should accept null and undefined values', () => {
        const synthetic: ISynthetic = sampleWithRequiredData;
        expectedResult = service.addSyntheticToCollectionIfMissing([], null, synthetic, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(synthetic);
      });

      it('should return initial array if no Synthetic is added', () => {
        const syntheticCollection: ISynthetic[] = [sampleWithRequiredData];
        expectedResult = service.addSyntheticToCollectionIfMissing(syntheticCollection, undefined, null);
        expect(expectedResult).toEqual(syntheticCollection);
      });
    });

    describe('compareSynthetic', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSynthetic(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareSynthetic(entity1, entity2);
        const compareResult2 = service.compareSynthetic(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareSynthetic(entity1, entity2);
        const compareResult2 = service.compareSynthetic(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareSynthetic(entity1, entity2);
        const compareResult2 = service.compareSynthetic(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
