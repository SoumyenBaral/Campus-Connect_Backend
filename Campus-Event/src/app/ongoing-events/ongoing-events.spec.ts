import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OngoingEvents } from './ongoing-events';

describe('OngoingEvents', () => {
  let component: OngoingEvents;
  let fixture: ComponentFixture<OngoingEvents>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OngoingEvents]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OngoingEvents);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
