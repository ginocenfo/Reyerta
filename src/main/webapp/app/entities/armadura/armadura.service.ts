import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IArmadura } from 'app/shared/model/armadura.model';

type EntityResponseType = HttpResponse<IArmadura>;
type EntityArrayResponseType = HttpResponse<IArmadura[]>;

@Injectable({ providedIn: 'root' })
export class ArmaduraService {
    public resourceUrl = SERVER_API_URL + 'api/armaduras';

    constructor(protected http: HttpClient) {}

    create(armadura: IArmadura): Observable<EntityResponseType> {
        return this.http.post<IArmadura>(this.resourceUrl, armadura, { observe: 'response' });
    }

    update(armadura: IArmadura): Observable<EntityResponseType> {
        return this.http.put<IArmadura>(this.resourceUrl, armadura, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IArmadura>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IArmadura[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
