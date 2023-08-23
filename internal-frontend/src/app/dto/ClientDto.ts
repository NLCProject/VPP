import {SystemStatus} from "./SystemStatus";
import {ConsumerGroupDto} from "./ConsumerGroupDto";

export class ClientDto {
  id: string = '';
  value: number = 0;
  serialNumber: string = '';
  manufacturer: string = '';
  group: ConsumerGroupDto | null = null;
  status: SystemStatus = SystemStatus.ONLINE;
}
