import {Component, OnDestroy, OnInit} from '@angular/core';
import {SystemService} from "../../services/rest/system.service";
import {GatewayDto} from "../../dto/GatewayDto";
import {TranslationService} from "../../services/translation/translation.service";

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss']
})
export class ToolbarComponent implements OnInit, OnDestroy {

  constructor(
    private systemService: SystemService,
    private translationService: TranslationService
  ) { }

  public loading = true;
  public selectedTab = 0;
  private interval = null;
  private loadingInternal = false;
  public gateways: GatewayDto[] = [];

  public ngOnInit(): void {
    this.loadInterval();
  }

  public ngOnDestroy(): void {
    if (this.interval) {
      clearInterval(this.interval);
    }
  }

  private loadInterval(): void {
    this.loadData();

    // @ts-ignore
    this.interval = setInterval(() => {
      this.loadData();
    }, 10_000);
  }

  private loadData(): void {
    if (this.loadingInternal) {
      return;
    }

    this.loadingInternal = true;
    this.systemService.findAll().subscribe(
      response => {
        this.gateways = response;
        this.loading = false;
        this.loadingInternal = false;
      }, error => {
        this.translationService.showSnackbar(error);
        this.loading = false;
        this.loadingInternal = false;
      }
    );
  }
}
