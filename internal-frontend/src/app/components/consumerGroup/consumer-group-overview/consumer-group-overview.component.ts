import {Component, Input} from '@angular/core';
import {ConsumerGroupDto} from "../../../dto/ConsumerGroupDto";
import {MatDialog} from "@angular/material/dialog";
import { ConsumerMode } from 'src/app/dto/ConsumerMode';
import {
  ConsumerGroupModeSelectionComponent
} from "../consumer-group-mode-selection/consumer-group-mode-selection.component";

@Component({
  selector: 'app-consumer-group-overview',
  templateUrl: './consumer-group-overview.component.html',
  styleUrls: ['./consumer-group-overview.component.scss']
})
export class ConsumerGroupOverviewComponent {

  @Input()
  public groups: ConsumerGroupDto[] = [];

  @Input()
  public loading: boolean = true;

  constructor(
    private dialog: MatDialog
  ) { }

  public ConsumerMode = ConsumerMode;

  public openDialog(group: ConsumerGroupDto): void {
    this.dialog.open(ConsumerGroupModeSelectionComponent, {
      data: { group }
    });
  }
}
