import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { DmNCCService } from '../service/dm-ncc.service';

import { DmNCCComponent } from './dm-ncc.component';

describe('DmNCC Management Component', () => {
  let comp: DmNCCComponent;
  let fixture: ComponentFixture<DmNCCComponent>;
  let service: DmNCCService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'dm-ncc', component: DmNCCComponent }]), HttpClientTestingModule],
      declarations: [DmNCCComponent],
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
      .overrideTemplate(DmNCCComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DmNCCComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(DmNCCService);

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
    expect(comp.dmNCCS?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to dmNCCService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getDmNCCIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getDmNCCIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
