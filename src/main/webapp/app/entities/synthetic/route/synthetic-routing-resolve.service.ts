import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISynthetic } from '../synthetic.model';
import { SyntheticService } from '../service/synthetic.service';

@Injectable({ providedIn: 'root' })
export class SyntheticRoutingResolveService implements Resolve<ISynthetic | null> {
  constructor(protected service: SyntheticService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISynthetic | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((synthetic: HttpResponse<ISynthetic>) => {
          if (synthetic.body) {
            return of(synthetic.body);
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
