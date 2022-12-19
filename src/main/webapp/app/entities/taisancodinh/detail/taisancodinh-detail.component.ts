import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITaisancodinh } from '../taisancodinh.model';

@Component({
  selector: 'jhi-taisancodinh-detail',
  templateUrl: './taisancodinh-detail.component.html',
})
export class TaisancodinhDetailComponent implements OnInit {
  taisancodinh: ITaisancodinh | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ taisancodinh }) => {
      this.taisancodinh = taisancodinh;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
