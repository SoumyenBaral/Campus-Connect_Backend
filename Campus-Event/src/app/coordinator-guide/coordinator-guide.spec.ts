import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoordinatorGuide } from './coordinator-guide';

describe('CoordinatorGuide', () => {
  let component: CoordinatorGuide;
  let fixture: ComponentFixture<CoordinatorGuide>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CoordinatorGuide]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CoordinatorGuide);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
