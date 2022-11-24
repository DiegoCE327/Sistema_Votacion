import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { Resultado } from '../../../modelos/resultado.model';
import { ResultadosService } from '../../../servicios/resultados.service';

@Component({
  selector: 'ngx-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.scss']
})
export class ListarComponent implements OnInit {

  resultados: Resultado[];
  nombresColumnas: string[] = ['Numero de mesa', 'Cedula', 'Nombre', 'Apellido', 'Partido', 'Numero de votos','Opciones'];

  constructor(private miServicioResultados: ResultadosService) { }

  ngOnInit(): void {
    this.listar();
  }

  listar(): void {
    this.miServicioResultados.listar().
      subscribe(data => {
        this.resultados = data;
      });
  }

  agregar(): void {
    console.log("agregando nuevo")
  }

  editar(id: string): void {
    console.log("editando a " + id)
  }

  eliminar(id: string): void {
    Swal.fire({
      title: 'Eliminar Resultado',
      text: "Confirmar para eliminar?",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.miServicioResultados.eliminar(id).
          subscribe(data => {
            Swal.fire(
              'Eliminado!',
              'El resultado ha sido eliminado correctamente',
              'success'
            )
            this.ngOnInit();
          });
      }
    })
  }
}
