import {SystemStatus} from "./SystemStatus";
import {ConsumerGroupDto} from "./ConsumerGroupDto";
import {Dto} from "./Dto";

export class BatterySystemDto extends Dto {
  value: number = 0;
  serialNumber: string = '';
  manufacturer: string = '';
  group: ConsumerGroupDto | null = null;
  status: SystemStatus = SystemStatus.ONLINE;
}
