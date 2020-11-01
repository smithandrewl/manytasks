import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppSiteHeadingComponent } from './app-site-heading.component';

describe('AppSiteHeadingComponent', () => {
  let component: AppSiteHeadingComponent;
  let fixture: ComponentFixture<AppSiteHeadingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppSiteHeadingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppSiteHeadingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
