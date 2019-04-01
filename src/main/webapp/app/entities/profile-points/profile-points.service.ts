import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProfilePoints } from 'app/shared/model/profile-points.model';

type EntityResponseType = HttpResponse<IProfilePoints>;
type EntityArrayResponseType = HttpResponse<IProfilePoints[]>;

@Injectable({ providedIn: 'root' })
export class ProfilePointsService {
    public resourceUrl = SERVER_API_URL + 'api/profile-points';

    constructor(protected http: HttpClient) {}

    create(profilePoints: IProfilePoints): Observable<EntityResponseType> {
        return this.http.post<IProfilePoints>(this.resourceUrl, profilePoints, { observe: 'response' });
    }

    update(profilePoints: IProfilePoints): Observable<EntityResponseType> {
        return this.http.put<IProfilePoints>(this.resourceUrl, profilePoints, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IProfilePoints>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IProfilePoints[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
