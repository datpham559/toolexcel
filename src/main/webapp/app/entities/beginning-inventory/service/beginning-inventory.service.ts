import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IBeginningInventory, NewBeginningInventory } from '../beginning-inventory.model';

export type PartialUpdateBeginningInventory = Partial<IBeginningInventory> & Pick<IBeginningInventory, 'id'>;

type RestOf<T extends IBeginningInventory | NewBeginningInventory> = Omit<T, 'created_date'> & {
  created_date?: string | null;
};

export type RestBeginningInventory = RestOf<IBeginningInventory>;

export type NewRestBeginningInventory = RestOf<NewBeginningInventory>;

export type PartialUpdateRestBeginningInventory = RestOf<PartialUpdateBeginningInventory>;

export type EntityResponseType = HttpResponse<IBeginningInventory>;
export type EntityArrayResponseType = HttpResponse<IBeginningInventory[]>;

@Injectable({ providedIn: 'root' })
export class BeginningInventoryService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/beginning-inventories');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(beginningInventory: NewBeginningInventory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(beginningInventory);
    return this.http
      .post<RestBeginningInventory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(beginningInventory: IBeginningInventory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(beginningInventory);
    return this.http
      .put<RestBeginningInventory>(`${this.resourceUrl}/${this.getBeginningInventoryIdentifier(beginningInventory)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(beginningInventory: PartialUpdateBeginningInventory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(beginningInventory);
    return this.http
      .patch<RestBeginningInventory>(`${this.resourceUrl}/${this.getBeginningInventoryIdentifier(beginningInventory)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestBeginningInventory>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestBeginningInventory[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getBeginningInventoryIdentifier(beginningInventory: Pick<IBeginningInventory, 'id'>): number {
    return beginningInventory.id;
  }

  compareBeginningInventory(o1: Pick<IBeginningInventory, 'id'> | null, o2: Pick<IBeginningInventory, 'id'> | null): boolean {
    return o1 && o2 ? this.getBeginningInventoryIdentifier(o1) === this.getBeginningInventoryIdentifier(o2) : o1 === o2;
  }

  addBeginningInventoryToCollectionIfMissing<Type extends Pick<IBeginningInventory, 'id'>>(
    beginningInventoryCollection: Type[],
    ...beginningInventoriesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const beginningInventories: Type[] = beginningInventoriesToCheck.filter(isPresent);
    if (beginningInventories.length > 0) {
      const beginningInventoryCollectionIdentifiers = beginningInventoryCollection.map(
        beginningInventoryItem => this.getBeginningInventoryIdentifier(beginningInventoryItem)!
      );
      const beginningInventoriesToAdd = beginningInventories.filter(beginningInventoryItem => {
        const beginningInventoryIdentifier = this.getBeginningInventoryIdentifier(beginningInventoryItem);
        if (beginningInventoryCollectionIdentifiers.includes(beginningInventoryIdentifier)) {
          return false;
        }
        beginningInventoryCollectionIdentifiers.push(beginningInventoryIdentifier);
        return true;
      });
      return [...beginningInventoriesToAdd, ...beginningInventoryCollection];
    }
    return beginningInventoryCollection;
  }

  protected convertDateFromClient<T extends IBeginningInventory | NewBeginningInventory | PartialUpdateBeginningInventory>(
    beginningInventory: T
  ): RestOf<T> {
    return {
      ...beginningInventory,
      created_date: beginningInventory.created_date?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restBeginningInventory: RestBeginningInventory): IBeginningInventory {
    return {
      ...restBeginningInventory,
      created_date: restBeginningInventory.created_date ? dayjs(restBeginningInventory.created_date) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestBeginningInventory>): HttpResponse<IBeginningInventory> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestBeginningInventory[]>): HttpResponse<IBeginningInventory[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
