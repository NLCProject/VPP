import {Component, OnDestroy, OnInit} from '@angular/core';
import {SystemService} from "../../../services/rest/system.service";
import {ActivatedRoute} from "@angular/router";
import {TranslationService} from "../../../services/translation/translation.service";
import {GatewayDto} from "../../../dto/GatewayDto";

@Component({
  selector: 'app-gateway-details',
  templateUrl: './gateway-details.component.html',
  styleUrls: ['./gateway-details.component.scss']
})
export class GatewayDetailsComponent implements OnInit, OnDestroy {

  constructor(
    private systemService: SystemService,
    private activatedRoute: ActivatedRoute,
    private translationService: TranslationService
  ) { }

  private gatewayId = '';
  private interval: any = null;
  public loading = true;
  public selectedTab = 0;
  public gateway: GatewayDto | null = null;

  public ngOnInit(): void {
    this.getGatewayId();
  }

  public ngOnDestroy(): void {
    if (this.interval) {
      clearInterval(this.interval);
    }
  }

  private loadInterval(): void {
    this.loadData();

    this.interval = setInterval(() => {
      this.loadData();
    }, 2_000);
  }

  private getGatewayId(): void {
    this.activatedRoute.params.subscribe(params => {
      const key = 'gatewayId';
      this.gatewayId = params[key];
      this.loadInterval();
    });
  }

  private loadData(): void {
    this.systemService.findById(this.gatewayId).subscribe(
      response => {
        this.gateway = response;
        this.loading = false;
      }, error => {
        this.translationService.showSnackbar(error);
        this.loading = false;
      }
    );
  }
}
