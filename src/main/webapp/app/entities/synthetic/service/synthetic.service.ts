import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISynthetic, NewSynthetic } from '../synthetic.model';

export type PartialUpdateSynthetic = Partial<ISynthetic> & Pick<ISynthetic, 'id'>;

type RestOf<T extends ISynthetic | NewSynthetic> = Omit<T, 'voucherDate' | 'accountingDate' | 'invoiceDate' | 'createdDate'> & {
  voucherDate?: string | null;
  accountingDate?: string | null;
  invoiceDate?: string | null;
  createdDate?: string | null;
};

export type RestSynthetic = RestOf<ISynthetic>;

export type NewRestSynthetic = RestOf<NewSynthetic>;

export type PartialUpdateRestSynthetic = RestOf<PartialUpdateSynthetic>;

export type EntityResponseType = HttpResponse<ISynthetic>;
export type EntityArrayResponseType = HttpResponse<ISynthetic[]>;

@Injectable({ providedIn: 'root' })
export class SyntheticService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/synthetics');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(synthetic: NewSynthetic): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(synthetic);
    return this.http
      .post<RestSynthetic>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(synthetic: ISynthetic): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(synthetic);
    return this.http
      .put<RestSynthetic>(`${this.resourceUrl}/${this.getSyntheticIdentifier(synthetic)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(synthetic: PartialUpdateSynthetic): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(synthetic);
    return this.http
      .patch<RestSynthetic>(`${this.resourceUrl}/${this.getSyntheticIdentifier(synthetic)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestSynthetic>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestSynthetic[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSyntheticIdentifier(synthetic: Pick<ISynthetic, 'id'>): number {
    return synthetic.id;
  }

  compareSynthetic(o1: Pick<ISynthetic, 'id'> | null, o2: Pick<ISynthetic, 'id'> | null): boolean {
    return o1 && o2 ? this.getSyntheticIdentifier(o1) === this.getSyntheticIdentifier(o2) : o1 === o2;
  }

  addSyntheticToCollectionIfMissing<Type extends Pick<ISynthetic, 'id'>>(
    syntheticCollection: Type[],
    ...syntheticsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const synthetics: Type[] = syntheticsToCheck.filter(isPresent);
    if (synthetics.length > 0) {
      const syntheticCollectionIdentifiers = syntheticCollection.map(syntheticItem => this.getSyntheticIdentifier(syntheticItem)!);
      const syntheticsToAdd = synthetics.filter(syntheticItem => {
        const syntheticIdentifier = this.getSyntheticIdentifier(syntheticItem);
        if (syntheticCollectionIdentifiers.includes(syntheticIdentifier)) {
          return false;
        }
        syntheticCollectionIdentifiers.push(syntheticIdentifier);
        return true;
      });
      return [...syntheticsToAdd, ...syntheticCollection];
    }
    return syntheticCollection;
  }

  protected convertDateFromClient<T extends ISynthetic | NewSynthetic | PartialUpdateSynthetic>(synthetic: T): RestOf<T> {
    return {
      ...synthetic,
      voucherDate: synthetic.voucherDate?.format(DATE_FORMAT) ?? null,
      accountingDate: synthetic.accountingDate?.format(DATE_FORMAT) ?? null,
      invoiceDate: synthetic.invoiceDate?.format(DATE_FORMAT) ?? null,
      createdDate: synthetic.createdDate?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restSynthetic: RestSynthetic): ISynthetic {
    return {
      ...restSynthetic,
      voucherDate: restSynthetic.voucherDate ? dayjs(restSynthetic.voucherDate) : undefined,
      accountingDate: restSynthetic.accountingDate ? dayjs(restSynthetic.accountingDate) : undefined,
      invoiceDate: restSynthetic.invoiceDate ? dayjs(restSynthetic.invoiceDate) : undefined,
      createdDate: restSynthetic.createdDate ? dayjs(restSynthetic.createdDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestSynthetic>): HttpResponse<ISynthetic> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestSynthetic[]>): HttpResponse<ISynthetic[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
