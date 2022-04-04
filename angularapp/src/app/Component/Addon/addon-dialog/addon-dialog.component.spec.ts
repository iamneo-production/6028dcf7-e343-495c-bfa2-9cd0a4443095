import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddonDialogComponent } from './addon-dialog.component';

describe('AddonDialogComponent', () => {
  let component: AddonDialogComponent;
  let fixture: ComponentFixture<AddonDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddonDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddonDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
