import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IMerchandise } from '../merchandise.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../merchandise.test-samples';

import { MerchandiseService, RestMerchandise } from './merchandise.service';

const requireRestSample: RestMerchandise = {
  ...sampleWithRequiredData,
  created_date: sampleWithRequiredData.created_date?.format(DATE_FORMAT),
};

describe('Merchandise Service', () => {
  let service: MerchandiseService;
  let httpMock: HttpTestingController;
  let expectedResult: IMerchandise | IMerchandise[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(MerchandiseService);
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

    it('should create a Merchandise', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const merchandise = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(merchandise).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Merchandise', () => {
      const merchandise = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(merchandise).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Merchandise', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Merchandise', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Merchandise', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addMerchandiseToCollectionIfMissing', () => {
      it('should add a Merchandise to an empty array', () => {
        const merchandise: IMerchandise = sampleWithRequiredData;
        expectedResult = service.addMerchandiseToCollectionIfMissing([], merchandise);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(merchandise);
      });

      it('should not add a Merchandise to an array that contains it', () => {
        const merchandise: IMerchandise = sampleWithRequiredData;
        const merchandiseCollection: IMerchandise[] = [
          {
            ...merchandise,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addMerchandiseToCollectionIfMissing(merchandiseCollection, merchandise);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Merchandise to an array that doesn't contain it", () => {
        const merchandise: IMerchandise = sampleWithRequiredData;
        const merchandiseCollection: IMerchandise[] = [sampleWithPartialData];
        expectedResult = service.addMerchandiseToCollectionIfMissing(merchandiseCollection, merchandise);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(merchandise);
      });

      it('should add only unique Merchandise to an array', () => {
        const merchandiseArray: IMerchandise[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const merchandiseCollection: IMerchandise[] = [sampleWithRequiredData];
        expectedResult = service.addMerchandiseToCollectionIfMissing(merchandiseCollection, ...merchandiseArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const merchandise: IMerchandise = sampleWithRequiredData;
        const merchandise2: IMerchandise = sampleWithPartialData;
        expectedResult = service.addMerchandiseToCollectionIfMissing([], merchandise, merchandise2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(merchandise);
        expect(expectedResult).toContain(merchandise2);
      });

      it('should accept null and undefined values', () => {
        const merchandise: IMerchandise = sampleWithRequiredData;
        expectedResult = service.addMerchandiseToCollectionIfMissing([], null, merchandise, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(merchandise);
      });

      it('should return initial array if no Merchandise is added', () => {
        const merchandiseCollection: IMerchandise[] = [sampleWithRequiredData];
        expectedResult = service.addMerchandiseToCollectionIfMissing(merchandiseCollection, undefined, null);
        expect(expectedResult).toEqual(merchandiseCollection);
      });
    });

    describe('compareMerchandise', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareMerchandise(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareMerchandise(entity1, entity2);
        const compareResult2 = service.compareMerchandise(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareMerchandise(entity1, entity2);
        const compareResult2 = service.compareMerchandise(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareMerchandise(entity1, entity2);
        const compareResult2 = service.compareMerchandise(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
