import { IDado } from 'app/shared/model/dado.model';
import { IPersonaje } from 'app/shared/model/personaje.model';

export const enum IncrustacionesArmadura {
    ACORAZADA_MENOR = 'ACORAZADA_MENOR',
    ATRAYENTE_MENOR = 'ATRAYENTE_MENOR',
    CURATIVA_BASICA = 'CURATIVA_BASICA',
    ESCAPISTA = 'ESCAPISTA',
    FLEXIBLE = 'FLEXIBLE',
    OPACA = 'OPACA',
    PROTECTORA = 'PROTECTORA',
    SILENCIOSA = 'SILENCIOSA',
    REFORZADA_MENOR = 'REFORZADA_MENOR',
    ACORAZADA = 'ACORAZADA',
    ANARQUICA = 'ANARQUICA',
    ATRAYENTE = 'ATRAYENTE',
    AXIOMATICA = 'AXIOMATICA',
    CAMBIANTE = 'CAMBIANTE',
    CURATIVA_CRITICA = 'CURATIVA_CRITICA',
    PROFANA = 'PROFANA',
    RESISTENTE_MENOR = 'RESISTENTE_MENOR',
    SAGRADA = 'SAGRADA',
    VALIENTE = 'VALIENTE',
    ACORAZADA_MAYOR = 'ACORAZADA_MAYOR',
    ATRAYENTE_MAYOR = 'ATRAYENTE_MAYOR',
    CURATIVA_SEVERA = 'CURATIVA_SEVERA',
    ETEREA = 'ETEREA',
    INDESTRUCTIBLE = 'INDESTRUCTIBLE',
    REFORZADA = 'REFORZADA',
    RESISTENTE = 'RESISTENTE',
    DESAFIANTE = 'DESAFIANTE',
    RESISTENTE_MAYOR = 'RESISTENTE_MAYOR',
    REFORZADA_MAYOR = 'REFORZADA_MAYOR',
    REDENTORA = 'REDENTORA',
    ACORAZADO_MENOR = 'ACORAZADO_MENOR',
    ALIENTODRAGON = 'ALIENTODRAGON',
    BATIENTE = 'BATIENTE',
    CAUSTICO_MENOR = 'CAUSTICO_MENOR',
    CECADOR = 'CECADOR',
    CONCUSION = 'CONCUSION',
    PROTECTOR = 'PROTECTOR',
    PUNZANTE = 'PUNZANTE',
    ACORAZADO = 'ACORAZADO',
    CAUSTICO = 'CAUSTICO',
    REFLEJANTE_MENOR = 'REFLEJANTE_MENOR',
    ACORAZADO_MAYOR = 'ACORAZADO_MAYOR',
    CAUTICO_MAYOR = 'CAUTICO_MAYOR',
    FLOTANTE = 'FLOTANTE',
    REFLEJANTE = 'REFLEJANTE',
    REFLEJANTE_MAYOR = 'REFLEJANTE_MAYOR',
    NONE = 'NONE'
}

export interface IArmadura {
    id?: number;
    type?: string;
    name?: string;
    evasionBonus?: number;
    maxDex?: number;
    level?: number;
    uc?: number;
    ppa?: number;
    castFailChance?: number;
    price?: number;
    incrustacionArmadura?: IncrustacionesArmadura;
    damageByShield?: IDado;
    personaje?: IPersonaje;
}

export class Armadura implements IArmadura {
    constructor(
        public id?: number,
        public type?: string,
        public name?: string,
        public evasionBonus?: number,
        public maxDex?: number,
        public level?: number,
        public uc?: number,
        public ppa?: number,
        public castFailChance?: number,
        public price?: number,
        public incrustacionArmadura?: IncrustacionesArmadura,
        public damageByShield?: IDado,
        public personaje?: IPersonaje
    ) {}
}
