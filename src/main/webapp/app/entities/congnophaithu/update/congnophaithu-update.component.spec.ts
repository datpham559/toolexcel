import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CongnophaithuFormService } from './congnophaithu-form.service';
import { CongnophaithuService } from '../service/congnophaithu.service';
import { ICongnophaithu } from '../congnophaithu.model';

import { CongnophaithuUpdateComponent } from './congnophaithu-update.component';

describe('Congnophaithu Management Update Component', () => {
  let comp: CongnophaithuUpdateComponent;
  let fixture: ComponentFixture<CongnophaithuUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let congnophaithuFormService: CongnophaithuFormService;
  let congnophaithuService: CongnophaithuService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CongnophaithuUpdateComponent],
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
      .overrideTemplate(CongnophaithuUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CongnophaithuUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    congnophaithuFormService = TestBed.inject(CongnophaithuFormService);
    congnophaithuService = TestBed.inject(CongnophaithuService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const congnophaithu: ICongnophaithu = { id: 456 };

      activatedRoute.data = of({ congnophaithu });
      comp.ngOnInit();

      expect(comp.congnophaithu).toEqual(congnophaithu);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICongnophaithu>>();
      const congnophaithu = { id: 123 };
      jest.spyOn(congnophaithuFormService, 'getCongnophaithu').mockReturnValue(congnophaithu);
      jest.spyOn(congnophaithuService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ congnophaithu });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: congnophaithu }));
      saveSubject.complete();

      // THEN
      expect(congnophaithuFormService.getCongnophaithu).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(congnophaithuService.update).toHaveBeenCalledWith(expect.objectContaining(congnophaithu));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICongnophaithu>>();
      const congnophaithu = { id: 123 };
      jest.spyOn(congnophaithuFormService, 'getCongnophaithu').mockReturnValue({ id: null });
      jest.spyOn(congnophaithuService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ congnophaithu: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: congnophaithu }));
      saveSubject.complete();

      // THEN
      expect(congnophaithuFormService.getCongnophaithu).toHaveBeenCalled();
      expect(congnophaithuService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICongnophaithu>>();
      const congnophaithu = { id: 123 };
      jest.spyOn(congnophaithuService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ congnophaithu });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(congnophaithuService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
