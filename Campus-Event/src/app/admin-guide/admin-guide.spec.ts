import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminGuide } from './admin-guide';

describe('AdminGuide', () => {
  let component: AdminGuide;
  let fixture: ComponentFixture<AdminGuide>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminGuide]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminGuide);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
