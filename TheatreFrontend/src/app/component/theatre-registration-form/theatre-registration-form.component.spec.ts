import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TheatreRegistrationFormComponent } from './theatre-registration-form.component';

describe('TheatreRegistrationFormComponent', () => {
  let component: TheatreRegistrationFormComponent;
  let fixture: ComponentFixture<TheatreRegistrationFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TheatreRegistrationFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TheatreRegistrationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
