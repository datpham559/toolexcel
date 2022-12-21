import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TaisancodinhDetailComponent } from './taisancodinh-detail.component';

describe('Taisancodinh Management Detail Component', () => {
  let comp: TaisancodinhDetailComponent;
  let fixture: ComponentFixture<TaisancodinhDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TaisancodinhDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ taisancodinh: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(TaisancodinhDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(TaisancodinhDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load taisancodinh on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.taisancodinh).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
