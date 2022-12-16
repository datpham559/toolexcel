import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { CongnophaitraService } from '../service/congnophaitra.service';

import { CongnophaitraComponent } from './congnophaitra.component';

describe('Congnophaitra Management Component', () => {
  let comp: CongnophaitraComponent;
  let fixture: ComponentFixture<CongnophaitraComponent>;
  let service: CongnophaitraService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'congnophaitra', component: CongnophaitraComponent }]), HttpClientTestingModule],
      declarations: [CongnophaitraComponent],
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
      .overrideTemplate(CongnophaitraComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CongnophaitraComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(CongnophaitraService);

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
    expect(comp.congnophaitras?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to congnophaitraService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getCongnophaitraIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getCongnophaitraIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
