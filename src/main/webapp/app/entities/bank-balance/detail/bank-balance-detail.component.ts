import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBankBalance } from '../bank-balance.model';

@Component({
  selector: 'jhi-bank-balance-detail',
  templateUrl: './bank-balance-detail.component.html',
})
export class BankBalanceDetailComponent implements OnInit {
  bankBalance: IBankBalance | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bankBalance }) => {
      this.bankBalance = bankBalance;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
