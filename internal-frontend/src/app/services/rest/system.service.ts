import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {RestHeaderService} from './rest-header.service';
import {GatewayDto} from '../../dto/GatewayDto';

@Injectable({
  providedIn: 'root'
})
export class SystemService extends RestHeaderService {
  path = 'client';

  public findAll(): Observable<GatewayDto[]> {
    const url = `${this.getBaseUrl(this.path)}/findAll`;
    return this.http.get<GatewayDto[]>(url, this.getHeaders());
  }
}
