import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Coordinator } from './coordinator';

describe('Coordinator', () => {
  let component: Coordinator;
  let fixture: ComponentFixture<Coordinator>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Coordinator]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Coordinator);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
