import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { SyntheticService } from '../service/synthetic.service';

import { SyntheticComponent } from './synthetic.component';

describe('Synthetic Management Component', () => {
  let comp: SyntheticComponent;
  let fixture: ComponentFixture<SyntheticComponent>;
  let service: SyntheticService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'synthetic', component: SyntheticComponent }]), HttpClientTestingModule],
      declarations: [SyntheticComponent],
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
      .overrideTemplate(SyntheticComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SyntheticComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(SyntheticService);

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
    expect(comp.synthetics?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to syntheticService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getSyntheticIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getSyntheticIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
