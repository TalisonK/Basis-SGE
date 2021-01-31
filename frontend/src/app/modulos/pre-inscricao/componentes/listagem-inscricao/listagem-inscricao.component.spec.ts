import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListagemInscricaoComponent } from './listagem-inscricao.component';

describe('ListagemInscricaoComponent', () => {
  let component: ListagemInscricaoComponent;
  let fixture: ComponentFixture<ListagemInscricaoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListagemInscricaoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListagemInscricaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
