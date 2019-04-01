export const enum Profile {
    CARISMA = 'CARISMA',
    CONSTITUCION = 'CONSTITUCION',
    DESTREZA = 'DESTREZA',
    FUERZA = 'FUERZA',
    INTELIGENCIA = 'INTELIGENCIA',
    SABIDURIA = 'SABIDURIA'
}

export interface IProfilePoints {
    id?: number;
    points?: number;
    perfil?: Profile;
}

export class ProfilePoints implements IProfilePoints {
    constructor(public id?: number, public points?: number, public perfil?: Profile) {}
}
