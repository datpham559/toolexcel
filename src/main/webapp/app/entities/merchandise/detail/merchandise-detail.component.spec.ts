import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MerchandiseDetailComponent } from './merchandise-detail.component';

describe('Merchandise Management Detail Component', () => {
  let comp: MerchandiseDetailComponent;
  let fixture: ComponentFixture<MerchandiseDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MerchandiseDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ merchandise: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(MerchandiseDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(MerchandiseDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load merchandise on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.merchandise).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
