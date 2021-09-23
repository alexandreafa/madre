import { Component, Input, OnInit, Output, ViewChild } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import * as moment from 'moment';
import { MessageService } from 'primeng/api';
import { DiaSemana } from '../../models/dropdowns/dia.dropdown';
import { GradesDeAgendamento } from '../../models/subjects/grades-de-agendamento';
import { HorarioAgendado } from '../../models/subjects/horario-agendado';
import { TipoDeMarcacao } from '../../models/subjects/tipo-de-marcacao';
import { GradeDeAgendamentoService } from '../../services/grade-de-agendamento.service';
import { TabelaHorariosAgendadosComponent } from '../tabela-horarios-agendados/tabela-horarios-agendados.component';

@Component({
  selector: 'app-formulario-horarios-agendados',
  templateUrl: './formulario-horarios-agendados.component.html',
  styleUrls: ['./formulario-horarios-agendados.component.css']
})
export class FormularioHorariosAgendadosComponent implements OnInit {

  horaInicio: Date;
  horaFim: Date;
  duracaoPadrao = new Date('December 31, 2021 00:30:00');
  horaPadrao = new Date('December 31, 2020 12:00:00');
  dia = DiaSemana;
  diaSelecionado: string;
  tiposDeMarcacao: TipoDeMarcacao[] = [];
  numeroDeHorarios: number;
  tipoDeMarcacao: number;

  @Input()
  grade: GradesDeAgendamento;

  @Output()
  gradeAtual: GradesDeAgendamento;

  @ViewChild(TabelaHorariosAgendadosComponent)
  appTabela: TabelaHorariosAgendadosComponent;

  constructor(private gradeService: GradeDeAgendamentoService,
    private fb: FormBuilder, private msg: MessageService) { }

  ngOnInit(): void {
    this.listarTiposDeMarcacao();
  }

  agendarHorario = this.fb.group({
    numeroDeHorarios: [null],
    duracao: [null, Validators.required],
    ativo: [null, Validators.required],
    exclusivo: [null, Validators.required]
  });

  agendarHorarioGrade() {
    const cadastroHorario = this.agendarHorario.value;

    const cadastro: HorarioAgendado = {
      horaInicio: this.gerarHora(this.horaInicio),
      horaFim: this.horaFim != null ? this.gerarHora(this.horaFim) : null,
      dia: this.diaSelecionado,
      duracao: this.gerarDuracao(cadastroHorario.duracao),
      ativo: cadastroHorario.ativo,
      exclusivo: cadastroHorario.exclusivo,
      numeroDeHorarios: cadastroHorario.numeroDeHorarios,
      tipoHorarioId: this.tipoDeMarcacao,
      gradeDeAgendamentoId: this.grade.id
    };

     if (this.isHoraFinalOuNumeroDeHorariosNulo(this.numeroDeHorarios, this.horaFim) || this
     .isHoraInicioDepoisDeHoraFim(this.horaInicio, this.horaFim) || this
     .isNumeroDeHorariosMaiorQueZero(this.numeroDeHorarios, this.horaFim)){
      return;
    }else {
      this.gradeService.cadastrarHorarioGrade(cadastro).subscribe(() => {
          this.appTabela.listarHorariosPorGrade();
        });
        this.limparFormulario();
    }
  }

  gerarHora(horaPreenchida: Date) {
    let horaGerada = new Date(Date.UTC(horaPreenchida.getFullYear(), horaPreenchida.getMonth(), horaPreenchida.getDate(),
    horaPreenchida.getHours(), horaPreenchida.getMinutes()));
    return horaGerada;
  }

  gerarDuracao(dataPreenchida: Date): moment.Duration {
    let valorDuracao = moment.duration({
      minutes: dataPreenchida.getMinutes(),
      hours: dataPreenchida.getHours()
    });
    return valorDuracao;
  }

  isHoraInicioDepoisDeHoraFim(horaInicio: Date, horaFim: Date): boolean {
    if (moment(horaInicio).isAfter(horaFim) && horaFim != null) {
      this.msg.add({
        severity: 'error', summary: 'Erro no preenchimento',
        detail: 'Hora fim deve ser depois de hora início.'
      })
      return true;
    }
  }

  isHoraFinalOuNumeroDeHorariosNulo(numeroDeHorarios: number, horaFim: Date): boolean {
    if (numeroDeHorarios != null && horaFim != null) {
      this.msg.add({
        severity: 'error', summary: 'Erro no preenchimento',
        detail: 'Não informar hora fim e número de horários ao mesmo tempo.'
      })
      return true;
    }
  }

  isNumeroDeHorariosMaiorQueZero(numeroDeHorarios: number, horaFim: Date): boolean {
    if (numeroDeHorarios <= 0 && horaFim == null) {
      this.msg.add({
        severity: 'error', summary: 'Erro no preenchimento',
        detail: 'O número de horários deve ser maior que zero.'
      })
      return true;
    }
  }

  validarFormulario(): boolean {
    if (this.agendarHorario.valid && (this.diaSelecionado && this.horaInicio && this.tipoDeMarcacao))
      return true;
    else
      return false;
  }

  limparFormulario() {
    this.agendarHorario.reset();
    this.diaSelecionado = null;
    this.horaFim = null;
    this.horaInicio = null;
    this.tipoDeMarcacao = null;
  }

  listarTiposDeMarcacao() {
    this.gradeService.getTiposDeMarcacao().subscribe((response) => {
      this.tiposDeMarcacao = response;
    });
  }

}