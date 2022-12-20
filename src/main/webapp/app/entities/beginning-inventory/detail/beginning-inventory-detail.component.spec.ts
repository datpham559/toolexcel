import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BeginningInventoryDetailComponent } from './beginning-inventory-detail.component';

describe('BeginningInventory Management Detail Component', () => {
  let comp: BeginningInventoryDetailComponent;
  let fixture: ComponentFixture<BeginningInventoryDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BeginningInventoryDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ beginningInventory: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(BeginningInventoryDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(BeginningInventoryDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load beginningInventory on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.beginningInventory).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
