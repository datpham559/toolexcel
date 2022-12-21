import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IMerchandise } from '../merchandise.model';
import { MerchandiseService } from '../service/merchandise.service';

@Injectable({ providedIn: 'root' })
export class MerchandiseRoutingResolveService implements Resolve<IMerchandise | null> {
  constructor(protected service: MerchandiseService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMerchandise | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((merchandise: HttpResponse<IMerchandise>) => {
          if (merchandise.body) {
            return of(merchandise.body);
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
