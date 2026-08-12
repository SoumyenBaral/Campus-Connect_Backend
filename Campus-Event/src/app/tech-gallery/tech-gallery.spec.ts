import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TechGallery } from './tech-gallery';

describe('TechGallery', () => {
  let component: TechGallery;
  let fixture: ComponentFixture<TechGallery>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TechGallery]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TechGallery);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
