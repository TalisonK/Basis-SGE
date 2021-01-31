import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RespostaBlocoComponent } from './resposta-bloco.component';

describe('RespostaBlocoComponent', () => {
  let component: RespostaBlocoComponent;
  let fixture: ComponentFixture<RespostaBlocoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RespostaBlocoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RespostaBlocoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
