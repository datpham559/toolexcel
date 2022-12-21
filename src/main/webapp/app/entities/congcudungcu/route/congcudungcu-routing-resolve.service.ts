import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICongcudungcu } from '../congcudungcu.model';
import { CongcudungcuService } from '../service/congcudungcu.service';

@Injectable({ providedIn: 'root' })
export class CongcudungcuRoutingResolveService implements Resolve<ICongcudungcu | null> {
  constructor(protected service: CongcudungcuService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICongcudungcu | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((congcudungcu: HttpResponse<ICongcudungcu>) => {
          if (congcudungcu.body) {
            return of(congcudungcu.body);
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
