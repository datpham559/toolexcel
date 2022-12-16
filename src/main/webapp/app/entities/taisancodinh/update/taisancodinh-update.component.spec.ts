import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { TaisancodinhFormService } from './taisancodinh-form.service';
import { TaisancodinhService } from '../service/taisancodinh.service';
import { ITaisancodinh } from '../taisancodinh.model';

import { TaisancodinhUpdateComponent } from './taisancodinh-update.component';

describe('Taisancodinh Management Update Component', () => {
  let comp: TaisancodinhUpdateComponent;
  let fixture: ComponentFixture<TaisancodinhUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let taisancodinhFormService: TaisancodinhFormService;
  let taisancodinhService: TaisancodinhService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [TaisancodinhUpdateComponent],
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
      .overrideTemplate(TaisancodinhUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TaisancodinhUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    taisancodinhFormService = TestBed.inject(TaisancodinhFormService);
    taisancodinhService = TestBed.inject(TaisancodinhService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const taisancodinh: ITaisancodinh = { id: 456 };

      activatedRoute.data = of({ taisancodinh });
      comp.ngOnInit();

      expect(comp.taisancodinh).toEqual(taisancodinh);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITaisancodinh>>();
      const taisancodinh = { id: 123 };
      jest.spyOn(taisancodinhFormService, 'getTaisancodinh').mockReturnValue(taisancodinh);
      jest.spyOn(taisancodinhService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ taisancodinh });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: taisancodinh }));
      saveSubject.complete();

      // THEN
      expect(taisancodinhFormService.getTaisancodinh).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(taisancodinhService.update).toHaveBeenCalledWith(expect.objectContaining(taisancodinh));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITaisancodinh>>();
      const taisancodinh = { id: 123 };
      jest.spyOn(taisancodinhFormService, 'getTaisancodinh').mockReturnValue({ id: null });
      jest.spyOn(taisancodinhService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ taisancodinh: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: taisancodinh }));
      saveSubject.complete();

      // THEN
      expect(taisancodinhFormService.getTaisancodinh).toHaveBeenCalled();
      expect(taisancodinhService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITaisancodinh>>();
      const taisancodinh = { id: 123 };
      jest.spyOn(taisancodinhService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ taisancodinh });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(taisancodinhService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
