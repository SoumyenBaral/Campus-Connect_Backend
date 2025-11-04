import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentDetails } from './student-details';

describe('StudentDetails', () => {
  let component: StudentDetails;
  let fixture: ComponentFixture<StudentDetails>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudentDetails]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudentDetails);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
