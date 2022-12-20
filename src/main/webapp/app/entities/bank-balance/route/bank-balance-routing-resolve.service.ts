import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IBankBalance } from '../bank-balance.model';
import { BankBalanceService } from '../service/bank-balance.service';

@Injectable({ providedIn: 'root' })
export class BankBalanceRoutingResolveService implements Resolve<IBankBalance | null> {
  constructor(protected service: BankBalanceService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBankBalance | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((bankBalance: HttpResponse<IBankBalance>) => {
          if (bankBalance.body) {
            return of(bankBalance.body);
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
