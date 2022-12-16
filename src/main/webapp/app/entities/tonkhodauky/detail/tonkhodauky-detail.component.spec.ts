import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TonkhodaukyDetailComponent } from './tonkhodauky-detail.component';

describe('Tonkhodauky Management Detail Component', () => {
  let comp: TonkhodaukyDetailComponent;
  let fixture: ComponentFixture<TonkhodaukyDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TonkhodaukyDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ tonkhodauky: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(TonkhodaukyDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(TonkhodaukyDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load tonkhodauky on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.tonkhodauky).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
