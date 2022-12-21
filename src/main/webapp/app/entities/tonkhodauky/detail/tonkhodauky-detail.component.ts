import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITonkhodauky } from '../tonkhodauky.model';

@Component({
  selector: 'jhi-tonkhodauky-detail',
  templateUrl: './tonkhodauky-detail.component.html',
})
export class TonkhodaukyDetailComponent implements OnInit {
  tonkhodauky: ITonkhodauky | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tonkhodauky }) => {
      this.tonkhodauky = tonkhodauky;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
