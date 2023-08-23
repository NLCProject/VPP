import {WiringMode} from "./WiringMode";
import {ConsumerMode} from "./ConsumerMode";
import {BatterySystemDto} from "./BatterySystemDto";
import {Dto} from "./Dto";

export class ConsumerGroupDto extends Dto {
  name: string = '';
  gatewayId: string = '';
  voltage = 0;
  systems: BatterySystemDto[] = [];
  mode: ConsumerMode = ConsumerMode.None;
  wiring: WiringMode = WiringMode.Unknown;
}
