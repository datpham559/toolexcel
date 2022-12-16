import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICongcudungcu, NewCongcudungcu } from '../congcudungcu.model';

export type PartialUpdateCongcudungcu = Partial<ICongcudungcu> & Pick<ICongcudungcu, 'id'>;

type RestOf<T extends ICongcudungcu | NewCongcudungcu> = Omit<T, 'ngay_ghi_tang' | 'createdDate'> & {
  ngay_ghi_tang?: string | null;
  createdDate?: string | null;
};

export type RestCongcudungcu = RestOf<ICongcudungcu>;

export type NewRestCongcudungcu = RestOf<NewCongcudungcu>;

export type PartialUpdateRestCongcudungcu = RestOf<PartialUpdateCongcudungcu>;

export type EntityResponseType = HttpResponse<ICongcudungcu>;
export type EntityArrayResponseType = HttpResponse<ICongcudungcu[]>;

@Injectable({ providedIn: 'root' })
export class CongcudungcuService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/congcudungcus');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(congcudungcu: NewCongcudungcu): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(congcudungcu);
    return this.http
      .post<RestCongcudungcu>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(congcudungcu: ICongcudungcu): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(congcudungcu);
    return this.http
      .put<RestCongcudungcu>(`${this.resourceUrl}/${this.getCongcudungcuIdentifier(congcudungcu)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(congcudungcu: PartialUpdateCongcudungcu): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(congcudungcu);
    return this.http
      .patch<RestCongcudungcu>(`${this.resourceUrl}/${this.getCongcudungcuIdentifier(congcudungcu)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestCongcudungcu>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestCongcudungcu[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCongcudungcuIdentifier(congcudungcu: Pick<ICongcudungcu, 'id'>): number {
    return congcudungcu.id;
  }

  compareCongcudungcu(o1: Pick<ICongcudungcu, 'id'> | null, o2: Pick<ICongcudungcu, 'id'> | null): boolean {
    return o1 && o2 ? this.getCongcudungcuIdentifier(o1) === this.getCongcudungcuIdentifier(o2) : o1 === o2;
  }

  addCongcudungcuToCollectionIfMissing<Type extends Pick<ICongcudungcu, 'id'>>(
    congcudungcuCollection: Type[],
    ...congcudungcusToCheck: (Type | null | undefined)[]
  ): Type[] {
    const congcudungcus: Type[] = congcudungcusToCheck.filter(isPresent);
    if (congcudungcus.length > 0) {
      const congcudungcuCollectionIdentifiers = congcudungcuCollection.map(
        congcudungcuItem => this.getCongcudungcuIdentifier(congcudungcuItem)!
      );
      const congcudungcusToAdd = congcudungcus.filter(congcudungcuItem => {
        const congcudungcuIdentifier = this.getCongcudungcuIdentifier(congcudungcuItem);
        if (congcudungcuCollectionIdentifiers.includes(congcudungcuIdentifier)) {
          return false;
        }
        congcudungcuCollectionIdentifiers.push(congcudungcuIdentifier);
        return true;
      });
      return [...congcudungcusToAdd, ...congcudungcuCollection];
    }
    return congcudungcuCollection;
  }

  protected convertDateFromClient<T extends ICongcudungcu | NewCongcudungcu | PartialUpdateCongcudungcu>(congcudungcu: T): RestOf<T> {
    return {
      ...congcudungcu,
      ngay_ghi_tang: congcudungcu.ngay_ghi_tang?.format(DATE_FORMAT) ?? null,
      createdDate: congcudungcu.createdDate?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restCongcudungcu: RestCongcudungcu): ICongcudungcu {
    return {
      ...restCongcudungcu,
      ngay_ghi_tang: restCongcudungcu.ngay_ghi_tang ? dayjs(restCongcudungcu.ngay_ghi_tang) : undefined,
      createdDate: restCongcudungcu.createdDate ? dayjs(restCongcudungcu.createdDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestCongcudungcu>): HttpResponse<ICongcudungcu> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestCongcudungcu[]>): HttpResponse<ICongcudungcu[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
