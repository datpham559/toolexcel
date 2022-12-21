import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { MerchandiseFormService } from './merchandise-form.service';
import { MerchandiseService } from '../service/merchandise.service';
import { IMerchandise } from '../merchandise.model';

import { MerchandiseUpdateComponent } from './merchandise-update.component';

describe('Merchandise Management Update Component', () => {
  let comp: MerchandiseUpdateComponent;
  let fixture: ComponentFixture<MerchandiseUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let merchandiseFormService: MerchandiseFormService;
  let merchandiseService: MerchandiseService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [MerchandiseUpdateComponent],
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
      .overrideTemplate(MerchandiseUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MerchandiseUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    merchandiseFormService = TestBed.inject(MerchandiseFormService);
    merchandiseService = TestBed.inject(MerchandiseService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const merchandise: IMerchandise = { id: 456 };

      activatedRoute.data = of({ merchandise });
      comp.ngOnInit();

      expect(comp.merchandise).toEqual(merchandise);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMerchandise>>();
      const merchandise = { id: 123 };
      jest.spyOn(merchandiseFormService, 'getMerchandise').mockReturnValue(merchandise);
      jest.spyOn(merchandiseService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ merchandise });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: merchandise }));
      saveSubject.complete();

      // THEN
      expect(merchandiseFormService.getMerchandise).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(merchandiseService.update).toHaveBeenCalledWith(expect.objectContaining(merchandise));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMerchandise>>();
      const merchandise = { id: 123 };
      jest.spyOn(merchandiseFormService, 'getMerchandise').mockReturnValue({ id: null });
      jest.spyOn(merchandiseService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ merchandise: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: merchandise }));
      saveSubject.complete();

      // THEN
      expect(merchandiseFormService.getMerchandise).toHaveBeenCalled();
      expect(merchandiseService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMerchandise>>();
      const merchandise = { id: 123 };
      jest.spyOn(merchandiseService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ merchandise });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(merchandiseService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
