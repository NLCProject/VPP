import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsumerGroupOverviewComponent } from './consumer-group-overview.component';

describe('ConsumerGroupOverviewComponent', () => {
  let component: ConsumerGroupOverviewComponent;
  let fixture: ComponentFixture<ConsumerGroupOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsumerGroupOverviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsumerGroupOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
