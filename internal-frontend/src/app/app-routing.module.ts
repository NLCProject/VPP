import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ToolbarComponent} from './components/toolbar/toolbar.component';
import {GatewayOverviewComponent} from "./components/gateway/gateway-overview/gateway-overview.component";

const routes: Routes = [
  {
    path: 'dashboard',
    component: ToolbarComponent
  }, {
    path: 'gateway/details/:gatewayId',
    component: GatewayOverviewComponent
  }, {
    path: '**',
    redirectTo: '/dashboard'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
