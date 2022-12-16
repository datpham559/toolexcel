import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CongnophaitraFormService } from './congnophaitra-form.service';
import { CongnophaitraService } from '../service/congnophaitra.service';
import { ICongnophaitra } from '../congnophaitra.model';

import { CongnophaitraUpdateComponent } from './congnophaitra-update.component';

describe('Congnophaitra Management Update Component', () => {
  let comp: CongnophaitraUpdateComponent;
  let fixture: ComponentFixture<CongnophaitraUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let congnophaitraFormService: CongnophaitraFormService;
  let congnophaitraService: CongnophaitraService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CongnophaitraUpdateComponent],
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
      .overrideTemplate(CongnophaitraUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CongnophaitraUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    congnophaitraFormService = TestBed.inject(CongnophaitraFormService);
    congnophaitraService = TestBed.inject(CongnophaitraService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const congnophaitra: ICongnophaitra = { id: 456 };

      activatedRoute.data = of({ congnophaitra });
      comp.ngOnInit();

      expect(comp.congnophaitra).toEqual(congnophaitra);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICongnophaitra>>();
      const congnophaitra = { id: 123 };
      jest.spyOn(congnophaitraFormService, 'getCongnophaitra').mockReturnValue(congnophaitra);
      jest.spyOn(congnophaitraService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ congnophaitra });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: congnophaitra }));
      saveSubject.complete();

      // THEN
      expect(congnophaitraFormService.getCongnophaitra).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(congnophaitraService.update).toHaveBeenCalledWith(expect.objectContaining(congnophaitra));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICongnophaitra>>();
      const congnophaitra = { id: 123 };
      jest.spyOn(congnophaitraFormService, 'getCongnophaitra').mockReturnValue({ id: null });
      jest.spyOn(congnophaitraService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ congnophaitra: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: congnophaitra }));
      saveSubject.complete();

      // THEN
      expect(congnophaitraFormService.getCongnophaitra).toHaveBeenCalled();
      expect(congnophaitraService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICongnophaitra>>();
      const congnophaitra = { id: 123 };
      jest.spyOn(congnophaitraService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ congnophaitra });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(congnophaitraService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
