import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Helpcenter } from './helpcenter';

describe('Helpcenter', () => {
  let component: Helpcenter;
  let fixture: ComponentFixture<Helpcenter>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Helpcenter]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Helpcenter);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
