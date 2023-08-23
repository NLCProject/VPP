import {WiringMode} from "./WiringMode";
import {ConsumerMode} from "./ConsumerMode";
import {ClientDto} from "./ClientDto";

export class ConsumerGroupDto {
  id: string = '';
  name: string = '';
  voltage = 0;
  systems: ClientDto[] = [];
  standard = false;
  mode: ConsumerMode = ConsumerMode.None;
  wiring: WiringMode = WiringMode.Unknown;
}
