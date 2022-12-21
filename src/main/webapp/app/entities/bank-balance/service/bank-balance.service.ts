import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IBankBalance, NewBankBalance } from '../bank-balance.model';

export type PartialUpdateBankBalance = Partial<IBankBalance> & Pick<IBankBalance, 'id'>;

type RestOf<T extends IBankBalance | NewBankBalance> = Omit<T, 'created_date'> & {
  created_date?: string | null;
};

export type RestBankBalance = RestOf<IBankBalance>;

export type NewRestBankBalance = RestOf<NewBankBalance>;

export type PartialUpdateRestBankBalance = RestOf<PartialUpdateBankBalance>;

export type EntityResponseType = HttpResponse<IBankBalance>;
export type EntityArrayResponseType = HttpResponse<IBankBalance[]>;

@Injectable({ providedIn: 'root' })
export class BankBalanceService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/bank-balances');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(bankBalance: NewBankBalance): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bankBalance);
    return this.http
      .post<RestBankBalance>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(bankBalance: IBankBalance): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bankBalance);
    return this.http
      .put<RestBankBalance>(`${this.resourceUrl}/${this.getBankBalanceIdentifier(bankBalance)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(bankBalance: PartialUpdateBankBalance): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bankBalance);
    return this.http
      .patch<RestBankBalance>(`${this.resourceUrl}/${this.getBankBalanceIdentifier(bankBalance)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestBankBalance>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestBankBalance[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getBankBalanceIdentifier(bankBalance: Pick<IBankBalance, 'id'>): number {
    return bankBalance.id;
  }

  compareBankBalance(o1: Pick<IBankBalance, 'id'> | null, o2: Pick<IBankBalance, 'id'> | null): boolean {
    return o1 && o2 ? this.getBankBalanceIdentifier(o1) === this.getBankBalanceIdentifier(o2) : o1 === o2;
  }

  addBankBalanceToCollectionIfMissing<Type extends Pick<IBankBalance, 'id'>>(
    bankBalanceCollection: Type[],
    ...bankBalancesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const bankBalances: Type[] = bankBalancesToCheck.filter(isPresent);
    if (bankBalances.length > 0) {
      const bankBalanceCollectionIdentifiers = bankBalanceCollection.map(
        bankBalanceItem => this.getBankBalanceIdentifier(bankBalanceItem)!
      );
      const bankBalancesToAdd = bankBalances.filter(bankBalanceItem => {
        const bankBalanceIdentifier = this.getBankBalanceIdentifier(bankBalanceItem);
        if (bankBalanceCollectionIdentifiers.includes(bankBalanceIdentifier)) {
          return false;
        }
        bankBalanceCollectionIdentifiers.push(bankBalanceIdentifier);
        return true;
      });
      return [...bankBalancesToAdd, ...bankBalanceCollection];
    }
    return bankBalanceCollection;
  }

  protected convertDateFromClient<T extends IBankBalance | NewBankBalance | PartialUpdateBankBalance>(bankBalance: T): RestOf<T> {
    return {
      ...bankBalance,
      created_date: bankBalance.created_date?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restBankBalance: RestBankBalance): IBankBalance {
    return {
      ...restBankBalance,
      created_date: restBankBalance.created_date ? dayjs(restBankBalance.created_date) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestBankBalance>): HttpResponse<IBankBalance> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestBankBalance[]>): HttpResponse<IBankBalance[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
