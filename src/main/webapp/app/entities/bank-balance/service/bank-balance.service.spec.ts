import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IBankBalance } from '../bank-balance.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../bank-balance.test-samples';

import { BankBalanceService, RestBankBalance } from './bank-balance.service';

const requireRestSample: RestBankBalance = {
  ...sampleWithRequiredData,
  created_date: sampleWithRequiredData.created_date?.format(DATE_FORMAT),
};

describe('BankBalance Service', () => {
  let service: BankBalanceService;
  let httpMock: HttpTestingController;
  let expectedResult: IBankBalance | IBankBalance[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(BankBalanceService);
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

    it('should create a BankBalance', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const bankBalance = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(bankBalance).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a BankBalance', () => {
      const bankBalance = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(bankBalance).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a BankBalance', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of BankBalance', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a BankBalance', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addBankBalanceToCollectionIfMissing', () => {
      it('should add a BankBalance to an empty array', () => {
        const bankBalance: IBankBalance = sampleWithRequiredData;
        expectedResult = service.addBankBalanceToCollectionIfMissing([], bankBalance);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(bankBalance);
      });

      it('should not add a BankBalance to an array that contains it', () => {
        const bankBalance: IBankBalance = sampleWithRequiredData;
        const bankBalanceCollection: IBankBalance[] = [
          {
            ...bankBalance,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addBankBalanceToCollectionIfMissing(bankBalanceCollection, bankBalance);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a BankBalance to an array that doesn't contain it", () => {
        const bankBalance: IBankBalance = sampleWithRequiredData;
        const bankBalanceCollection: IBankBalance[] = [sampleWithPartialData];
        expectedResult = service.addBankBalanceToCollectionIfMissing(bankBalanceCollection, bankBalance);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(bankBalance);
      });

      it('should add only unique BankBalance to an array', () => {
        const bankBalanceArray: IBankBalance[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const bankBalanceCollection: IBankBalance[] = [sampleWithRequiredData];
        expectedResult = service.addBankBalanceToCollectionIfMissing(bankBalanceCollection, ...bankBalanceArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const bankBalance: IBankBalance = sampleWithRequiredData;
        const bankBalance2: IBankBalance = sampleWithPartialData;
        expectedResult = service.addBankBalanceToCollectionIfMissing([], bankBalance, bankBalance2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(bankBalance);
        expect(expectedResult).toContain(bankBalance2);
      });

      it('should accept null and undefined values', () => {
        const bankBalance: IBankBalance = sampleWithRequiredData;
        expectedResult = service.addBankBalanceToCollectionIfMissing([], null, bankBalance, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(bankBalance);
      });

      it('should return initial array if no BankBalance is added', () => {
        const bankBalanceCollection: IBankBalance[] = [sampleWithRequiredData];
        expectedResult = service.addBankBalanceToCollectionIfMissing(bankBalanceCollection, undefined, null);
        expect(expectedResult).toEqual(bankBalanceCollection);
      });
    });

    describe('compareBankBalance', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareBankBalance(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareBankBalance(entity1, entity2);
        const compareResult2 = service.compareBankBalance(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareBankBalance(entity1, entity2);
        const compareResult2 = service.compareBankBalance(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareBankBalance(entity1, entity2);
        const compareResult2 = service.compareBankBalance(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
