export class Candidato {
    _id? : string;
    cedula? : string;
    numero_resolucion? : string;
    nombre? : string;
    apellido? : string;
    partido?: {
        _id? : string,
        nombre?: string,
        lema?: string,
        }
}
