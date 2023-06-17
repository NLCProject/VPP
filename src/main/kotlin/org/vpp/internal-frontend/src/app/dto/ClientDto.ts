import {SystemStatus} from "./SystemStatus";

export class ClientDto {
  id: string = '';
  value: number = 0;
  serialNumber: string = '';
  manufacturer: string = '';
  status: SystemStatus = SystemStatus.ONLINE;
}
