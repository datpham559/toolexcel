import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICongnophaitra } from '../congnophaitra.model';
import { CongnophaitraService } from '../service/congnophaitra.service';

@Injectable({ providedIn: 'root' })
export class CongnophaitraRoutingResolveService implements Resolve<ICongnophaitra | null> {
  constructor(protected service: CongnophaitraService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICongnophaitra | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((congnophaitra: HttpResponse<ICongnophaitra>) => {
          if (congnophaitra.body) {
            return of(congnophaitra.body);
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
