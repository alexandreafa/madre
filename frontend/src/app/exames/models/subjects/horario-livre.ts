export class HorarioLivre {
    constructor(
        public id?: number,
        public dataInicial?: Date,
        public dataFinal?: Date,
        public ocupado?: boolean,
        public horarioAgendadoId?: number,
    ) { }
}