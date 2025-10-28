import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Nikhil } from './nikhil';

describe('Nikhil', () => {
  let component: Nikhil;
  let fixture: ComponentFixture<Nikhil>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Nikhil]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Nikhil);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
