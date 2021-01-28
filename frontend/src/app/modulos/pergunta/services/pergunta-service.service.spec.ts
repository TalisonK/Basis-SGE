import { TestBed } from '@angular/core/testing';

import { PerguntaServiceService } from './pergunta-service.service';

describe('PerguntaServiceService', () => {
  let service: PerguntaServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PerguntaServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
