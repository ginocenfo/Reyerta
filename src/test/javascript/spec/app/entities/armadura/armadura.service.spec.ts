/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { ArmaduraService } from 'app/entities/armadura/armadura.service';
import { IArmadura, Armadura, IncrustacionesArmadura } from 'app/shared/model/armadura.model';

describe('Service Tests', () => {
    describe('Armadura Service', () => {
        let injector: TestBed;
        let service: ArmaduraService;
        let httpMock: HttpTestingController;
        let elemDefault: IArmadura;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(ArmaduraService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new Armadura(0, 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0, 0, 0, 0, IncrustacionesArmadura.ACORAZADA_MENOR);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Armadura', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new Armadura(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Armadura', async () => {
                const returnedFromService = Object.assign(
                    {
                        type: 'BBBBBB',
                        name: 'BBBBBB',
                        evasionBonus: 1,
                        maxDex: 1,
                        level: 1,
                        uc: 1,
                        ppa: 1,
                        castFailChance: 1,
                        price: 1,
                        incrustacionArmadura: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Armadura', async () => {
                const returnedFromService = Object.assign(
                    {
                        type: 'BBBBBB',
                        name: 'BBBBBB',
                        evasionBonus: 1,
                        maxDex: 1,
                        level: 1,
                        uc: 1,
                        ppa: 1,
                        castFailChance: 1,
                        price: 1,
                        incrustacionArmadura: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Armadura', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
