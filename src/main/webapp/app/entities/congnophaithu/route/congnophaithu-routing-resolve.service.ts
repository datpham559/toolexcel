import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICongnophaithu } from '../congnophaithu.model';
import { CongnophaithuService } from '../service/congnophaithu.service';

@Injectable({ providedIn: 'root' })
export class CongnophaithuRoutingResolveService implements Resolve<ICongnophaithu | null> {
  constructor(protected service: CongnophaithuService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICongnophaithu | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((congnophaithu: HttpResponse<ICongnophaithu>) => {
          if (congnophaithu.body) {
            return of(congnophaithu.body);
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
