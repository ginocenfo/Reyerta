export interface IDado {
    id?: number;
    size?: number;
    cant?: number;
}

export class Dado implements IDado {
    constructor(public id?: number, public size?: number, public cant?: number) {}
}
