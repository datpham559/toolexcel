import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICongcudungcu } from '../congcudungcu.model';

@Component({
  selector: 'jhi-congcudungcu-detail',
  templateUrl: './congcudungcu-detail.component.html',
})
export class CongcudungcuDetailComponent implements OnInit {
  congcudungcu: ICongcudungcu | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ congcudungcu }) => {
      this.congcudungcu = congcudungcu;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
