import {Component, Input} from '@angular/core';
import { SystemStatus } from 'src/app/dto/SystemStatus';
import {BatterySystemDto} from '../../../dto/BatterySystemDto';

@Component({
  selector: 'app-system-overview',
  templateUrl: './system-overview.component.html',
  styleUrls: ['./system-overview.component.scss']
})
export class SystemOverviewComponent {

  @Input()
  public clients: BatterySystemDto[] = [];

  @Input()
  public loading: boolean = true;

  public SystemStatus = SystemStatus;
}
