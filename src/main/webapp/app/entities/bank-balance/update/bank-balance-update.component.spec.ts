import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { BankBalanceFormService } from './bank-balance-form.service';
import { BankBalanceService } from '../service/bank-balance.service';
import { IBankBalance } from '../bank-balance.model';

import { BankBalanceUpdateComponent } from './bank-balance-update.component';

describe('BankBalance Management Update Component', () => {
  let comp: BankBalanceUpdateComponent;
  let fixture: ComponentFixture<BankBalanceUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let bankBalanceFormService: BankBalanceFormService;
  let bankBalanceService: BankBalanceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [BankBalanceUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(BankBalanceUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BankBalanceUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    bankBalanceFormService = TestBed.inject(BankBalanceFormService);
    bankBalanceService = TestBed.inject(BankBalanceService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const bankBalance: IBankBalance = { id: 456 };

      activatedRoute.data = of({ bankBalance });
      comp.ngOnInit();

      expect(comp.bankBalance).toEqual(bankBalance);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBankBalance>>();
      const bankBalance = { id: 123 };
      jest.spyOn(bankBalanceFormService, 'getBankBalance').mockReturnValue(bankBalance);
      jest.spyOn(bankBalanceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ bankBalance });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: bankBalance }));
      saveSubject.complete();

      // THEN
      expect(bankBalanceFormService.getBankBalance).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(bankBalanceService.update).toHaveBeenCalledWith(expect.objectContaining(bankBalance));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBankBalance>>();
      const bankBalance = { id: 123 };
      jest.spyOn(bankBalanceFormService, 'getBankBalance').mockReturnValue({ id: null });
      jest.spyOn(bankBalanceService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ bankBalance: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: bankBalance }));
      saveSubject.complete();

      // THEN
      expect(bankBalanceFormService.getBankBalance).toHaveBeenCalled();
      expect(bankBalanceService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBankBalance>>();
      const bankBalance = { id: 123 };
      jest.spyOn(bankBalanceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ bankBalance });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(bankBalanceService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
