import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IArma } from 'app/shared/model/arma.model';

type EntityResponseType = HttpResponse<IArma>;
type EntityArrayResponseType = HttpResponse<IArma[]>;

@Injectable({ providedIn: 'root' })
export class ArmaService {
    public resourceUrl = SERVER_API_URL + 'api/armas';

    constructor(protected http: HttpClient) {}

    create(arma: IArma): Observable<EntityResponseType> {
        return this.http.post<IArma>(this.resourceUrl, arma, { observe: 'response' });
    }

    update(arma: IArma): Observable<EntityResponseType> {
        return this.http.put<IArma>(this.resourceUrl, arma, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IArma>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IArma[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
