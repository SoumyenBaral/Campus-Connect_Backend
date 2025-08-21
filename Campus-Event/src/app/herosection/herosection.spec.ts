import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Herosection } from './herosection';

describe('Herosection', () => {
  let component: Herosection;
  let fixture: ComponentFixture<Herosection>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Herosection]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Herosection);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
