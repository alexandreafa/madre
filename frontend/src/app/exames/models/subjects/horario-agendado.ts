export class HorarioAgendado {
    constructor(
        public id?: number,
        public horaInicio?: Date,
        public horaFim?: Date,
        public numeroDeHorarios?: number,
        public dia?: string,
        public duracao?: moment.Duration,
        public ativo?: boolean,
        public exclusivo?: boolean,
        public tipoHorarioId?: number,
        public tipoHorarioTipoDeMarcacao?: string,
        public gradeDeAgendamentoId?: number
    ) { }
}