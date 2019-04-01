import { IArma } from 'app/shared/model/arma.model';
import { IArmadura } from 'app/shared/model/armadura.model';

export const enum Alignment {
    CAOTICO_BUENO = 'CAOTICO_BUENO',
    CAOTICO_NEUTRAL = 'CAOTICO_NEUTRAL',
    CAOTICO_MALO = 'CAOTICO_MALO',
    NEUTRAL_BUENO = 'NEUTRAL_BUENO',
    NEUTRAL_NEUTRAL = 'NEUTRAL_NEUTRAL',
    NEUTRAL_MALO = 'NEUTRAL_MALO',
    LEGAL_BUENO = 'LEGAL_BUENO',
    LEGAL_NEUTRAL = 'LEGAL_NEUTRAL',
    LEGAL_MALO = 'LEGAL_MALO'
}

export const enum Dexterity {
    DIESTRO = 'DIESTRO',
    ZURDO = 'ZURDO'
}

export const enum Sizes {
    GIGANTIC = 'GIGANTIC',
    LARGE = 'LARGE',
    MEDIUM = 'MEDIUM',
    SMALL = 'SMALL',
    TINY = 'TINY'
}

export const enum Races {
    ALARDYS = 'ALARDYS',
    ALFIR_ELAK = 'ALFIR_ELAK',
    ALFIR_GYLENE = 'ALFIR_GYLENE',
    ALFIR_MUANEN = 'ALFIR_MUANEN',
    DHAMPIR = 'DHAMPIR',
    DOKKALFAR = 'DOKKALFAR',
    GNOMO = 'GNOMO',
    IYIWAK_ICHICK = 'IYIWAK_ICHICK',
    IYIWAK_NAMU = 'IYIWAK_NAMU',
    IYIWAK_URRI = 'IYIWAK_URRI',
    HUMANO = 'HUMANO',
    MITALFIR = 'MITALFIR',
    VARKOLAK = 'VARKOLAK'
}

export const enum CharacterClase {
    ASESINO = 'ASESINO',
    BARBARO = 'BARBARO',
    BARDO = 'BARDO',
    CABALLERO_OSCURO = 'CABALLERO_OSCURO',
    CAZADOR = 'CAZADOR',
    CLERIGO = 'CLERIGO',
    ESPADACHIN = 'ESPADACHIN',
    LADRON = 'LADRON',
    MAGO = 'MAGO',
    MONJE = 'MONJE',
    NINJA = 'NINJA',
    PALADIN = 'PALADIN',
    PELEADOR = 'PELEADOR',
    PISTOLERO = 'PISTOLERO',
    SAMURAI = 'SAMURAI',
    LANCERO = 'LANCERO',
    TECNOLOGO = 'TECNOLOGO',
    ALQUIMISTA = 'ALQUIMISTA'
}

export interface IPersonaje {
    id?: number;
    ownerName?: string;
    characterName?: string;
    alignment?: Alignment;
    religion?: string;
    gender?: string;
    realAge?: number;
    apparentAge?: number;
    dexterity?: Dexterity;
    originName?: string;
    size?: Sizes;
    height?: number;
    weight?: number;
    eyeColor?: string;
    hairColor?: string;
    tezColor?: string;
    maxHitPoints?: number;
    currentHitPoints?: number;
    race?: Races;
    characterClass?: CharacterClase;
    armas?: IArma[];
    armaduras?: IArmadura[];
}

export class Personaje implements IPersonaje {
    constructor(
        public id?: number,
        public ownerName?: string,
        public characterName?: string,
        public alignment?: Alignment,
        public religion?: string,
        public gender?: string,
        public realAge?: number,
        public apparentAge?: number,
        public dexterity?: Dexterity,
        public originName?: string,
        public size?: Sizes,
        public height?: number,
        public weight?: number,
        public eyeColor?: string,
        public hairColor?: string,
        public tezColor?: string,
        public maxHitPoints?: number,
        public currentHitPoints?: number,
        public race?: Races,
        public characterClass?: CharacterClase,
        public armas?: IArma[],
        public armaduras?: IArmadura[]
    ) {}
}
