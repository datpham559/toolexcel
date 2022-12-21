import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IBeginningInventory } from '../beginning-inventory.model';
import { BeginningInventoryService } from '../service/beginning-inventory.service';

@Injectable({ providedIn: 'root' })
export class BeginningInventoryRoutingResolveService implements Resolve<IBeginningInventory | null> {
  constructor(protected service: BeginningInventoryService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBeginningInventory | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((beginningInventory: HttpResponse<IBeginningInventory>) => {
          if (beginningInventory.body) {
            return of(beginningInventory.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
