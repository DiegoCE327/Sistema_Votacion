export class Resultado {
    _id? : string;

    mesa?: {
        _id? : string,
        numero?: string,
        };

    candidato?: {
        _id? : string,
        cedula?: string,
        nombre?: string,
        apellido?:string,
        partido?:{
            _id?:string,
            nombre?:string,
           };
        };

    numero_votos?:string
}
