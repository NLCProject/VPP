import { BatterySystemDto } from './BatterySystemDto';
import {ConsumerGroupDto} from "./ConsumerGroupDto";
import {Dto} from "./Dto";

export class GatewayDto extends Dto {
  serialNumber: string = '';
  systems: BatterySystemDto[] = [];
  groups: ConsumerGroupDto[] = [];
}
