import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HostDetails } from './host-details';

describe('HostDetails', () => {
  let component: HostDetails;
  let fixture: ComponentFixture<HostDetails>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HostDetails]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HostDetails);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
