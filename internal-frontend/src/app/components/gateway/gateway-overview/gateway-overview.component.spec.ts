import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GatewayOverviewComponent } from './gateway-overview.component';

describe('GatewayOverviewComponent', () => {
  let component: GatewayOverviewComponent;
  let fixture: ComponentFixture<GatewayOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GatewayOverviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GatewayOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
