import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ISynthetic, NewSynthetic } from '../synthetic.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISynthetic for edit and NewSyntheticFormGroupInput for create.
 */
type SyntheticFormGroupInput = ISynthetic | PartialWithRequiredKeyOf<NewSynthetic>;

type SyntheticFormDefaults = Pick<NewSynthetic, 'id'>;

type SyntheticFormGroupContent = {
  id: FormControl<ISynthetic['id'] | NewSynthetic['id']>;
  voucherType: FormControl<ISynthetic['voucherType']>;
  voucherTypeNo: FormControl<ISynthetic['voucherTypeNo']>;
  voucherNo: FormControl<ISynthetic['voucherNo']>;
  voucherDate: FormControl<ISynthetic['voucherDate']>;
  accountingDate: FormControl<ISynthetic['accountingDate']>;
  invoiceNo: FormControl<ISynthetic['invoiceNo']>;
  invoiceDate: FormControl<ISynthetic['invoiceDate']>;
  debitAccount: FormControl<ISynthetic['debitAccount']>;
  creditAccount: FormControl<ISynthetic['creditAccount']>;
  currencyType: FormControl<ISynthetic['currencyType']>;
  currency: FormControl<ISynthetic['currency']>;
  materialGoodCode: FormControl<ISynthetic['materialGoodCode']>;
  materialGoodName: FormControl<ISynthetic['materialGoodName']>;
  storageIn: FormControl<ISynthetic['storageIn']>;
  storageOut: FormControl<ISynthetic['storageOut']>;
  caculationUnit: FormControl<ISynthetic['caculationUnit']>;
  amount: FormControl<ISynthetic['amount']>;
  price: FormControl<ISynthetic['price']>;
  tranferRate: FormControl<ISynthetic['tranferRate']>;
  moneyTranfer: FormControl<ISynthetic['moneyTranfer']>;
  fixedAssetsType: FormControl<ISynthetic['fixedAssetsType']>;
  fixedAssetsCode: FormControl<ISynthetic['fixedAssetsCode']>;
  toolsCode: FormControl<ISynthetic['toolsCode']>;
  debitObject: FormControl<ISynthetic['debitObject']>;
  creditObject: FormControl<ISynthetic['creditObject']>;
  unit: FormControl<ISynthetic['unit']>;
  employee: FormControl<ISynthetic['employee']>;
  bankAccount: FormControl<ISynthetic['bankAccount']>;
  itemCost: FormControl<ISynthetic['itemCost']>;
  construction: FormControl<ISynthetic['construction']>;
  costSet: FormControl<ISynthetic['costSet']>;
  purchaseOrder: FormControl<ISynthetic['purchaseOrder']>;
  buyOrder: FormControl<ISynthetic['buyOrder']>;
  purchaseContract: FormControl<ISynthetic['purchaseContract']>;
  saleContract: FormControl<ISynthetic['saleContract']>;
  statsCode: FormControl<ISynthetic['statsCode']>;
  explanation: FormControl<ISynthetic['explanation']>;
  explanationDetail: FormControl<ISynthetic['explanationDetail']>;
  recordStatus: FormControl<ISynthetic['recordStatus']>;
  createdDate: FormControl<ISynthetic['createdDate']>;
  keyUUID: FormControl<ISynthetic['keyUUID']>;
};

export type SyntheticFormGroup = FormGroup<SyntheticFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SyntheticFormService {
  createSyntheticFormGroup(synthetic: SyntheticFormGroupInput = { id: null }): SyntheticFormGroup {
    const syntheticRawValue = {
      ...this.getFormDefaults(),
      ...synthetic,
    };
    return new FormGroup<SyntheticFormGroupContent>({
      id: new FormControl(
        { value: syntheticRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      voucherType: new FormControl(syntheticRawValue.voucherType),
      voucherTypeNo: new FormControl(syntheticRawValue.voucherTypeNo),
      voucherNo: new FormControl(syntheticRawValue.voucherNo),
      voucherDate: new FormControl(syntheticRawValue.voucherDate),
      accountingDate: new FormControl(syntheticRawValue.accountingDate),
      invoiceNo: new FormControl(syntheticRawValue.invoiceNo),
      invoiceDate: new FormControl(syntheticRawValue.invoiceDate),
      debitAccount: new FormControl(syntheticRawValue.debitAccount),
      creditAccount: new FormControl(syntheticRawValue.creditAccount),
      currencyType: new FormControl(syntheticRawValue.currencyType),
      currency: new FormControl(syntheticRawValue.currency),
      materialGoodCode: new FormControl(syntheticRawValue.materialGoodCode),
      materialGoodName: new FormControl(syntheticRawValue.materialGoodName),
      storageIn: new FormControl(syntheticRawValue.storageIn),
      storageOut: new FormControl(syntheticRawValue.storageOut),
      caculationUnit: new FormControl(syntheticRawValue.caculationUnit),
      amount: new FormControl(syntheticRawValue.amount),
      price: new FormControl(syntheticRawValue.price),
      tranferRate: new FormControl(syntheticRawValue.tranferRate),
      moneyTranfer: new FormControl(syntheticRawValue.moneyTranfer),
      fixedAssetsType: new FormControl(syntheticRawValue.fixedAssetsType),
      fixedAssetsCode: new FormControl(syntheticRawValue.fixedAssetsCode),
      toolsCode: new FormControl(syntheticRawValue.toolsCode),
      debitObject: new FormControl(syntheticRawValue.debitObject),
      creditObject: new FormControl(syntheticRawValue.creditObject),
      unit: new FormControl(syntheticRawValue.unit),
      employee: new FormControl(syntheticRawValue.employee),
      bankAccount: new FormControl(syntheticRawValue.bankAccount),
      itemCost: new FormControl(syntheticRawValue.itemCost),
      construction: new FormControl(syntheticRawValue.construction),
      costSet: new FormControl(syntheticRawValue.costSet),
      purchaseOrder: new FormControl(syntheticRawValue.purchaseOrder),
      buyOrder: new FormControl(syntheticRawValue.buyOrder),
      purchaseContract: new FormControl(syntheticRawValue.purchaseContract),
      saleContract: new FormControl(syntheticRawValue.saleContract),
      statsCode: new FormControl(syntheticRawValue.statsCode),
      explanation: new FormControl(syntheticRawValue.explanation),
      explanationDetail: new FormControl(syntheticRawValue.explanationDetail),
      recordStatus: new FormControl(syntheticRawValue.recordStatus),
      createdDate: new FormControl(syntheticRawValue.createdDate),
      keyUUID: new FormControl(syntheticRawValue.keyUUID),
    });
  }

  getSynthetic(form: SyntheticFormGroup): ISynthetic | NewSynthetic {
    return form.getRawValue() as ISynthetic | NewSynthetic;
  }

  resetForm(form: SyntheticFormGroup, synthetic: SyntheticFormGroupInput): void {
    const syntheticRawValue = { ...this.getFormDefaults(), ...synthetic };
    form.reset(
      {
        ...syntheticRawValue,
        id: { value: syntheticRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): SyntheticFormDefaults {
    return {
      id: null,
    };
  }
}
