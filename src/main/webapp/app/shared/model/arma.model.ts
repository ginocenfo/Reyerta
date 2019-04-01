import { IDado } from 'app/shared/model/dado.model';
import { IAic } from 'app/shared/model/aic.model';
import { IPersonaje } from 'app/shared/model/personaje.model';

export const enum DamageType {
    CORTANTE = 'CORTANTE',
    PERFORANTE = 'PERFORANTE',
    IMPACTANTE = 'IMPACTANTE',
    ENERGIA = 'ENERGIA',
    AGUA = 'AGUA',
    AIRE = 'AIRE',
    FUEGO = 'FUEGO',
    TIERRA = 'TIERRA',
    LUZ = 'LUZ',
    SOMBRA = 'SOMBRA',
    METAL = 'METAL',
    ROCA = 'ROCA',
    ELECTRICIDAD = 'ELECTRICIDAD',
    HIELO = 'HIELO'
}

export const enum Special {
    NOLETAL = 'NOLETAL',
    LETAL = 'LETAL',
    ARROJADIZA = 'ARROJADIZA',
    DERRIBAR = 'DERRIBAR',
    FRAGIL = 'FRAGIL',
    MADERA = 'MADERA',
    DOBLE = 'DOBLE',
    MONJE = 'MONJE',
    APUNTALAR = 'APUNTALAR',
    ALCANCE = 'ALCANCE',
    DESARMAR = 'DESARMAR',
    NINGUNO = 'NINGUNO'
}

export const enum IncrustacionesArma {
    AMENAZANTE = 'AMENAZANTE',
    ANULANTE_MENOR = 'ANULANTE_MENOR',
    ARROJADIZA = 'ARROJADIZA',
    ATINANTE = 'ATINANTE',
    BALANCEADA = 'BALANCEADA',
    BRILALNTE = 'BRILALNTE',
    CASTIGADORA = 'CASTIGADORA',
    COMPASIVA = 'COMPASIVA',
    CORROSIVA = 'CORROSIVA',
    DEFENSORA = 'DEFENSORA',
    DISTANTE = 'DISTANTE',
    ENCANTADA_MENOR = 'ENCANTADA_MENOR',
    FILOSA = 'FILOSA',
    PROTECTORA = 'PROTECTORA',
    RETORNANTE = 'RETORNANTE',
    VICTORIOSA = 'VICTORIOSA',
    DINAMO = 'DINAMO',
    ANARQUICA = 'ANARQUICA',
    ANULANTE = 'ANULANTE',
    AXIOMATICA = 'AXIOMATICA',
    CONTUNDENTE = 'CONTUNDENTE',
    ENCANTADA = 'ENCANTADA',
    LACERANTE = 'LACERANTE',
    PROFANA = 'PROFANA',
    SAGRADA = 'SAGRADA',
    VAMPIRICA = 'VAMPIRICA',
    RADIANTE = 'RADIANTE',
    DINAMO_OPTIMIZADO = 'DINAMO_OPTIMIZADO',
    ANIMADA = 'ANIMADA',
    ANULANTE_MAYOR = 'ANULANTE_MAYOR',
    BUSCADORA = 'BUSCADORA',
    ETEREA = 'ETEREA',
    ENCANTADA_MAYOR = 'ENCANTADA_MAYOR',
    INVISIBLE = 'INVISIBLE',
    INVOCABLE = 'INVOCABLE',
    VELOZ = 'VELOZ',
    RADIANTE_EXPLOSIVA = 'RADIANTE_EXPLOSIVA',
    EXORCISTA = 'EXORCISTA',
    INDESTRUCTIBLE = 'INDESTRUCTIBLE',
    EJECUTORA = 'EJECUTORA',
    SOLARIANA = 'SOLARIANA',
    NONE = 'NONE'
}

export interface IArma {
    id?: number;
    type?: string;
    name?: string;
    prize?: number;
    level?: number;
    isMasterpiece?: boolean;
    eliteLevel?: number;
    damageType?: DamageType;
    special?: Special;
    incrustacion?: IncrustacionesArma;
    dado?: IDado;
    crit?: IAic;
    personaje?: IPersonaje;
}

export class Arma implements IArma {
    constructor(
        public id?: number,
        public type?: string,
        public name?: string,
        public prize?: number,
        public level?: number,
        public isMasterpiece?: boolean,
        public eliteLevel?: number,
        public damageType?: DamageType,
        public special?: Special,
        public incrustacion?: IncrustacionesArma,
        public dado?: IDado,
        public crit?: IAic,
        public personaje?: IPersonaje
    ) {
        this.isMasterpiece = this.isMasterpiece || false;
    }
}
