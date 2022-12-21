import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CongcudungcuDetailComponent } from './congcudungcu-detail.component';

describe('Congcudungcu Management Detail Component', () => {
  let comp: CongcudungcuDetailComponent;
  let fixture: ComponentFixture<CongcudungcuDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CongcudungcuDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ congcudungcu: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(CongcudungcuDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(CongcudungcuDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load congcudungcu on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.congcudungcu).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
