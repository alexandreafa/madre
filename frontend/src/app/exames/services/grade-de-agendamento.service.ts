import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { GradesDeAgendamento } from '../models/subjects/grades-de-agendamento';
import { Sala } from '../models/subjects/sala';
import { TipoDeMarcacao } from '../models/subjects/tipo-de-marcacao';
import { HorarioAgendado } from '../models/subjects/horario-agendado';
import { HorarioLivre } from '../models/subjects/horario-livre';

@Injectable({
  providedIn: 'root'
})
export class GradeDeAgendamentoService {

  private readonly URL = '/madreexames/api'

  constructor(
    private client: HttpClient
  ) { }

  public getGradesDeAgendamento(
    id: string,
    unidadeExecutoraId: string,
    ativo: string,
    grupoGradeNome: string,
    exameGradeNome: string,
    responsavelNome: string
  ): Observable<GradesDeAgendamento[]> {
    return this.client.get<GradesDeAgendamento[]>(`${this.URL}/_search/grades-de-agendamento`, {
      params: new HttpParams()
        .set('id', id)
        .set('unidadeExecutoraId', unidadeExecutoraId)
        .set('ativo', ativo)
        .set('grupoGradeNome', grupoGradeNome)
        .set('exameGradeNome', exameGradeNome)
        .set('responsavelNome', responsavelNome)
    });
  }

  public getSalas(): Observable<Array<Sala>> {
    return this.client.get<Array<Sala>>(`${this.URL}/salas`);
  }

  public getTiposDeMarcacao(): Observable<Array<TipoDeMarcacao>> {
    return this.client.get<Array<TipoDeMarcacao>>(`${this.URL}/tipo-de-marcacaos`);
  }

  public getHorariosAgendados(): Observable<Array<HorarioAgendado>> {
    return this.client.get<Array<HorarioAgendado>>(`${this.URL}/_search/horarios-agendados`);
  }

  public getHorariosAgendadosPorGrade(
    gradeDeAgendamentoId: string
  ): Observable<Array<HorarioAgendado>> {
    return this.client.get<Array<HorarioAgendado>>(`${this.URL}/_search/horarios-grades`, {
      params: new HttpParams()
      .set('gradeDeAgendamentoId', gradeDeAgendamentoId)
    });
  }

  cadastrarGrade(gradeDeAgendamento: GradesDeAgendamento) {
    return this.client.post(`${this.URL}/grade-de-agendamentos`, gradeDeAgendamento);
  }

  cadastrarHorarioGrade(horarioGrade: HorarioAgendado) {
    return this.client.post(`${this.URL}/horario-agendados`, horarioGrade);
  }

  cadastrarHorarioLivre(horarioLivre: HorarioLivre) {
    return this.client.post(`${this.URL}/horario-livres`, horarioLivre);
  }

}
