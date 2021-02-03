import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioInscricaoComponent } from './formulario-inscricao.component';

describe('FormularioInscricaoComponent', () => {
  let component: FormularioInscricaoComponent;
  let fixture: ComponentFixture<FormularioInscricaoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormularioInscricaoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormularioInscricaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
