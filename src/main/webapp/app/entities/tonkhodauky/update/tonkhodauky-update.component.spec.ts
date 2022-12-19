import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { TonkhodaukyFormService } from './tonkhodauky-form.service';
import { TonkhodaukyService } from '../service/tonkhodauky.service';
import { ITonkhodauky } from '../tonkhodauky.model';

import { TonkhodaukyUpdateComponent } from './tonkhodauky-update.component';

describe('Tonkhodauky Management Update Component', () => {
  let comp: TonkhodaukyUpdateComponent;
  let fixture: ComponentFixture<TonkhodaukyUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let tonkhodaukyFormService: TonkhodaukyFormService;
  let tonkhodaukyService: TonkhodaukyService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [TonkhodaukyUpdateComponent],
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
      .overrideTemplate(TonkhodaukyUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TonkhodaukyUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    tonkhodaukyFormService = TestBed.inject(TonkhodaukyFormService);
    tonkhodaukyService = TestBed.inject(TonkhodaukyService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const tonkhodauky: ITonkhodauky = { id: 456 };

      activatedRoute.data = of({ tonkhodauky });
      comp.ngOnInit();

      expect(comp.tonkhodauky).toEqual(tonkhodauky);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITonkhodauky>>();
      const tonkhodauky = { id: 123 };
      jest.spyOn(tonkhodaukyFormService, 'getTonkhodauky').mockReturnValue(tonkhodauky);
      jest.spyOn(tonkhodaukyService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tonkhodauky });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: tonkhodauky }));
      saveSubject.complete();

      // THEN
      expect(tonkhodaukyFormService.getTonkhodauky).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(tonkhodaukyService.update).toHaveBeenCalledWith(expect.objectContaining(tonkhodauky));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITonkhodauky>>();
      const tonkhodauky = { id: 123 };
      jest.spyOn(tonkhodaukyFormService, 'getTonkhodauky').mockReturnValue({ id: null });
      jest.spyOn(tonkhodaukyService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tonkhodauky: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: tonkhodauky }));
      saveSubject.complete();

      // THEN
      expect(tonkhodaukyFormService.getTonkhodauky).toHaveBeenCalled();
      expect(tonkhodaukyService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITonkhodauky>>();
      const tonkhodauky = { id: 123 };
      jest.spyOn(tonkhodaukyService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tonkhodauky });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(tonkhodaukyService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
