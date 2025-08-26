import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeGallery } from './home-gallery';

describe('HomeGallery', () => {
  let component: HomeGallery;
  let fixture: ComponentFixture<HomeGallery>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomeGallery]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeGallery);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
