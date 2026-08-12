import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Host } from './host';

describe('Host', () => {
  let component: Host;
  let fixture: ComponentFixture<Host>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Host]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Host);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
