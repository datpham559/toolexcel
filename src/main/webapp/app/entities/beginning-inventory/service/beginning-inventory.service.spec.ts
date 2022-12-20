import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IBeginningInventory } from '../beginning-inventory.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../beginning-inventory.test-samples';

import { BeginningInventoryService, RestBeginningInventory } from './beginning-inventory.service';

const requireRestSample: RestBeginningInventory = {
  ...sampleWithRequiredData,
  created_date: sampleWithRequiredData.created_date?.format(DATE_FORMAT),
};

describe('BeginningInventory Service', () => {
  let service: BeginningInventoryService;
  let httpMock: HttpTestingController;
  let expectedResult: IBeginningInventory | IBeginningInventory[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(BeginningInventoryService);
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

    it('should create a BeginningInventory', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const beginningInventory = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(beginningInventory).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a BeginningInventory', () => {
      const beginningInventory = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(beginningInventory).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a BeginningInventory', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of BeginningInventory', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a BeginningInventory', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addBeginningInventoryToCollectionIfMissing', () => {
      it('should add a BeginningInventory to an empty array', () => {
        const beginningInventory: IBeginningInventory = sampleWithRequiredData;
        expectedResult = service.addBeginningInventoryToCollectionIfMissing([], beginningInventory);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(beginningInventory);
      });

      it('should not add a BeginningInventory to an array that contains it', () => {
        const beginningInventory: IBeginningInventory = sampleWithRequiredData;
        const beginningInventoryCollection: IBeginningInventory[] = [
          {
            ...beginningInventory,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addBeginningInventoryToCollectionIfMissing(beginningInventoryCollection, beginningInventory);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a BeginningInventory to an array that doesn't contain it", () => {
        const beginningInventory: IBeginningInventory = sampleWithRequiredData;
        const beginningInventoryCollection: IBeginningInventory[] = [sampleWithPartialData];
        expectedResult = service.addBeginningInventoryToCollectionIfMissing(beginningInventoryCollection, beginningInventory);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(beginningInventory);
      });

      it('should add only unique BeginningInventory to an array', () => {
        const beginningInventoryArray: IBeginningInventory[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const beginningInventoryCollection: IBeginningInventory[] = [sampleWithRequiredData];
        expectedResult = service.addBeginningInventoryToCollectionIfMissing(beginningInventoryCollection, ...beginningInventoryArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const beginningInventory: IBeginningInventory = sampleWithRequiredData;
        const beginningInventory2: IBeginningInventory = sampleWithPartialData;
        expectedResult = service.addBeginningInventoryToCollectionIfMissing([], beginningInventory, beginningInventory2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(beginningInventory);
        expect(expectedResult).toContain(beginningInventory2);
      });

      it('should accept null and undefined values', () => {
        const beginningInventory: IBeginningInventory = sampleWithRequiredData;
        expectedResult = service.addBeginningInventoryToCollectionIfMissing([], null, beginningInventory, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(beginningInventory);
      });

      it('should return initial array if no BeginningInventory is added', () => {
        const beginningInventoryCollection: IBeginningInventory[] = [sampleWithRequiredData];
        expectedResult = service.addBeginningInventoryToCollectionIfMissing(beginningInventoryCollection, undefined, null);
        expect(expectedResult).toEqual(beginningInventoryCollection);
      });
    });

    describe('compareBeginningInventory', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareBeginningInventory(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareBeginningInventory(entity1, entity2);
        const compareResult2 = service.compareBeginningInventory(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareBeginningInventory(entity1, entity2);
        const compareResult2 = service.compareBeginningInventory(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareBeginningInventory(entity1, entity2);
        const compareResult2 = service.compareBeginningInventory(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
