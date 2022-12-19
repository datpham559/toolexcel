import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { CongcudungcuService } from '../service/congcudungcu.service';

import { CongcudungcuComponent } from './congcudungcu.component';

describe('Congcudungcu Management Component', () => {
  let comp: CongcudungcuComponent;
  let fixture: ComponentFixture<CongcudungcuComponent>;
  let service: CongcudungcuService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'congcudungcu', component: CongcudungcuComponent }]), HttpClientTestingModule],
      declarations: [CongcudungcuComponent],
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
      .overrideTemplate(CongcudungcuComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CongcudungcuComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(CongcudungcuService);

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
    expect(comp.congcudungcus?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to congcudungcuService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getCongcudungcuIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getCongcudungcuIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
