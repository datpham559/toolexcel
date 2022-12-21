import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDmNCC } from '../dm-ncc.model';

@Component({
  selector: 'jhi-dm-ncc-detail',
  templateUrl: './dm-ncc-detail.component.html',
})
export class DmNCCDetailComponent implements OnInit {
  dmNCC: IDmNCC | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dmNCC }) => {
      this.dmNCC = dmNCC;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
