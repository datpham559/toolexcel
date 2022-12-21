import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITonkhodauky } from '../tonkhodauky.model';
import { TonkhodaukyService } from '../service/tonkhodauky.service';

@Injectable({ providedIn: 'root' })
export class TonkhodaukyRoutingResolveService implements Resolve<ITonkhodauky | null> {
  constructor(protected service: TonkhodaukyService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITonkhodauky | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tonkhodauky: HttpResponse<ITonkhodauky>) => {
          if (tonkhodauky.body) {
            return of(tonkhodauky.body);
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
