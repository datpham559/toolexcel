import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { TonkhodaukyService } from '../service/tonkhodauky.service';

import { TonkhodaukyComponent } from './tonkhodauky.component';

describe('Tonkhodauky Management Component', () => {
  let comp: TonkhodaukyComponent;
  let fixture: ComponentFixture<TonkhodaukyComponent>;
  let service: TonkhodaukyService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'tonkhodauky', component: TonkhodaukyComponent }]), HttpClientTestingModule],
      declarations: [TonkhodaukyComponent],
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
      .overrideTemplate(TonkhodaukyComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TonkhodaukyComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(TonkhodaukyService);

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
    expect(comp.tonkhodaukies?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to tonkhodaukyService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getTonkhodaukyIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getTonkhodaukyIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
