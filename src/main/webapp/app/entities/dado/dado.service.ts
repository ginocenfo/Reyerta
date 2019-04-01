import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDado } from 'app/shared/model/dado.model';

type EntityResponseType = HttpResponse<IDado>;
type EntityArrayResponseType = HttpResponse<IDado[]>;

@Injectable({ providedIn: 'root' })
export class DadoService {
    public resourceUrl = SERVER_API_URL + 'api/dados';

    constructor(protected http: HttpClient) {}

    create(dado: IDado): Observable<EntityResponseType> {
        return this.http.post<IDado>(this.resourceUrl, dado, { observe: 'response' });
    }

    update(dado: IDado): Observable<EntityResponseType> {
        return this.http.put<IDado>(this.resourceUrl, dado, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDado>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDado[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
