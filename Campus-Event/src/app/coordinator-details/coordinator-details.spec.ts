import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoordinatorDetails } from './coordinator-details';

describe('CoordinatorDetails', () => {
  let component: CoordinatorDetails;
  let fixture: ComponentFixture<CoordinatorDetails>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CoordinatorDetails]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CoordinatorDetails);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
