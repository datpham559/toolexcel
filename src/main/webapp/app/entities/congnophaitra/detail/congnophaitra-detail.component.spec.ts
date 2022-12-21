import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CongnophaitraDetailComponent } from './congnophaitra-detail.component';

describe('Congnophaitra Management Detail Component', () => {
  let comp: CongnophaitraDetailComponent;
  let fixture: ComponentFixture<CongnophaitraDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CongnophaitraDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ congnophaitra: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(CongnophaitraDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(CongnophaitraDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load congnophaitra on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.congnophaitra).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
