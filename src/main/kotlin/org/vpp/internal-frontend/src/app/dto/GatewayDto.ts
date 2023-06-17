import { ClientDto } from './ClientDto';

export class GatewayDto {
  id: string = '';
  serialNumber: string = '';
  clients: ClientDto[] = [];
}
