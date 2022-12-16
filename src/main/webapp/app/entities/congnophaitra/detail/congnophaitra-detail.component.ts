import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICongnophaitra } from '../congnophaitra.model';

@Component({
  selector: 'jhi-congnophaitra-detail',
  templateUrl: './congnophaitra-detail.component.html',
})
export class CongnophaitraDetailComponent implements OnInit {
  congnophaitra: ICongnophaitra | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ congnophaitra }) => {
      this.congnophaitra = congnophaitra;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
