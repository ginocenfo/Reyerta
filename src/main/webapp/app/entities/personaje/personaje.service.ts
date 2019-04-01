import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPersonaje } from 'app/shared/model/personaje.model';

type EntityResponseType = HttpResponse<IPersonaje>;
type EntityArrayResponseType = HttpResponse<IPersonaje[]>;

@Injectable({ providedIn: 'root' })
export class PersonajeService {
    public resourceUrl = SERVER_API_URL + 'api/personajes';

    constructor(protected http: HttpClient) {}

    create(personaje: IPersonaje): Observable<EntityResponseType> {
        return this.http.post<IPersonaje>(this.resourceUrl, personaje, { observe: 'response' });
    }

    update(personaje: IPersonaje): Observable<EntityResponseType> {
        return this.http.put<IPersonaje>(this.resourceUrl, personaje, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPersonaje>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPersonaje[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
