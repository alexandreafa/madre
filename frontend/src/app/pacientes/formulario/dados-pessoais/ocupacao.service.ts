import { Ocupacao } from '../../models/dropdowns/types/ocupacao';
import { Injectable } from '@angular/core';
import { CrudServiceNuvem } from '@nuvem/primeng-components';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class OcupacaoService extends CrudServiceNuvem<number, Ocupacao> {
    
    constructor(private httpClient: HttpClient) {
        super('pacientes/api/ocupacaos', httpClient);
    }

    getListaDeOcupacoes(): Observable<Ocupacao[]> {
        return this.httpClient.get<Ocupacao[]>('pacientes/api/ocupacaos');
    }

    getOcupacoes():Observable<Array<Ocupacao>> {
        return this.httpClient.get<Array<Ocupacao>>(`pacientes/api/ocupacaos`);
    }
}
