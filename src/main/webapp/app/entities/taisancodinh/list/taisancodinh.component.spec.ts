import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { TaisancodinhService } from '../service/taisancodinh.service';

import { TaisancodinhComponent } from './taisancodinh.component';

describe('Taisancodinh Management Component', () => {
  let comp: TaisancodinhComponent;
  let fixture: ComponentFixture<TaisancodinhComponent>;
  let service: TaisancodinhService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'taisancodinh', component: TaisancodinhComponent }]), HttpClientTestingModule],
      declarations: [TaisancodinhComponent],
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
      .overrideTemplate(TaisancodinhComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TaisancodinhComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(TaisancodinhService);

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
    expect(comp.taisancodinhs?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to taisancodinhService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getTaisancodinhIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getTaisancodinhIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
