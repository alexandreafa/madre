import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { UnidadeFuncional } from '../../models/subjects/unidade-model';
import { SituacaoAtivo } from '../../models/dropdowns/situacao.dropdown';
import { GradeDeAgendamentoService } from '../../services/grade-de-agendamento.service';
import { GrupoModel } from '../../models/subjects/grupo-model';
import { ExamModel } from '../../models/subjects/exames-model';
import { GradesDeAgendamento } from '../../models/subjects/grades-de-agendamento';
import { UnidadeFuncionalService } from '../../services/unidade-funcional.service';
import { Sala } from '../../models/subjects/sala';
import { ExamesService } from '../../services/exames.service';
import { GruposExamesService } from '../../services/grupos-exames.service';
import { ListaServidor } from 'src/app/seguranca/models/dropdowns/lista-servidor';
import { ServidorService } from 'src/app/seguranca/services/servidor.service';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-formulario-grade-de-agendamento',
  templateUrl: './formulario-grade-de-agendamento.component.html',
  styleUrls: ['./formulario-grade-de-agendamento.component.css']
})
export class FormularioGradeDeAgendamentoComponent implements OnInit {

  unidadesExecutoras: UnidadeFuncional[] = [];
  servidores: ListaServidor[] = [];
  gruposDeExame: GrupoModel[] = [];
  salas: Sala[] = [];
  exames: ExamModel[] = [];

  situacaoGrade = SituacaoAtivo;

  @Input()
  grade: GradesDeAgendamento;

  @Output()
  gradeSalva = new EventEmitter<GradesDeAgendamento>();

  constructor(private fb: FormBuilder,
    private gradeAgendamentoService: GradeDeAgendamentoService,
    private unidadeFuncionalService: UnidadeFuncionalService,
    private servidorService: ServidorService,
    private exameService: ExamesService,
    private grupoExameService: GruposExamesService,
    private confirmacaoService: ConfirmationService,
    private router: Router,
    private msg: MessageService) { }


  cadastroGrade = this.fb.group({
    unidadeExecutoraId: [null, Validators.required],
    responsavelId: [null, Validators.required],
    ativo: [null, Validators.required],
    exameGradeId: [null],
    grupoGradeId: [null],
    salaGradeId: [null, Validators.required],
  });

  validarFormulario(): boolean {
    if (this.cadastroGrade.valid && (this.cadastroGrade
      .get('grupoGradeId').value != null || this.cadastroGrade
        .get('exameGradeId').value != null)) {
      return true;
    }
  }

  limparFormulario() {
    this.cadastroGrade.reset();
  }

  confirmarGravacaoDaGrade() {
    this.confirmacaoService.confirm({
      message: 'Gostaria de agendar horários para esta grade agora?',
      header: 'Salvar grade',
      icon: 'pi pi-question',
      accept: () => {
        this.msg.add({
          severity: 'info',
          detail: "Acesse 'Horários Agendados' para marcar horários nessa grade."
        });
      },
      reject: () => {
        this.router.navigate(['/listar-grade-exame']);
      }
    });
  }

  cadastrarGradeDeAgendamento() {
    const cadastroGradeValor = this.cadastroGrade.value;

    this.grade = {
      unidadeExecutoraId: cadastroGradeValor.unidadeExecutoraId,
      responsavelId: cadastroGradeValor.responsavelId,
      ativo: cadastroGradeValor.ativo,
      salaGradeId: cadastroGradeValor.salaGradeId,
      salaGradeIdentificacaoDaSala: this.salas[cadastroGradeValor.salaGradeId - 1].identificacaoDaSala,
      exameGradeId: cadastroGradeValor.exameGradeId,
      exameGradeNome: this.exames[cadastroGradeValor.exameGradeId - 1].nome,
      grupoGradeId: cadastroGradeValor.grupoGradeId,
      grupoGradeNome: this.gruposDeExame[cadastroGradeValor.grupoGradeId - 1].nome,
    };

    this.gradeAgendamentoService.cadastrarGrade(this.grade).subscribe((response) => {
      Object.assign(this.grade, response);
      this.gradeSalva.emit(this.grade)
    });

    this.cadastroGrade.reset();
    this.confirmarGravacaoDaGrade();
  }

  ngOnInit(): void {
    this.unidadeFuncionalService.getUnidades().subscribe((response) => {
      this.unidadesExecutoras = response;
    });

    this.servidorService.getServidor().subscribe((response) => {
      this.servidores = response;
    });

    this.gradeAgendamentoService.getSalas().subscribe((response) => {
      this.salas = response;
    });

    this.exameService.GetExames().subscribe((response) => {
      this.exames = response;
    });

    this.grupoExameService.GetGrupos().subscribe((response) => {
      this.gruposDeExame = response;
    });


  }


}
