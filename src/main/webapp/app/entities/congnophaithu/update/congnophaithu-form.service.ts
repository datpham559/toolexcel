import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICongnophaithu, NewCongnophaithu } from '../congnophaithu.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICongnophaithu for edit and NewCongnophaithuFormGroupInput for create.
 */
type CongnophaithuFormGroupInput = ICongnophaithu | PartialWithRequiredKeyOf<NewCongnophaithu>;

type CongnophaithuFormDefaults = Pick<NewCongnophaithu, 'id'>;

type CongnophaithuFormGroupContent = {
  id: FormControl<ICongnophaithu['id'] | NewCongnophaithu['id']>;
  makh: FormControl<ICongnophaithu['makh']>;
  tenkh: FormControl<ICongnophaithu['tenkh']>;
  tkcongno: FormControl<ICongnophaithu['tkcongno']>;
  sodunodauky: FormControl<ICongnophaithu['sodunodauky']>;
  soducodauky: FormControl<ICongnophaithu['soducodauky']>;
  sonophatsinh: FormControl<ICongnophaithu['sonophatsinh']>;
  socophatsinh: FormControl<ICongnophaithu['socophatsinh']>;
  sodunocuoiky: FormControl<ICongnophaithu['sodunocuoiky']>;
  soducocuoiky: FormControl<ICongnophaithu['soducocuoiky']>;
  createdDate: FormControl<ICongnophaithu['createdDate']>;
  key_uuid: FormControl<ICongnophaithu['key_uuid']>;
};

export type CongnophaithuFormGroup = FormGroup<CongnophaithuFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CongnophaithuFormService {
  createCongnophaithuFormGroup(congnophaithu: CongnophaithuFormGroupInput = { id: null }): CongnophaithuFormGroup {
    const congnophaithuRawValue = {
      ...this.getFormDefaults(),
      ...congnophaithu,
    };
    return new FormGroup<CongnophaithuFormGroupContent>({
      id: new FormControl(
        { value: congnophaithuRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      makh: new FormControl(congnophaithuRawValue.makh),
      tenkh: new FormControl(congnophaithuRawValue.tenkh),
      tkcongno: new FormControl(congnophaithuRawValue.tkcongno),
      sodunodauky: new FormControl(congnophaithuRawValue.sodunodauky),
      soducodauky: new FormControl(congnophaithuRawValue.soducodauky),
      sonophatsinh: new FormControl(congnophaithuRawValue.sonophatsinh),
      socophatsinh: new FormControl(congnophaithuRawValue.socophatsinh),
      sodunocuoiky: new FormControl(congnophaithuRawValue.sodunocuoiky),
      soducocuoiky: new FormControl(congnophaithuRawValue.soducocuoiky),
      createdDate: new FormControl(congnophaithuRawValue.createdDate),
      key_uuid: new FormControl(congnophaithuRawValue.key_uuid),
    });
  }

  getCongnophaithu(form: CongnophaithuFormGroup): ICongnophaithu | NewCongnophaithu {
    return form.getRawValue() as ICongnophaithu | NewCongnophaithu;
  }

  resetForm(form: CongnophaithuFormGroup, congnophaithu: CongnophaithuFormGroupInput): void {
    const congnophaithuRawValue = { ...this.getFormDefaults(), ...congnophaithu };
    form.reset(
      {
        ...congnophaithuRawValue,
        id: { value: congnophaithuRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CongnophaithuFormDefaults {
    return {
      id: null,
    };
  }
}
