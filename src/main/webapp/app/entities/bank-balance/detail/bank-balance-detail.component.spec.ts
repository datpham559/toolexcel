import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BankBalanceDetailComponent } from './bank-balance-detail.component';

describe('BankBalance Management Detail Component', () => {
  let comp: BankBalanceDetailComponent;
  let fixture: ComponentFixture<BankBalanceDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BankBalanceDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ bankBalance: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(BankBalanceDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(BankBalanceDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load bankBalance on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.bankBalance).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
