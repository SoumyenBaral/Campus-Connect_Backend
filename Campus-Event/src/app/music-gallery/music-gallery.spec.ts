import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MusicGallery } from './music-gallery';

describe('MusicGallery', () => {
  let component: MusicGallery;
  let fixture: ComponentFixture<MusicGallery>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MusicGallery]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MusicGallery);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
