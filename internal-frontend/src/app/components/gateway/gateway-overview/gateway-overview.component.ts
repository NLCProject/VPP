import {Component, OnDestroy, OnInit} from '@angular/core';
import {SystemService} from "../../../services/rest/system.service";
import {TranslationService} from "../../../services/translation/translation.service";
import {GatewayDto} from "../../../dto/GatewayDto";
import {Router} from "@angular/router";

@Component({
  selector: 'app-gateway-overview',
  templateUrl: './gateway-overview.component.html',
  styleUrls: ['./gateway-overview.component.scss']
})
export class GatewayOverviewComponent implements OnInit, OnDestroy {

  constructor(
    protected router: Router,
    private systemService: SystemService,
    private translationService: TranslationService
  ) { }

  private interval: any = null;
  public loading = true;
  public gateways: GatewayDto[] = [];

  public ngOnInit(): void {
    this.loadInterval();
  }

  public ngOnDestroy(): void {
    if (this.interval) {
      clearInterval(this.interval);
    }
  }

  public openDetails(gatewayId: string): void {
    const urlTree = this.router.createUrlTree([`/gateway/details`, gatewayId]);
    const url = this.router.serializeUrl(urlTree);
    window.open(url, '_blank');
  }

  private loadInterval(): void {
    this.loadData();

    this.interval = setInterval(() => {
      this.loadData();
    }, 2_000);
  }

  private loadData(): void {
    this.systemService.findAll().subscribe(
      response => {
        this.gateways = response;
        this.loading = false;
      }, error => {
        this.translationService.showSnackbar(error);
        this.loading = false;
      }
    );
  }
}
