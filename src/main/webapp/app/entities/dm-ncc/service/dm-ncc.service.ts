import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDmNCC, NewDmNCC } from '../dm-ncc.model';

export type PartialUpdateDmNCC = Partial<IDmNCC> & Pick<IDmNCC, 'id'>;

type RestOf<T extends IDmNCC | NewDmNCC> = Omit<T, 'created_date'> & {
  created_date?: string | null;
};

export type RestDmNCC = RestOf<IDmNCC>;

export type NewRestDmNCC = RestOf<NewDmNCC>;

export type PartialUpdateRestDmNCC = RestOf<PartialUpdateDmNCC>;

export type EntityResponseType = HttpResponse<IDmNCC>;
export type EntityArrayResponseType = HttpResponse<IDmNCC[]>;

@Injectable({ providedIn: 'root' })
export class DmNCCService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/dm-nccs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(dmNCC: NewDmNCC): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dmNCC);
    return this.http.post<RestDmNCC>(this.resourceUrl, copy, { observe: 'response' }).pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(dmNCC: IDmNCC): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dmNCC);
    return this.http
      .put<RestDmNCC>(`${this.resourceUrl}/${this.getDmNCCIdentifier(dmNCC)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(dmNCC: PartialUpdateDmNCC): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dmNCC);
    return this.http
      .patch<RestDmNCC>(`${this.resourceUrl}/${this.getDmNCCIdentifier(dmNCC)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestDmNCC>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestDmNCC[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getDmNCCIdentifier(dmNCC: Pick<IDmNCC, 'id'>): number {
    return dmNCC.id;
  }

  compareDmNCC(o1: Pick<IDmNCC, 'id'> | null, o2: Pick<IDmNCC, 'id'> | null): boolean {
    return o1 && o2 ? this.getDmNCCIdentifier(o1) === this.getDmNCCIdentifier(o2) : o1 === o2;
  }

  addDmNCCToCollectionIfMissing<Type extends Pick<IDmNCC, 'id'>>(
    dmNCCCollection: Type[],
    ...dmNCCSToCheck: (Type | null | undefined)[]
  ): Type[] {
    const dmNCCS: Type[] = dmNCCSToCheck.filter(isPresent);
    if (dmNCCS.length > 0) {
      const dmNCCCollectionIdentifiers = dmNCCCollection.map(dmNCCItem => this.getDmNCCIdentifier(dmNCCItem)!);
      const dmNCCSToAdd = dmNCCS.filter(dmNCCItem => {
        const dmNCCIdentifier = this.getDmNCCIdentifier(dmNCCItem);
        if (dmNCCCollectionIdentifiers.includes(dmNCCIdentifier)) {
          return false;
        }
        dmNCCCollectionIdentifiers.push(dmNCCIdentifier);
        return true;
      });
      return [...dmNCCSToAdd, ...dmNCCCollection];
    }
    return dmNCCCollection;
  }

  protected convertDateFromClient<T extends IDmNCC | NewDmNCC | PartialUpdateDmNCC>(dmNCC: T): RestOf<T> {
    return {
      ...dmNCC,
      created_date: dmNCC.created_date?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restDmNCC: RestDmNCC): IDmNCC {
    return {
      ...restDmNCC,
      created_date: restDmNCC.created_date ? dayjs(restDmNCC.created_date) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestDmNCC>): HttpResponse<IDmNCC> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestDmNCC[]>): HttpResponse<IDmNCC[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
