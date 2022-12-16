import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { CongnophaithuService } from '../service/congnophaithu.service';

import { CongnophaithuComponent } from './congnophaithu.component';

describe('Congnophaithu Management Component', () => {
  let comp: CongnophaithuComponent;
  let fixture: ComponentFixture<CongnophaithuComponent>;
  let service: CongnophaithuService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'congnophaithu', component: CongnophaithuComponent }]), HttpClientTestingModule],
      declarations: [CongnophaithuComponent],
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
      .overrideTemplate(CongnophaithuComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CongnophaithuComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(CongnophaithuService);

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
    expect(comp.congnophaithus?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to congnophaithuService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getCongnophaithuIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getCongnophaithuIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
