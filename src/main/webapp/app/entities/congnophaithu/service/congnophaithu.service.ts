import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICongnophaithu, NewCongnophaithu } from '../congnophaithu.model';

export type PartialUpdateCongnophaithu = Partial<ICongnophaithu> & Pick<ICongnophaithu, 'id'>;

type RestOf<T extends ICongnophaithu | NewCongnophaithu> = Omit<T, 'createdDate'> & {
  createdDate?: string | null;
};

export type RestCongnophaithu = RestOf<ICongnophaithu>;

export type NewRestCongnophaithu = RestOf<NewCongnophaithu>;

export type PartialUpdateRestCongnophaithu = RestOf<PartialUpdateCongnophaithu>;

export type EntityResponseType = HttpResponse<ICongnophaithu>;
export type EntityArrayResponseType = HttpResponse<ICongnophaithu[]>;

@Injectable({ providedIn: 'root' })
export class CongnophaithuService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/congnophaithus');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(congnophaithu: NewCongnophaithu): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(congnophaithu);
    return this.http
      .post<RestCongnophaithu>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(congnophaithu: ICongnophaithu): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(congnophaithu);
    return this.http
      .put<RestCongnophaithu>(`${this.resourceUrl}/${this.getCongnophaithuIdentifier(congnophaithu)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(congnophaithu: PartialUpdateCongnophaithu): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(congnophaithu);
    return this.http
      .patch<RestCongnophaithu>(`${this.resourceUrl}/${this.getCongnophaithuIdentifier(congnophaithu)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestCongnophaithu>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestCongnophaithu[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCongnophaithuIdentifier(congnophaithu: Pick<ICongnophaithu, 'id'>): number {
    return congnophaithu.id;
  }

  compareCongnophaithu(o1: Pick<ICongnophaithu, 'id'> | null, o2: Pick<ICongnophaithu, 'id'> | null): boolean {
    return o1 && o2 ? this.getCongnophaithuIdentifier(o1) === this.getCongnophaithuIdentifier(o2) : o1 === o2;
  }

  addCongnophaithuToCollectionIfMissing<Type extends Pick<ICongnophaithu, 'id'>>(
    congnophaithuCollection: Type[],
    ...congnophaithusToCheck: (Type | null | undefined)[]
  ): Type[] {
    const congnophaithus: Type[] = congnophaithusToCheck.filter(isPresent);
    if (congnophaithus.length > 0) {
      const congnophaithuCollectionIdentifiers = congnophaithuCollection.map(
        congnophaithuItem => this.getCongnophaithuIdentifier(congnophaithuItem)!
      );
      const congnophaithusToAdd = congnophaithus.filter(congnophaithuItem => {
        const congnophaithuIdentifier = this.getCongnophaithuIdentifier(congnophaithuItem);
        if (congnophaithuCollectionIdentifiers.includes(congnophaithuIdentifier)) {
          return false;
        }
        congnophaithuCollectionIdentifiers.push(congnophaithuIdentifier);
        return true;
      });
      return [...congnophaithusToAdd, ...congnophaithuCollection];
    }
    return congnophaithuCollection;
  }

  protected convertDateFromClient<T extends ICongnophaithu | NewCongnophaithu | PartialUpdateCongnophaithu>(congnophaithu: T): RestOf<T> {
    return {
      ...congnophaithu,
      createdDate: congnophaithu.createdDate?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restCongnophaithu: RestCongnophaithu): ICongnophaithu {
    return {
      ...restCongnophaithu,
      createdDate: restCongnophaithu.createdDate ? dayjs(restCongnophaithu.createdDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestCongnophaithu>): HttpResponse<ICongnophaithu> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestCongnophaithu[]>): HttpResponse<ICongnophaithu[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
