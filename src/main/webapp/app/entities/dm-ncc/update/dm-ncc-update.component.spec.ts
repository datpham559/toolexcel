import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DmNCCFormService } from './dm-ncc-form.service';
import { DmNCCService } from '../service/dm-ncc.service';
import { IDmNCC } from '../dm-ncc.model';

import { DmNCCUpdateComponent } from './dm-ncc-update.component';

describe('DmNCC Management Update Component', () => {
  let comp: DmNCCUpdateComponent;
  let fixture: ComponentFixture<DmNCCUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let dmNCCFormService: DmNCCFormService;
  let dmNCCService: DmNCCService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DmNCCUpdateComponent],
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
      .overrideTemplate(DmNCCUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DmNCCUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    dmNCCFormService = TestBed.inject(DmNCCFormService);
    dmNCCService = TestBed.inject(DmNCCService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const dmNCC: IDmNCC = { id: 456 };

      activatedRoute.data = of({ dmNCC });
      comp.ngOnInit();

      expect(comp.dmNCC).toEqual(dmNCC);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDmNCC>>();
      const dmNCC = { id: 123 };
      jest.spyOn(dmNCCFormService, 'getDmNCC').mockReturnValue(dmNCC);
      jest.spyOn(dmNCCService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ dmNCC });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: dmNCC }));
      saveSubject.complete();

      // THEN
      expect(dmNCCFormService.getDmNCC).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(dmNCCService.update).toHaveBeenCalledWith(expect.objectContaining(dmNCC));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDmNCC>>();
      const dmNCC = { id: 123 };
      jest.spyOn(dmNCCFormService, 'getDmNCC').mockReturnValue({ id: null });
      jest.spyOn(dmNCCService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ dmNCC: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: dmNCC }));
      saveSubject.complete();

      // THEN
      expect(dmNCCFormService.getDmNCC).toHaveBeenCalled();
      expect(dmNCCService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDmNCC>>();
      const dmNCC = { id: 123 };
      jest.spyOn(dmNCCService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ dmNCC });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(dmNCCService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
