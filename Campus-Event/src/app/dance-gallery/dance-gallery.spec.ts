import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DanceGallery } from './dance-gallery';

describe('DanceGallery', () => {
  let component: DanceGallery;
  let fixture: ComponentFixture<DanceGallery>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DanceGallery]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DanceGallery);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
