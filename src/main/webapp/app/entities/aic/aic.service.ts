import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAic } from 'app/shared/model/aic.model';

type EntityResponseType = HttpResponse<IAic>;
type EntityArrayResponseType = HttpResponse<IAic[]>;

@Injectable({ providedIn: 'root' })
export class AicService {
    public resourceUrl = SERVER_API_URL + 'api/aics';

    constructor(protected http: HttpClient) {}

    create(aic: IAic): Observable<EntityResponseType> {
        return this.http.post<IAic>(this.resourceUrl, aic, { observe: 'response' });
    }

    update(aic: IAic): Observable<EntityResponseType> {
        return this.http.put<IAic>(this.resourceUrl, aic, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAic>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAic[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
