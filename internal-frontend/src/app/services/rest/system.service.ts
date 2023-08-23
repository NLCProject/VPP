import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {RestHeaderService} from './rest-header.service';
import {GatewayDto} from '../../dto/GatewayDto';
import {ConsumerMode} from "../../dto/ConsumerMode";

@Injectable({
  providedIn: 'root'
})
export class SystemService extends RestHeaderService {
  path = 'client';

  public findAll(): Observable<GatewayDto[]> {
    const url = `${this.getBaseUrl(this.path)}/findAll`;
    return this.http.get<GatewayDto[]>(url, this.getHeaders());
  }

  public findById(gatewayId: string): Observable<GatewayDto> {
    const url = `${this.getBaseUrl(this.path)}/findById?gatewayId=${gatewayId}`;
    return this.http.get<GatewayDto>(url, this.getHeaders());
  }

  public changeConsumerMode(gatewayId: string, groupId: string, mode: ConsumerMode): Observable<void> {
    const url = `${this.getBaseUrl(this.path)}/changeConsumerMode?gatewayId=${gatewayId}&groupId=${groupId}&mode=${mode}`;
    return this.http.post<void>(url, null, this.getHeaders());
  }
}
