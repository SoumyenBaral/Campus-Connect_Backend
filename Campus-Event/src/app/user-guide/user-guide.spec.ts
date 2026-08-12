import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserGuide } from './user-guide';

describe('UserGuide', () => {
  let component: UserGuide;
  let fixture: ComponentFixture<UserGuide>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserGuide]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserGuide);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
