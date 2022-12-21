import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { BeginningInventoryFormService } from './beginning-inventory-form.service';
import { BeginningInventoryService } from '../service/beginning-inventory.service';
import { IBeginningInventory } from '../beginning-inventory.model';

import { BeginningInventoryUpdateComponent } from './beginning-inventory-update.component';

describe('BeginningInventory Management Update Component', () => {
  let comp: BeginningInventoryUpdateComponent;
  let fixture: ComponentFixture<BeginningInventoryUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let beginningInventoryFormService: BeginningInventoryFormService;
  let beginningInventoryService: BeginningInventoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [BeginningInventoryUpdateComponent],
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
      .overrideTemplate(BeginningInventoryUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BeginningInventoryUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    beginningInventoryFormService = TestBed.inject(BeginningInventoryFormService);
    beginningInventoryService = TestBed.inject(BeginningInventoryService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const beginningInventory: IBeginningInventory = { id: 456 };

      activatedRoute.data = of({ beginningInventory });
      comp.ngOnInit();

      expect(comp.beginningInventory).toEqual(beginningInventory);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBeginningInventory>>();
      const beginningInventory = { id: 123 };
      jest.spyOn(beginningInventoryFormService, 'getBeginningInventory').mockReturnValue(beginningInventory);
      jest.spyOn(beginningInventoryService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ beginningInventory });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: beginningInventory }));
      saveSubject.complete();

      // THEN
      expect(beginningInventoryFormService.getBeginningInventory).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(beginningInventoryService.update).toHaveBeenCalledWith(expect.objectContaining(beginningInventory));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBeginningInventory>>();
      const beginningInventory = { id: 123 };
      jest.spyOn(beginningInventoryFormService, 'getBeginningInventory').mockReturnValue({ id: null });
      jest.spyOn(beginningInventoryService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ beginningInventory: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: beginningInventory }));
      saveSubject.complete();

      // THEN
      expect(beginningInventoryFormService.getBeginningInventory).toHaveBeenCalled();
      expect(beginningInventoryService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBeginningInventory>>();
      const beginningInventory = { id: 123 };
      jest.spyOn(beginningInventoryService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ beginningInventory });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(beginningInventoryService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
