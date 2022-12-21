import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { BankBalanceService } from '../service/bank-balance.service';

import { BankBalanceComponent } from './bank-balance.component';

describe('BankBalance Management Component', () => {
  let comp: BankBalanceComponent;
  let fixture: ComponentFixture<BankBalanceComponent>;
  let service: BankBalanceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'bank-balance', component: BankBalanceComponent }]), HttpClientTestingModule],
      declarations: [BankBalanceComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              })
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(BankBalanceComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BankBalanceComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(BankBalanceService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.bankBalances?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to bankBalanceService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getBankBalanceIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getBankBalanceIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
