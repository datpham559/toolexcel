import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITaisancodinh, NewTaisancodinh } from '../taisancodinh.model';

export type PartialUpdateTaisancodinh = Partial<ITaisancodinh> & Pick<ITaisancodinh, 'id'>;

type RestOf<T extends ITaisancodinh | NewTaisancodinh> = Omit<T, 'ngay_ghi_tang' | 'ngay_bat_dau_tinh_kh' | 'createdDate'> & {
  ngay_ghi_tang?: string | null;
  ngay_bat_dau_tinh_kh?: string | null;
  createdDate?: string | null;
};

export type RestTaisancodinh = RestOf<ITaisancodinh>;

export type NewRestTaisancodinh = RestOf<NewTaisancodinh>;

export type PartialUpdateRestTaisancodinh = RestOf<PartialUpdateTaisancodinh>;

export type EntityResponseType = HttpResponse<ITaisancodinh>;
export type EntityArrayResponseType = HttpResponse<ITaisancodinh[]>;

@Injectable({ providedIn: 'root' })
export class TaisancodinhService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/taisancodinhs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(taisancodinh: NewTaisancodinh): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(taisancodinh);
    return this.http
      .post<RestTaisancodinh>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(taisancodinh: ITaisancodinh): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(taisancodinh);
    return this.http
      .put<RestTaisancodinh>(`${this.resourceUrl}/${this.getTaisancodinhIdentifier(taisancodinh)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(taisancodinh: PartialUpdateTaisancodinh): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(taisancodinh);
    return this.http
      .patch<RestTaisancodinh>(`${this.resourceUrl}/${this.getTaisancodinhIdentifier(taisancodinh)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestTaisancodinh>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestTaisancodinh[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getTaisancodinhIdentifier(taisancodinh: Pick<ITaisancodinh, 'id'>): number {
    return taisancodinh.id;
  }

  compareTaisancodinh(o1: Pick<ITaisancodinh, 'id'> | null, o2: Pick<ITaisancodinh, 'id'> | null): boolean {
    return o1 && o2 ? this.getTaisancodinhIdentifier(o1) === this.getTaisancodinhIdentifier(o2) : o1 === o2;
  }

  addTaisancodinhToCollectionIfMissing<Type extends Pick<ITaisancodinh, 'id'>>(
    taisancodinhCollection: Type[],
    ...taisancodinhsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const taisancodinhs: Type[] = taisancodinhsToCheck.filter(isPresent);
    if (taisancodinhs.length > 0) {
      const taisancodinhCollectionIdentifiers = taisancodinhCollection.map(
        taisancodinhItem => this.getTaisancodinhIdentifier(taisancodinhItem)!
      );
      const taisancodinhsToAdd = taisancodinhs.filter(taisancodinhItem => {
        const taisancodinhIdentifier = this.getTaisancodinhIdentifier(taisancodinhItem);
        if (taisancodinhCollectionIdentifiers.includes(taisancodinhIdentifier)) {
          return false;
        }
        taisancodinhCollectionIdentifiers.push(taisancodinhIdentifier);
        return true;
      });
      return [...taisancodinhsToAdd, ...taisancodinhCollection];
    }
    return taisancodinhCollection;
  }

  protected convertDateFromClient<T extends ITaisancodinh | NewTaisancodinh | PartialUpdateTaisancodinh>(taisancodinh: T): RestOf<T> {
    return {
      ...taisancodinh,
      ngay_ghi_tang: taisancodinh.ngay_ghi_tang?.format(DATE_FORMAT) ?? null,
      ngay_bat_dau_tinh_kh: taisancodinh.ngay_bat_dau_tinh_kh?.format(DATE_FORMAT) ?? null,
      createdDate: taisancodinh.createdDate?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restTaisancodinh: RestTaisancodinh): ITaisancodinh {
    return {
      ...restTaisancodinh,
      ngay_ghi_tang: restTaisancodinh.ngay_ghi_tang ? dayjs(restTaisancodinh.ngay_ghi_tang) : undefined,
      ngay_bat_dau_tinh_kh: restTaisancodinh.ngay_bat_dau_tinh_kh ? dayjs(restTaisancodinh.ngay_bat_dau_tinh_kh) : undefined,
      createdDate: restTaisancodinh.createdDate ? dayjs(restTaisancodinh.createdDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestTaisancodinh>): HttpResponse<ITaisancodinh> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestTaisancodinh[]>): HttpResponse<ITaisancodinh[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
