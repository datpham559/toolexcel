import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICongnophaithu } from '../congnophaithu.model';

@Component({
  selector: 'jhi-congnophaithu-detail',
  templateUrl: './congnophaithu-detail.component.html',
})
export class CongnophaithuDetailComponent implements OnInit {
  congnophaithu: ICongnophaithu | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ congnophaithu }) => {
      this.congnophaithu = congnophaithu;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
