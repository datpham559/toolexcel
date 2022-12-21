import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SyntheticDetailComponent } from './synthetic-detail.component';

describe('Synthetic Management Detail Component', () => {
  let comp: SyntheticDetailComponent;
  let fixture: ComponentFixture<SyntheticDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SyntheticDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ synthetic: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(SyntheticDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(SyntheticDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load synthetic on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.synthetic).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
