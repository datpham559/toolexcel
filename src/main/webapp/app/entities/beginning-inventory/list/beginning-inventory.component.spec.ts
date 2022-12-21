import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { BeginningInventoryService } from '../service/beginning-inventory.service';

import { BeginningInventoryComponent } from './beginning-inventory.component';

describe('BeginningInventory Management Component', () => {
  let comp: BeginningInventoryComponent;
  let fixture: ComponentFixture<BeginningInventoryComponent>;
  let service: BeginningInventoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'beginning-inventory', component: BeginningInventoryComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [BeginningInventoryComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              })
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(BeginningInventoryComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BeginningInventoryComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(BeginningInventoryService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.beginningInventories?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to beginningInventoryService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getBeginningInventoryIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getBeginningInventoryIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
