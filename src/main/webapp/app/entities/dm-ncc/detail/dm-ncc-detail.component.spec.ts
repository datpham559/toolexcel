import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DmNCCDetailComponent } from './dm-ncc-detail.component';

describe('DmNCC Management Detail Component', () => {
  let comp: DmNCCDetailComponent;
  let fixture: ComponentFixture<DmNCCDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DmNCCDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ dmNCC: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(DmNCCDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(DmNCCDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load dmNCC on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.dmNCC).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
