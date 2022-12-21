import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IMerchandise, NewMerchandise } from '../merchandise.model';

export type PartialUpdateMerchandise = Partial<IMerchandise> & Pick<IMerchandise, 'id'>;

type RestOf<T extends IMerchandise | NewMerchandise> = Omit<T, 'created_date'> & {
  created_date?: string | null;
};

export type RestMerchandise = RestOf<IMerchandise>;

export type NewRestMerchandise = RestOf<NewMerchandise>;

export type PartialUpdateRestMerchandise = RestOf<PartialUpdateMerchandise>;

export type EntityResponseType = HttpResponse<IMerchandise>;
export type EntityArrayResponseType = HttpResponse<IMerchandise[]>;

@Injectable({ providedIn: 'root' })
export class MerchandiseService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/merchandises');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(merchandise: NewMerchandise): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(merchandise);
    return this.http
      .post<RestMerchandise>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(merchandise: IMerchandise): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(merchandise);
    return this.http
      .put<RestMerchandise>(`${this.resourceUrl}/${this.getMerchandiseIdentifier(merchandise)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(merchandise: PartialUpdateMerchandise): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(merchandise);
    return this.http
      .patch<RestMerchandise>(`${this.resourceUrl}/${this.getMerchandiseIdentifier(merchandise)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestMerchandise>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestMerchandise[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getMerchandiseIdentifier(merchandise: Pick<IMerchandise, 'id'>): number {
    return merchandise.id;
  }

  compareMerchandise(o1: Pick<IMerchandise, 'id'> | null, o2: Pick<IMerchandise, 'id'> | null): boolean {
    return o1 && o2 ? this.getMerchandiseIdentifier(o1) === this.getMerchandiseIdentifier(o2) : o1 === o2;
  }

  addMerchandiseToCollectionIfMissing<Type extends Pick<IMerchandise, 'id'>>(
    merchandiseCollection: Type[],
    ...merchandisesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const merchandises: Type[] = merchandisesToCheck.filter(isPresent);
    if (merchandises.length > 0) {
      const merchandiseCollectionIdentifiers = merchandiseCollection.map(
        merchandiseItem => this.getMerchandiseIdentifier(merchandiseItem)!
      );
      const merchandisesToAdd = merchandises.filter(merchandiseItem => {
        const merchandiseIdentifier = this.getMerchandiseIdentifier(merchandiseItem);
        if (merchandiseCollectionIdentifiers.includes(merchandiseIdentifier)) {
          return false;
        }
        merchandiseCollectionIdentifiers.push(merchandiseIdentifier);
        return true;
      });
      return [...merchandisesToAdd, ...merchandiseCollection];
    }
    return merchandiseCollection;
  }

  protected convertDateFromClient<T extends IMerchandise | NewMerchandise | PartialUpdateMerchandise>(merchandise: T): RestOf<T> {
    return {
      ...merchandise,
      created_date: merchandise.created_date?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restMerchandise: RestMerchandise): IMerchandise {
    return {
      ...restMerchandise,
      created_date: restMerchandise.created_date ? dayjs(restMerchandise.created_date) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestMerchandise>): HttpResponse<IMerchandise> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestMerchandise[]>): HttpResponse<IMerchandise[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
