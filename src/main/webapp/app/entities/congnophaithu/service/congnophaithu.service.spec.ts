import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ICongnophaithu } from '../congnophaithu.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../congnophaithu.test-samples';

import { CongnophaithuService, RestCongnophaithu } from './congnophaithu.service';

const requireRestSample: RestCongnophaithu = {
  ...sampleWithRequiredData,
  createdDate: sampleWithRequiredData.createdDate?.format(DATE_FORMAT),
};

describe('Congnophaithu Service', () => {
  let service: CongnophaithuService;
  let httpMock: HttpTestingController;
  let expectedResult: ICongnophaithu | ICongnophaithu[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CongnophaithuService);
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

    it('should create a Congnophaithu', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const congnophaithu = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(congnophaithu).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Congnophaithu', () => {
      const congnophaithu = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(congnophaithu).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Congnophaithu', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Congnophaithu', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Congnophaithu', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCongnophaithuToCollectionIfMissing', () => {
      it('should add a Congnophaithu to an empty array', () => {
        const congnophaithu: ICongnophaithu = sampleWithRequiredData;
        expectedResult = service.addCongnophaithuToCollectionIfMissing([], congnophaithu);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(congnophaithu);
      });

      it('should not add a Congnophaithu to an array that contains it', () => {
        const congnophaithu: ICongnophaithu = sampleWithRequiredData;
        const congnophaithuCollection: ICongnophaithu[] = [
          {
            ...congnophaithu,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCongnophaithuToCollectionIfMissing(congnophaithuCollection, congnophaithu);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Congnophaithu to an array that doesn't contain it", () => {
        const congnophaithu: ICongnophaithu = sampleWithRequiredData;
        const congnophaithuCollection: ICongnophaithu[] = [sampleWithPartialData];
        expectedResult = service.addCongnophaithuToCollectionIfMissing(congnophaithuCollection, congnophaithu);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(congnophaithu);
      });

      it('should add only unique Congnophaithu to an array', () => {
        const congnophaithuArray: ICongnophaithu[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const congnophaithuCollection: ICongnophaithu[] = [sampleWithRequiredData];
        expectedResult = service.addCongnophaithuToCollectionIfMissing(congnophaithuCollection, ...congnophaithuArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const congnophaithu: ICongnophaithu = sampleWithRequiredData;
        const congnophaithu2: ICongnophaithu = sampleWithPartialData;
        expectedResult = service.addCongnophaithuToCollectionIfMissing([], congnophaithu, congnophaithu2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(congnophaithu);
        expect(expectedResult).toContain(congnophaithu2);
      });

      it('should accept null and undefined values', () => {
        const congnophaithu: ICongnophaithu = sampleWithRequiredData;
        expectedResult = service.addCongnophaithuToCollectionIfMissing([], null, congnophaithu, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(congnophaithu);
      });

      it('should return initial array if no Congnophaithu is added', () => {
        const congnophaithuCollection: ICongnophaithu[] = [sampleWithRequiredData];
        expectedResult = service.addCongnophaithuToCollectionIfMissing(congnophaithuCollection, undefined, null);
        expect(expectedResult).toEqual(congnophaithuCollection);
      });
    });

    describe('compareCongnophaithu', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCongnophaithu(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCongnophaithu(entity1, entity2);
        const compareResult2 = service.compareCongnophaithu(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCongnophaithu(entity1, entity2);
        const compareResult2 = service.compareCongnophaithu(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCongnophaithu(entity1, entity2);
        const compareResult2 = service.compareCongnophaithu(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
