import {Component, OnDestroy, OnInit} from '@angular/core';
import {SystemService} from "../../services/rest/system.service";
import {GatewayDto} from "../../dto/GatewayDto";
import {TranslationService} from "../../services/translation/translation.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss']
})
export class ToolbarComponent implements OnInit, OnDestroy {

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
    this.router.navigate([`/gateway/details`, gatewayId]);
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
