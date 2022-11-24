import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ResultadosRoutingModule } from './resultados-routing.module';
import { ListarComponent } from './listar/listar.component';
import { NbCardModule } from '@nebular/theme';


@NgModule({
  declarations: [
    ListarComponent
  ],
  imports: [
    CommonModule,
    ResultadosRoutingModule,
    NbCardModule
  ]
})
export class ResultadosModule { }
