import { EspecialidadeService } from '@internacao/services/especialidade.service';
import { Especialidade } from './../../../internacao/models/especialidade';
import { CadaverModel } from './../../models/subjects/cadaver-model';
import { Component, OnInit } from '@angular/core';
import { CadaverService } from '../../services/cadaver.service';
import { ControleQualidadeservice } from '../../services/controleQualidade.service';
import { ControleQualidadeModel } from '../../models/subjects/controleQualidade-model';
import { LaboratorioExternoService } from '../../services/laboratorioExterno.service';
import { LaboratorioExternoModel } from '../../models/subjects/laboratorioExterno-model';
import { CentroService } from '@internacao/formulario-unidades/services/centro-de-atividade.service';
import { CentroDeAtividade } from '@internacao/formulario-unidades/models/dropwdowns/centro-de-atividade';


@Component({
  selector: 'app-atendimento-diverso',
  templateUrl: './atendimento-diverso.component.html',
  styleUrls: ['./atendimento-diverso.component.css'],

})

export class AtendimentoDiversoComponent implements OnInit {

  cadavers: CadaverModel[];
  controles: ControleQualidadeModel[];
  laboratorios: LaboratorioExternoModel[];
  centros: CentroDeAtividade[];
  especialidades: Especialidade[];
 

  TipoAmostra: string[] = ["Doador","Receptor",];
  selectedTipo: string; 

  OrigemAmostra: string[] = ["Humano","Não Humano",];
  selectedOrigem: string;

  Sexo: string[] = ["Feminino","Masculino","Ignorado",];
  selectedSexo: string;

  constructor(private cadaverService: CadaverService, 
    private controleQualidadeservice: ControleQualidadeservice,
    private laboratorioExternoService: LaboratorioExternoService,
    private centroService: CentroService,
    private especialidadeservice: EspecialidadeService) { }

     ngOnInit() : void{

       this.cadaverService.GetCadaver().subscribe((response)=>{
         this.cadavers = response; 
       });

       this.controleQualidadeservice.GetControleQualidade().subscribe((response)=>{
        this.controles = response; 
       });

       this.laboratorioExternoService.GetLaboratorioExterno().subscribe((response)=>{
        this.laboratorios = response; 
       });

       this.laboratorioExternoService.GetLaboratorioExterno().subscribe((response)=>{
        this.laboratorios = response; 
       });

       this.centroService.getListaDeCentros().subscribe((response)=>{
         this.centros = response;
       });

       this.especialidadeservice.getEspecialidades().subscribe((response)=>{
        this.especialidades = response; 
       })
     }
    
}
