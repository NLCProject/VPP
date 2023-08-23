import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {GatewayDetailsComponent} from "./components/gateway/gateway-details/gateway-details.component";
import {GatewayOverviewComponent} from "./components/gateway/gateway-overview/gateway-overview.component";

const routes: Routes = [
  {
    path: 'dashboard',
    component: GatewayOverviewComponent
  }, {
    path: 'gateway/details/:gatewayId',
    component: GatewayDetailsComponent
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
