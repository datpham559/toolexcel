import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ICongnophaitra } from '../congnophaitra.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../congnophaitra.test-samples';

import { CongnophaitraService, RestCongnophaitra } from './congnophaitra.service';

const requireRestSample: RestCongnophaitra = {
  ...sampleWithRequiredData,
  created_Date: sampleWithRequiredData.created_Date?.format(DATE_FORMAT),
};

describe('Congnophaitra Service', () => {
  let service: CongnophaitraService;
  let httpMock: HttpTestingController;
  let expectedResult: ICongnophaitra | ICongnophaitra[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CongnophaitraService);
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

    it('should create a Congnophaitra', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const congnophaitra = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(congnophaitra).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Congnophaitra', () => {
      const congnophaitra = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(congnophaitra).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Congnophaitra', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Congnophaitra', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Congnophaitra', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCongnophaitraToCollectionIfMissing', () => {
      it('should add a Congnophaitra to an empty array', () => {
        const congnophaitra: ICongnophaitra = sampleWithRequiredData;
        expectedResult = service.addCongnophaitraToCollectionIfMissing([], congnophaitra);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(congnophaitra);
      });

      it('should not add a Congnophaitra to an array that contains it', () => {
        const congnophaitra: ICongnophaitra = sampleWithRequiredData;
        const congnophaitraCollection: ICongnophaitra[] = [
          {
            ...congnophaitra,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCongnophaitraToCollectionIfMissing(congnophaitraCollection, congnophaitra);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Congnophaitra to an array that doesn't contain it", () => {
        const congnophaitra: ICongnophaitra = sampleWithRequiredData;
        const congnophaitraCollection: ICongnophaitra[] = [sampleWithPartialData];
        expectedResult = service.addCongnophaitraToCollectionIfMissing(congnophaitraCollection, congnophaitra);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(congnophaitra);
      });

      it('should add only unique Congnophaitra to an array', () => {
        const congnophaitraArray: ICongnophaitra[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const congnophaitraCollection: ICongnophaitra[] = [sampleWithRequiredData];
        expectedResult = service.addCongnophaitraToCollectionIfMissing(congnophaitraCollection, ...congnophaitraArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const congnophaitra: ICongnophaitra = sampleWithRequiredData;
        const congnophaitra2: ICongnophaitra = sampleWithPartialData;
        expectedResult = service.addCongnophaitraToCollectionIfMissing([], congnophaitra, congnophaitra2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(congnophaitra);
        expect(expectedResult).toContain(congnophaitra2);
      });

      it('should accept null and undefined values', () => {
        const congnophaitra: ICongnophaitra = sampleWithRequiredData;
        expectedResult = service.addCongnophaitraToCollectionIfMissing([], null, congnophaitra, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(congnophaitra);
      });

      it('should return initial array if no Congnophaitra is added', () => {
        const congnophaitraCollection: ICongnophaitra[] = [sampleWithRequiredData];
        expectedResult = service.addCongnophaitraToCollectionIfMissing(congnophaitraCollection, undefined, null);
        expect(expectedResult).toEqual(congnophaitraCollection);
      });
    });

    describe('compareCongnophaitra', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCongnophaitra(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCongnophaitra(entity1, entity2);
        const compareResult2 = service.compareCongnophaitra(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCongnophaitra(entity1, entity2);
        const compareResult2 = service.compareCongnophaitra(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCongnophaitra(entity1, entity2);
        const compareResult2 = service.compareCongnophaitra(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
