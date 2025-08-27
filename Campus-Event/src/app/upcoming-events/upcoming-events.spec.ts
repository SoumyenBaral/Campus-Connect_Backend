import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpcomingEvents } from './upcoming-events';

describe('UpcomingEvents', () => {
  let component: UpcomingEvents;
  let fixture: ComponentFixture<UpcomingEvents>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpcomingEvents]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpcomingEvents);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
