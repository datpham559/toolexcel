import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IDmNCC } from '../dm-ncc.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../dm-ncc.test-samples';

import { DmNCCService, RestDmNCC } from './dm-ncc.service';

const requireRestSample: RestDmNCC = {
  ...sampleWithRequiredData,
  created_date: sampleWithRequiredData.created_date?.format(DATE_FORMAT),
};

describe('DmNCC Service', () => {
  let service: DmNCCService;
  let httpMock: HttpTestingController;
  let expectedResult: IDmNCC | IDmNCC[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DmNCCService);
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

    it('should create a DmNCC', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const dmNCC = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(dmNCC).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a DmNCC', () => {
      const dmNCC = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(dmNCC).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a DmNCC', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of DmNCC', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a DmNCC', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addDmNCCToCollectionIfMissing', () => {
      it('should add a DmNCC to an empty array', () => {
        const dmNCC: IDmNCC = sampleWithRequiredData;
        expectedResult = service.addDmNCCToCollectionIfMissing([], dmNCC);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(dmNCC);
      });

      it('should not add a DmNCC to an array that contains it', () => {
        const dmNCC: IDmNCC = sampleWithRequiredData;
        const dmNCCCollection: IDmNCC[] = [
          {
            ...dmNCC,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addDmNCCToCollectionIfMissing(dmNCCCollection, dmNCC);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a DmNCC to an array that doesn't contain it", () => {
        const dmNCC: IDmNCC = sampleWithRequiredData;
        const dmNCCCollection: IDmNCC[] = [sampleWithPartialData];
        expectedResult = service.addDmNCCToCollectionIfMissing(dmNCCCollection, dmNCC);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(dmNCC);
      });

      it('should add only unique DmNCC to an array', () => {
        const dmNCCArray: IDmNCC[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const dmNCCCollection: IDmNCC[] = [sampleWithRequiredData];
        expectedResult = service.addDmNCCToCollectionIfMissing(dmNCCCollection, ...dmNCCArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const dmNCC: IDmNCC = sampleWithRequiredData;
        const dmNCC2: IDmNCC = sampleWithPartialData;
        expectedResult = service.addDmNCCToCollectionIfMissing([], dmNCC, dmNCC2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(dmNCC);
        expect(expectedResult).toContain(dmNCC2);
      });

      it('should accept null and undefined values', () => {
        const dmNCC: IDmNCC = sampleWithRequiredData;
        expectedResult = service.addDmNCCToCollectionIfMissing([], null, dmNCC, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(dmNCC);
      });

      it('should return initial array if no DmNCC is added', () => {
        const dmNCCCollection: IDmNCC[] = [sampleWithRequiredData];
        expectedResult = service.addDmNCCToCollectionIfMissing(dmNCCCollection, undefined, null);
        expect(expectedResult).toEqual(dmNCCCollection);
      });
    });

    describe('compareDmNCC', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareDmNCC(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareDmNCC(entity1, entity2);
        const compareResult2 = service.compareDmNCC(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareDmNCC(entity1, entity2);
        const compareResult2 = service.compareDmNCC(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareDmNCC(entity1, entity2);
        const compareResult2 = service.compareDmNCC(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
