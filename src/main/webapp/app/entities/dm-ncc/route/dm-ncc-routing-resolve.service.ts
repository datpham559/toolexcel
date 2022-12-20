import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDmNCC } from '../dm-ncc.model';
import { DmNCCService } from '../service/dm-ncc.service';

@Injectable({ providedIn: 'root' })
export class DmNCCRoutingResolveService implements Resolve<IDmNCC | null> {
  constructor(protected service: DmNCCService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDmNCC | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((dmNCC: HttpResponse<IDmNCC>) => {
          if (dmNCC.body) {
            return of(dmNCC.body);
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
