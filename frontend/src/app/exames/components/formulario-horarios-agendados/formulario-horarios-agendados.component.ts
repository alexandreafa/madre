import { Time } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import * as moment from 'moment';
import { MessageService } from 'primeng/api';
import { DiaSemana } from '../../models/dropdowns/dia.dropdown';
import { GradesDeAgendamento } from '../../models/subjects/grades-de-agendamento';
import { HorarioAgendado } from '../../models/subjects/horario-agendado';
import { TipoDeMarcacao } from '../../models/subjects/tipo-de-marcacao';
import { GradeDeAgendamentoService } from '../../services/grade-de-agendamento.service';

@Component({
  selector: 'app-formulario-horarios-agendados',
  templateUrl: './formulario-horarios-agendados.component.html',
  styleUrls: ['./formulario-horarios-agendados.component.css']
})
export class FormularioHorariosAgendadosComponent implements OnInit {

  horaInicio: Date;
  horaFim: Date;
  duracao: Time;
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
      tipoHorarioId: this.tipoDeMarcacao,
      gradeDeAgendamentoId: this.grade.id
    };

     if (this.validarHoraOuNumeroDeHorarios(this.numeroDeHorarios, this.horaFim) || this
     .validarHoraInicioDepoisDeHoraFim(this.horaInicio, this.horaFim)){
      return;
    }else {
      this.gradeService.cadastrarHorarioGrade(cadastro).subscribe();
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

  validarHoraInicioDepoisDeHoraFim(horaInicio: Date, horaFim: Date): boolean {
    if (moment(horaInicio).isAfter(horaFim) && horaFim != null) {
      this.msg.add({
        severity: 'error', summary: 'Erro no preenchimento',
        detail: 'Hora fim deve ser depois de hora início.'
      })
      return true;
    }
  }

  validarHoraOuNumeroDeHorarios(numeroDeHorarios: number, horaFim: Date): boolean {
    if (numeroDeHorarios != null && horaFim != null) {
      this.msg.add({
        severity: 'error', summary: 'Erro no preenchimento',
        detail: 'Não informar hora fim e número de horários ao mesmo tempo.'
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
