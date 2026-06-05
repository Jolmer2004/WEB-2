import { Component, OnInit } from '@angular/core';
import { Modulo } from './Modulo';
import { ModuloService } from './services/modulo.service';

@Component({
  selector: 'app-modulos',
  templateUrl: './modulos.html',
  standalone: false,
  styleUrl: './modulos.css',
})
export class Modulos implements OnInit {
  modulos: Modulo[] = [];

  constructor(private moduloService: ModuloService) {}

  ngOnInit(): void {
    this.moduloService.getModulos().subscribe((modulo) => {
      console.log('modulos recibidos:', modulo);
      this.modulos = modulo;
    });
  }
}
