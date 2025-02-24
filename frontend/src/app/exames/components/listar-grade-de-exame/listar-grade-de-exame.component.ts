import { Component, OnInit } from '@angular/core';
import { GradesDeAgendamento } from '../../models/subjects/grades-de-agendamento';
import { GrupoModel } from '../../models/subjects/grupo-model';
import { ExamModel } from '../../models/subjects/exames-model';
import { Responsavel } from '../../models/subjects/responsavel-model';
import { Sala } from '../../models/subjects/sala';
import { UnidadeFuncional } from '../../models/subjects/unidade-model';
import { ExamesService } from '../../services/exames.service';
import { GradeDeAgendamentoService } from '../../services/grade-de-agendamento.service';
import { GruposExamesService } from '../../services/grupos-exames.service';
import { UnidadeFuncionalService } from '../../services/unidade-funcional.service';
import { SituacaoAtivo } from '../../models/dropdowns/situacao.dropdown';
import { ListaServidor } from 'src/app/seguranca/models/dropdowns/lista-servidor';
import { ServidorService } from 'src/app/seguranca/services/servidor.service';


@Component({
  selector: 'app-listar-grade-de-exame',
  templateUrl: './listar-grade-de-exame.component.html',
  styleUrls: ['./listar-grade-de-exame.component.css']
})
export class ListarGradeDeExameComponent implements OnInit {

  id: string = '';
  unidadeExecutoraId: string = '';
  ativo: string = '';
  gradeDeAgendamentoId: string = '';
  salaId: string = '';
  grupoAgendamentoExameId: string = '';
  exameId: string = '';
  responsavelId: string = '';
  results = [];
 
  gradeAgendamento: GradesDeAgendamento[];
  unidadesExecutoras: UnidadeFuncional[] = [];
  servidores: ListaServidor[] = [];
  salas: Sala[] = [];
  gruposDeExame: GrupoModel[] = [];
  exames: ExamModel[] = [];
  responsaveis: Responsavel[] = [];

  situacaoGrade = SituacaoAtivo;

  searchUrl:string = 'madreexames/api/_search/grades-de-agendamento';


  constructor(private gradeAgendamentoService: GradeDeAgendamentoService,
    private unidadeFuncionalService: UnidadeFuncionalService,
    private servidorService: ServidorService,
    private exameService: ExamesService,
    private grupoExameService: GruposExamesService) { }


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

    this.listarGrades();
  }

  listarGrades() {
    this.gradeAgendamentoService.getGradesDeAgendamento(this.id, this.unidadeExecutoraId,
      this.ativo, this.grupoAgendamentoExameId, this.exameId, this.responsavelId)
      .subscribe((response) => {
        this.gradeAgendamento = response;
       });
  }

}
