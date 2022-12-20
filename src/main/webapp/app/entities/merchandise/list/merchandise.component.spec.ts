import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { MerchandiseService } from '../service/merchandise.service';

import { MerchandiseComponent } from './merchandise.component';

describe('Merchandise Management Component', () => {
  let comp: MerchandiseComponent;
  let fixture: ComponentFixture<MerchandiseComponent>;
  let service: MerchandiseService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'merchandise', component: MerchandiseComponent }]), HttpClientTestingModule],
      declarations: [MerchandiseComponent],
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
      .overrideTemplate(MerchandiseComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MerchandiseComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(MerchandiseService);

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
    expect(comp.merchandises?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to merchandiseService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getMerchandiseIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getMerchandiseIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
