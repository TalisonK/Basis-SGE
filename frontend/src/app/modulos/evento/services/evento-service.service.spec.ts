import { TestBed } from '@angular/core/testing';

import { EventoServiceService } from './evento-service.service';

describe('EventoServiceService', () => {
  let service: EventoServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EventoServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
