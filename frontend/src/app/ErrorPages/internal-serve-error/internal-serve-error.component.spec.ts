import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InternalServeErrorComponent } from './internal-serve-error.component';

describe('InternalServeErrorComponent', () => {
  let component: InternalServeErrorComponent;
  let fixture: ComponentFixture<InternalServeErrorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InternalServeErrorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InternalServeErrorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
