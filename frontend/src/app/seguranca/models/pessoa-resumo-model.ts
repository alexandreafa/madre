export interface IPessoa {
    nome?: string;
    id?: number;
    codigo?: number;
    dataDeNascimento?: Date;
}

export class Pessoa implements IPessoa {
    constructor( 
        public nome?: string,
        public id?: number,
        public codigo?: number,
        public dataDeNascimento?: Date,

    ){}
}