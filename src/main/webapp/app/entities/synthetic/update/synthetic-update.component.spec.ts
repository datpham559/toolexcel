import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SyntheticFormService } from './synthetic-form.service';
import { SyntheticService } from '../service/synthetic.service';
import { ISynthetic } from '../synthetic.model';

import { SyntheticUpdateComponent } from './synthetic-update.component';

describe('Synthetic Management Update Component', () => {
  let comp: SyntheticUpdateComponent;
  let fixture: ComponentFixture<SyntheticUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let syntheticFormService: SyntheticFormService;
  let syntheticService: SyntheticService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [SyntheticUpdateComponent],
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
      .overrideTemplate(SyntheticUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SyntheticUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    syntheticFormService = TestBed.inject(SyntheticFormService);
    syntheticService = TestBed.inject(SyntheticService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const synthetic: ISynthetic = { id: 456 };

      activatedRoute.data = of({ synthetic });
      comp.ngOnInit();

      expect(comp.synthetic).toEqual(synthetic);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISynthetic>>();
      const synthetic = { id: 123 };
      jest.spyOn(syntheticFormService, 'getSynthetic').mockReturnValue(synthetic);
      jest.spyOn(syntheticService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ synthetic });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: synthetic }));
      saveSubject.complete();

      // THEN
      expect(syntheticFormService.getSynthetic).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(syntheticService.update).toHaveBeenCalledWith(expect.objectContaining(synthetic));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISynthetic>>();
      const synthetic = { id: 123 };
      jest.spyOn(syntheticFormService, 'getSynthetic').mockReturnValue({ id: null });
      jest.spyOn(syntheticService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ synthetic: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: synthetic }));
      saveSubject.complete();

      // THEN
      expect(syntheticFormService.getSynthetic).toHaveBeenCalled();
      expect(syntheticService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISynthetic>>();
      const synthetic = { id: 123 };
      jest.spyOn(syntheticService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ synthetic });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(syntheticService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
