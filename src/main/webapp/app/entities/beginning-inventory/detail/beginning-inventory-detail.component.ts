import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBeginningInventory } from '../beginning-inventory.model';

@Component({
  selector: 'jhi-beginning-inventory-detail',
  templateUrl: './beginning-inventory-detail.component.html',
})
export class BeginningInventoryDetailComponent implements OnInit {
  beginningInventory: IBeginningInventory | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ beginningInventory }) => {
      this.beginningInventory = beginningInventory;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
