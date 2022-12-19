import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CongcudungcuFormService } from './congcudungcu-form.service';
import { CongcudungcuService } from '../service/congcudungcu.service';
import { ICongcudungcu } from '../congcudungcu.model';

import { CongcudungcuUpdateComponent } from './congcudungcu-update.component';

describe('Congcudungcu Management Update Component', () => {
  let comp: CongcudungcuUpdateComponent;
  let fixture: ComponentFixture<CongcudungcuUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let congcudungcuFormService: CongcudungcuFormService;
  let congcudungcuService: CongcudungcuService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CongcudungcuUpdateComponent],
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
      .overrideTemplate(CongcudungcuUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CongcudungcuUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    congcudungcuFormService = TestBed.inject(CongcudungcuFormService);
    congcudungcuService = TestBed.inject(CongcudungcuService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const congcudungcu: ICongcudungcu = { id: 456 };

      activatedRoute.data = of({ congcudungcu });
      comp.ngOnInit();

      expect(comp.congcudungcu).toEqual(congcudungcu);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICongcudungcu>>();
      const congcudungcu = { id: 123 };
      jest.spyOn(congcudungcuFormService, 'getCongcudungcu').mockReturnValue(congcudungcu);
      jest.spyOn(congcudungcuService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ congcudungcu });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: congcudungcu }));
      saveSubject.complete();

      // THEN
      expect(congcudungcuFormService.getCongcudungcu).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(congcudungcuService.update).toHaveBeenCalledWith(expect.objectContaining(congcudungcu));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICongcudungcu>>();
      const congcudungcu = { id: 123 };
      jest.spyOn(congcudungcuFormService, 'getCongcudungcu').mockReturnValue({ id: null });
      jest.spyOn(congcudungcuService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ congcudungcu: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: congcudungcu }));
      saveSubject.complete();

      // THEN
      expect(congcudungcuFormService.getCongcudungcu).toHaveBeenCalled();
      expect(congcudungcuService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICongcudungcu>>();
      const congcudungcu = { id: 123 };
      jest.spyOn(congcudungcuService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ congcudungcu });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(congcudungcuService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
