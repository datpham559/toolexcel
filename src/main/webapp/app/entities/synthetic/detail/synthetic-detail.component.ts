import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISynthetic } from '../synthetic.model';

@Component({
  selector: 'jhi-synthetic-detail',
  templateUrl: './synthetic-detail.component.html',
})
export class SyntheticDetailComponent implements OnInit {
  synthetic: ISynthetic | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ synthetic }) => {
      this.synthetic = synthetic;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
