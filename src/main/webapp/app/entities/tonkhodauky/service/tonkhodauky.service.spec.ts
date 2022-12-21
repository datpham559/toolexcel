import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ITonkhodauky } from '../tonkhodauky.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../tonkhodauky.test-samples';

import { TonkhodaukyService, RestTonkhodauky } from './tonkhodauky.service';

const requireRestSample: RestTonkhodauky = {
  ...sampleWithRequiredData,
  createdDate: sampleWithRequiredData.createdDate?.format(DATE_FORMAT),
};

describe('Tonkhodauky Service', () => {
  let service: TonkhodaukyService;
  let httpMock: HttpTestingController;
  let expectedResult: ITonkhodauky | ITonkhodauky[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TonkhodaukyService);
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

    it('should create a Tonkhodauky', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const tonkhodauky = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(tonkhodauky).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Tonkhodauky', () => {
      const tonkhodauky = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(tonkhodauky).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Tonkhodauky', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Tonkhodauky', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Tonkhodauky', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addTonkhodaukyToCollectionIfMissing', () => {
      it('should add a Tonkhodauky to an empty array', () => {
        const tonkhodauky: ITonkhodauky = sampleWithRequiredData;
        expectedResult = service.addTonkhodaukyToCollectionIfMissing([], tonkhodauky);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tonkhodauky);
      });

      it('should not add a Tonkhodauky to an array that contains it', () => {
        const tonkhodauky: ITonkhodauky = sampleWithRequiredData;
        const tonkhodaukyCollection: ITonkhodauky[] = [
          {
            ...tonkhodauky,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addTonkhodaukyToCollectionIfMissing(tonkhodaukyCollection, tonkhodauky);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Tonkhodauky to an array that doesn't contain it", () => {
        const tonkhodauky: ITonkhodauky = sampleWithRequiredData;
        const tonkhodaukyCollection: ITonkhodauky[] = [sampleWithPartialData];
        expectedResult = service.addTonkhodaukyToCollectionIfMissing(tonkhodaukyCollection, tonkhodauky);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tonkhodauky);
      });

      it('should add only unique Tonkhodauky to an array', () => {
        const tonkhodaukyArray: ITonkhodauky[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const tonkhodaukyCollection: ITonkhodauky[] = [sampleWithRequiredData];
        expectedResult = service.addTonkhodaukyToCollectionIfMissing(tonkhodaukyCollection, ...tonkhodaukyArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const tonkhodauky: ITonkhodauky = sampleWithRequiredData;
        const tonkhodauky2: ITonkhodauky = sampleWithPartialData;
        expectedResult = service.addTonkhodaukyToCollectionIfMissing([], tonkhodauky, tonkhodauky2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tonkhodauky);
        expect(expectedResult).toContain(tonkhodauky2);
      });

      it('should accept null and undefined values', () => {
        const tonkhodauky: ITonkhodauky = sampleWithRequiredData;
        expectedResult = service.addTonkhodaukyToCollectionIfMissing([], null, tonkhodauky, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tonkhodauky);
      });

      it('should return initial array if no Tonkhodauky is added', () => {
        const tonkhodaukyCollection: ITonkhodauky[] = [sampleWithRequiredData];
        expectedResult = service.addTonkhodaukyToCollectionIfMissing(tonkhodaukyCollection, undefined, null);
        expect(expectedResult).toEqual(tonkhodaukyCollection);
      });
    });

    describe('compareTonkhodauky', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareTonkhodauky(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareTonkhodauky(entity1, entity2);
        const compareResult2 = service.compareTonkhodauky(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareTonkhodauky(entity1, entity2);
        const compareResult2 = service.compareTonkhodauky(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareTonkhodauky(entity1, entity2);
        const compareResult2 = service.compareTonkhodauky(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
