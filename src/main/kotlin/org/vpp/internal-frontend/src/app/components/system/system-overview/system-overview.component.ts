import {Component, Input} from '@angular/core';
import { SystemStatus } from 'src/app/dto/SystemStatus';
import {ClientDto} from '../../../dto/ClientDto';

@Component({
  selector: 'app-system-overview',
  templateUrl: './system-overview.component.html',
  styleUrls: ['./system-overview.component.scss']
})
export class SystemOverviewComponent {

  @Input()
  public clients: ClientDto[] = [];

  @Input()
  public loading: boolean = true;

  public SystemStatus = SystemStatus;
}
