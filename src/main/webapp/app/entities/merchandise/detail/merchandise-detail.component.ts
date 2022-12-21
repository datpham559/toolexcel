import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMerchandise } from '../merchandise.model';

@Component({
  selector: 'jhi-merchandise-detail',
  templateUrl: './merchandise-detail.component.html',
})
export class MerchandiseDetailComponent implements OnInit {
  merchandise: IMerchandise | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ merchandise }) => {
      this.merchandise = merchandise;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
