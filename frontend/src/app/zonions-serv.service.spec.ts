import { TestBed } from '@angular/core/testing';

import { ZonionsServService } from './zonions-serv.service';

describe('ZonionsServService', () => {
  let service: ZonionsServService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ZonionsServService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
