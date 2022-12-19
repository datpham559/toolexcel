import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICongnophaitra, NewCongnophaitra } from '../congnophaitra.model';

export type PartialUpdateCongnophaitra = Partial<ICongnophaitra> & Pick<ICongnophaitra, 'id'>;

type RestOf<T extends ICongnophaitra | NewCongnophaitra> = Omit<T, 'created_Date'> & {
  created_Date?: string | null;
};

export type RestCongnophaitra = RestOf<ICongnophaitra>;

export type NewRestCongnophaitra = RestOf<NewCongnophaitra>;

export type PartialUpdateRestCongnophaitra = RestOf<PartialUpdateCongnophaitra>;

export type EntityResponseType = HttpResponse<ICongnophaitra>;
export type EntityArrayResponseType = HttpResponse<ICongnophaitra[]>;

@Injectable({ providedIn: 'root' })
export class CongnophaitraService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/congnophaitras');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(congnophaitra: NewCongnophaitra): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(congnophaitra);
    return this.http
      .post<RestCongnophaitra>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(congnophaitra: ICongnophaitra): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(congnophaitra);
    return this.http
      .put<RestCongnophaitra>(`${this.resourceUrl}/${this.getCongnophaitraIdentifier(congnophaitra)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(congnophaitra: PartialUpdateCongnophaitra): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(congnophaitra);
    return this.http
      .patch<RestCongnophaitra>(`${this.resourceUrl}/${this.getCongnophaitraIdentifier(congnophaitra)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestCongnophaitra>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestCongnophaitra[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCongnophaitraIdentifier(congnophaitra: Pick<ICongnophaitra, 'id'>): number {
    return congnophaitra.id;
  }

  compareCongnophaitra(o1: Pick<ICongnophaitra, 'id'> | null, o2: Pick<ICongnophaitra, 'id'> | null): boolean {
    return o1 && o2 ? this.getCongnophaitraIdentifier(o1) === this.getCongnophaitraIdentifier(o2) : o1 === o2;
  }

  addCongnophaitraToCollectionIfMissing<Type extends Pick<ICongnophaitra, 'id'>>(
    congnophaitraCollection: Type[],
    ...congnophaitrasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const congnophaitras: Type[] = congnophaitrasToCheck.filter(isPresent);
    if (congnophaitras.length > 0) {
      const congnophaitraCollectionIdentifiers = congnophaitraCollection.map(
        congnophaitraItem => this.getCongnophaitraIdentifier(congnophaitraItem)!
      );
      const congnophaitrasToAdd = congnophaitras.filter(congnophaitraItem => {
        const congnophaitraIdentifier = this.getCongnophaitraIdentifier(congnophaitraItem);
        if (congnophaitraCollectionIdentifiers.includes(congnophaitraIdentifier)) {
          return false;
        }
        congnophaitraCollectionIdentifiers.push(congnophaitraIdentifier);
        return true;
      });
      return [...congnophaitrasToAdd, ...congnophaitraCollection];
    }
    return congnophaitraCollection;
  }

  protected convertDateFromClient<T extends ICongnophaitra | NewCongnophaitra | PartialUpdateCongnophaitra>(congnophaitra: T): RestOf<T> {
    return {
      ...congnophaitra,
      created_Date: congnophaitra.created_Date?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restCongnophaitra: RestCongnophaitra): ICongnophaitra {
    return {
      ...restCongnophaitra,
      created_Date: restCongnophaitra.created_Date ? dayjs(restCongnophaitra.created_Date) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestCongnophaitra>): HttpResponse<ICongnophaitra> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestCongnophaitra[]>): HttpResponse<ICongnophaitra[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
