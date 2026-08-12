import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HostGuide } from './host-guide';

describe('HostGuide', () => {
  let component: HostGuide;
  let fixture: ComponentFixture<HostGuide>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HostGuide]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HostGuide);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
