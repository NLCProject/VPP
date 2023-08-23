import {Component, Inject, OnInit} from '@angular/core';
import {SystemService} from "../../../services/rest/system.service";
import {UntypedFormBuilder, UntypedFormGroup, Validators} from "@angular/forms";
import {TranslationService} from "../../../services/translation/translation.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ConsumerMode} from "../../../dto/ConsumerMode";

@Component({
  selector: 'app-consumer-group-mode-selection',
  templateUrl: './consumer-group-mode-selection.component.html',
  styleUrls: ['./consumer-group-mode-selection.component.scss']
})
export class ConsumerGroupModeSelectionComponent implements OnInit {

  constructor(
    private service: SystemService,
    private formBuilder: UntypedFormBuilder,
    private translationService: TranslationService,
    private dialogRef: MatDialogRef<ConsumerGroupModeSelectionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  public loading = true;
  public formGroup: UntypedFormGroup | undefined;

  ngOnInit(): void {
    this.createForms();
    this.loadData();
  }

  public getMode(): string[] {
    return Object.keys(ConsumerMode).filter(key => typeof ConsumerMode[key as any] === 'number');
  }

  public save(): void {
    this.loading = true;
    const controls = this.formGroup!!.controls;
    const mode = controls['mode'].value;

    this.service.changeConsumerMode(this.data.group.gatewayId, this.data.group.id, mode).subscribe(
      () => {
        this.translationService.showSnackbar('Updated');
        this.dialogRef.close();
      },
      () => {
        this.translationService.showSnackbarOnError();
        this.loading = false;
      }
    );
  }

  private loadData(): void {
    this.formGroup!!.setValue({
      mode: this.data.group.mode
    });

    this.loading = false;
  }

  private createForms(): void {
    this.formGroup = this.formBuilder.group(
      {
        mode: [null, Validators.compose([Validators.required])]
      }
    );
  }
}
