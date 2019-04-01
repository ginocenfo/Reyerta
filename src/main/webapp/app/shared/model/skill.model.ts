export const enum Profile {
    CARISMA = 'CARISMA',
    CONSTITUCION = 'CONSTITUCION',
    DESTREZA = 'DESTREZA',
    FUERZA = 'FUERZA',
    INTELIGENCIA = 'INTELIGENCIA',
    SABIDURIA = 'SABIDURIA'
}

export interface ISkill {
    id?: number;
    name?: string;
    relatedProfile?: Profile;
    grade?: number;
    magicObject?: number;
    other?: number;
    penalizer?: number;
    isTrained?: boolean;
    isExpertise?: boolean;
    isPenalized?: boolean;
    isPenalizedByLanguage?: boolean;
    girsoPieces?: number;
    platinumPieces?: number;
    goldPieces?: number;
    silverPieces?: number;
    copperPieces?: number;
}

export class Skill implements ISkill {
    constructor(
        public id?: number,
        public name?: string,
        public relatedProfile?: Profile,
        public grade?: number,
        public magicObject?: number,
        public other?: number,
        public penalizer?: number,
        public isTrained?: boolean,
        public isExpertise?: boolean,
        public isPenalized?: boolean,
        public isPenalizedByLanguage?: boolean,
        public girsoPieces?: number,
        public platinumPieces?: number,
        public goldPieces?: number,
        public silverPieces?: number,
        public copperPieces?: number
    ) {
        this.isTrained = this.isTrained || false;
        this.isExpertise = this.isExpertise || false;
        this.isPenalized = this.isPenalized || false;
        this.isPenalizedByLanguage = this.isPenalizedByLanguage || false;
    }
}
