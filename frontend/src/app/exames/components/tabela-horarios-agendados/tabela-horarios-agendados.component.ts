import { Component, Input, OnInit } from '@angular/core';
import { GradesDeAgendamento } from '../../models/subjects/grades-de-agendamento';
import { HorarioAgendado } from '../../models/subjects/horario-agendado';
import { HorarioLivre } from '../../models/subjects/horario-livre';
import { GradeDeAgendamentoService } from '../../services/grade-de-agendamento.service';

@Component({
  selector: 'app-tabela-horarios-agendados',
  templateUrl: './tabela-horarios-agendados.component.html',
  styleUrls: ['./tabela-horarios-agendados.component.css']
})
export class TabelaHorariosAgendadosComponent implements OnInit {

  id: string = '';
  horaInicio: string = '';
  horaFim: string = '';
  numeroDeHorarios: string = '';
  dia: string = '';
  duracao: string = '';
  ativo: string = '';
  exclusivo: string = '';
  mostrarModal: boolean;

  dataInicial: Date;
  dataFinal: Date;
  ocupado: boolean;

  horariosAgendados: HorarioAgendado[];

  @Input()
  grade: GradesDeAgendamento;

  constructor(private gradeAgendamentoService: GradeDeAgendamentoService) { }

  ngOnInit(): void {
    this.listarHorariosAgendados();
  }

  mostrarFormModal() {
    this.mostrarModal = true;
  }

  listarHorariosAgendados() {
    this.gradeAgendamentoService.getHorariosAgendados().subscribe((response) => {
      this.horariosAgendados = response;
    });
  }

  gerarDisponibilidade() {
    const horarioLivre: HorarioLivre = {
      dataInicial: this.dataInicial,
      dataFinal: this.dataFinal,
      ocupado: this.ocupado
    };

    this.gradeAgendamentoService.cadastrarHorarioLivre(horarioLivre).subscribe();
    this.limparFormulario();
  }

  limparFormulario() {
    this.ocupado = null;
    this.dataInicial = null;
    this.dataFinal = null;
  }

  validarFormulario(): boolean {
    if (this.dataFinal && this.ocupado != null && this.dataInicial) {
      return true;
    }
  }

}
