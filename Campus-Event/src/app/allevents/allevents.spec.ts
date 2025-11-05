import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Allevents } from './allevents';

describe('Allevents', () => {
  let component: Allevents;
  let fixture: ComponentFixture<Allevents>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Allevents]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Allevents);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
