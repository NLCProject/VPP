import {Component, Input} from '@angular/core';
import { ConsumerMode } from 'src/app/dto/ConsumerMode';
import { SystemStatus } from 'src/app/dto/SystemStatus';
import {BatterySystemDto} from '../../../dto/BatterySystemDto';

@Component({
  selector: 'app-system-overview',
  templateUrl: './system-overview.component.html',
  styleUrls: ['./system-overview.component.scss']
})
export class SystemOverviewComponent {

  @Input()
  public systems: BatterySystemDto[] = [];

  @Input()
  public loading: boolean = true;

  public ConsumerMode = ConsumerMode;
  public SystemStatus = SystemStatus;
}
