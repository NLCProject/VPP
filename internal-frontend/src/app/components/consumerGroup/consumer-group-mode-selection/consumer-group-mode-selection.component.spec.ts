import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsumerGroupModeSelectionComponent } from './consumer-group-mode-selection.component';

describe('ConsumerGroupModeSelectionComponent', () => {
  let component: ConsumerGroupModeSelectionComponent;
  let fixture: ComponentFixture<ConsumerGroupModeSelectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsumerGroupModeSelectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsumerGroupModeSelectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
