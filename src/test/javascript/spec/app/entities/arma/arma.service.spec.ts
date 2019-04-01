/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { ArmaService } from 'app/entities/arma/arma.service';
import { IArma, Arma, DamageType, Special, IncrustacionesArma } from 'app/shared/model/arma.model';

describe('Service Tests', () => {
    describe('Arma Service', () => {
        let injector: TestBed;
        let service: ArmaService;
        let httpMock: HttpTestingController;
        let elemDefault: IArma;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(ArmaService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new Arma(
                0,
                'AAAAAAA',
                'AAAAAAA',
                0,
                0,
                false,
                0,
                DamageType.CORTANTE,
                Special.NOLETAL,
                IncrustacionesArma.AMENAZANTE
            );
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

            it('should create a Arma', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new Arma(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Arma', async () => {
                const returnedFromService = Object.assign(
                    {
                        type: 'BBBBBB',
                        name: 'BBBBBB',
                        prize: 1,
                        level: 1,
                        isMasterpiece: true,
                        eliteLevel: 1,
                        damageType: 'BBBBBB',
                        special: 'BBBBBB',
                        incrustacion: 'BBBBBB'
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

            it('should return a list of Arma', async () => {
                const returnedFromService = Object.assign(
                    {
                        type: 'BBBBBB',
                        name: 'BBBBBB',
                        prize: 1,
                        level: 1,
                        isMasterpiece: true,
                        eliteLevel: 1,
                        damageType: 'BBBBBB',
                        special: 'BBBBBB',
                        incrustacion: 'BBBBBB'
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

            it('should delete a Arma', async () => {
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
