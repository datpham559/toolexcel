import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITonkhodauky, NewTonkhodauky } from '../tonkhodauky.model';

export type PartialUpdateTonkhodauky = Partial<ITonkhodauky> & Pick<ITonkhodauky, 'id'>;

type RestOf<T extends ITonkhodauky | NewTonkhodauky> = Omit<T, 'createdDate'> & {
  createdDate?: string | null;
};

export type RestTonkhodauky = RestOf<ITonkhodauky>;

export type NewRestTonkhodauky = RestOf<NewTonkhodauky>;

export type PartialUpdateRestTonkhodauky = RestOf<PartialUpdateTonkhodauky>;

export type EntityResponseType = HttpResponse<ITonkhodauky>;
export type EntityArrayResponseType = HttpResponse<ITonkhodauky[]>;

@Injectable({ providedIn: 'root' })
export class TonkhodaukyService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/tonkhodaukies');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(tonkhodauky: NewTonkhodauky): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tonkhodauky);
    return this.http
      .post<RestTonkhodauky>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(tonkhodauky: ITonkhodauky): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tonkhodauky);
    return this.http
      .put<RestTonkhodauky>(`${this.resourceUrl}/${this.getTonkhodaukyIdentifier(tonkhodauky)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(tonkhodauky: PartialUpdateTonkhodauky): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tonkhodauky);
    return this.http
      .patch<RestTonkhodauky>(`${this.resourceUrl}/${this.getTonkhodaukyIdentifier(tonkhodauky)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestTonkhodauky>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestTonkhodauky[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getTonkhodaukyIdentifier(tonkhodauky: Pick<ITonkhodauky, 'id'>): number {
    return tonkhodauky.id;
  }

  compareTonkhodauky(o1: Pick<ITonkhodauky, 'id'> | null, o2: Pick<ITonkhodauky, 'id'> | null): boolean {
    return o1 && o2 ? this.getTonkhodaukyIdentifier(o1) === this.getTonkhodaukyIdentifier(o2) : o1 === o2;
  }

  addTonkhodaukyToCollectionIfMissing<Type extends Pick<ITonkhodauky, 'id'>>(
    tonkhodaukyCollection: Type[],
    ...tonkhodaukiesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const tonkhodaukies: Type[] = tonkhodaukiesToCheck.filter(isPresent);
    if (tonkhodaukies.length > 0) {
      const tonkhodaukyCollectionIdentifiers = tonkhodaukyCollection.map(
        tonkhodaukyItem => this.getTonkhodaukyIdentifier(tonkhodaukyItem)!
      );
      const tonkhodaukiesToAdd = tonkhodaukies.filter(tonkhodaukyItem => {
        const tonkhodaukyIdentifier = this.getTonkhodaukyIdentifier(tonkhodaukyItem);
        if (tonkhodaukyCollectionIdentifiers.includes(tonkhodaukyIdentifier)) {
          return false;
        }
        tonkhodaukyCollectionIdentifiers.push(tonkhodaukyIdentifier);
        return true;
      });
      return [...tonkhodaukiesToAdd, ...tonkhodaukyCollection];
    }
    return tonkhodaukyCollection;
  }

  protected convertDateFromClient<T extends ITonkhodauky | NewTonkhodauky | PartialUpdateTonkhodauky>(tonkhodauky: T): RestOf<T> {
    return {
      ...tonkhodauky,
      createdDate: tonkhodauky.createdDate?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restTonkhodauky: RestTonkhodauky): ITonkhodauky {
    return {
      ...restTonkhodauky,
      createdDate: restTonkhodauky.createdDate ? dayjs(restTonkhodauky.createdDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestTonkhodauky>): HttpResponse<ITonkhodauky> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestTonkhodauky[]>): HttpResponse<ITonkhodauky[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
