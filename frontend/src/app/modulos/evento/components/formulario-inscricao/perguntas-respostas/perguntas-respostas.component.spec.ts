import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PerguntasRespostasComponent } from './perguntas-respostas.component';

describe('PerguntasRespostasComponent', () => {
  let component: PerguntasRespostasComponent;
  let fixture: ComponentFixture<PerguntasRespostasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PerguntasRespostasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PerguntasRespostasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
