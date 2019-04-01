/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { PersonajeService } from 'app/entities/personaje/personaje.service';
import { IPersonaje, Personaje, Alignment, Dexterity, Sizes, Races, CharacterClase } from 'app/shared/model/personaje.model';

describe('Service Tests', () => {
    describe('Personaje Service', () => {
        let injector: TestBed;
        let service: PersonajeService;
        let httpMock: HttpTestingController;
        let elemDefault: IPersonaje;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(PersonajeService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new Personaje(
                0,
                'AAAAAAA',
                'AAAAAAA',
                Alignment.CAOTICO_BUENO,
                'AAAAAAA',
                'AAAAAAA',
                0,
                0,
                Dexterity.DIESTRO,
                'AAAAAAA',
                Sizes.GIGANTIC,
                0,
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0,
                0,
                Races.ALARDYS,
                CharacterClase.ASESINO
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

            it('should create a Personaje', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new Personaje(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Personaje', async () => {
                const returnedFromService = Object.assign(
                    {
                        ownerName: 'BBBBBB',
                        characterName: 'BBBBBB',
                        alignment: 'BBBBBB',
                        religion: 'BBBBBB',
                        gender: 'BBBBBB',
                        realAge: 1,
                        apparentAge: 1,
                        dexterity: 'BBBBBB',
                        originName: 'BBBBBB',
                        size: 'BBBBBB',
                        height: 1,
                        weight: 1,
                        eyeColor: 'BBBBBB',
                        hairColor: 'BBBBBB',
                        tezColor: 'BBBBBB',
                        maxHitPoints: 1,
                        currentHitPoints: 1,
                        race: 'BBBBBB',
                        characterClass: 'BBBBBB'
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

            it('should return a list of Personaje', async () => {
                const returnedFromService = Object.assign(
                    {
                        ownerName: 'BBBBBB',
                        characterName: 'BBBBBB',
                        alignment: 'BBBBBB',
                        religion: 'BBBBBB',
                        gender: 'BBBBBB',
                        realAge: 1,
                        apparentAge: 1,
                        dexterity: 'BBBBBB',
                        originName: 'BBBBBB',
                        size: 'BBBBBB',
                        height: 1,
                        weight: 1,
                        eyeColor: 'BBBBBB',
                        hairColor: 'BBBBBB',
                        tezColor: 'BBBBBB',
                        maxHitPoints: 1,
                        currentHitPoints: 1,
                        race: 'BBBBBB',
                        characterClass: 'BBBBBB'
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

            it('should delete a Personaje', async () => {
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
