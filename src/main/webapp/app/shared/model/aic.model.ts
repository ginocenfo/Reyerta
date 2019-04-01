export interface IAic {
    id?: number;
    prob?: number;
    mult?: number;
}

export class Aic implements IAic {
    constructor(public id?: number, public prob?: number, public mult?: number) {}
}
