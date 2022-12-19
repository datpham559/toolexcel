import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CongnophaithuDetailComponent } from './congnophaithu-detail.component';

describe('Congnophaithu Management Detail Component', () => {
  let comp: CongnophaithuDetailComponent;
  let fixture: ComponentFixture<CongnophaithuDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CongnophaithuDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ congnophaithu: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(CongnophaithuDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(CongnophaithuDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load congnophaithu on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.congnophaithu).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
