import { Component, OnInit } from '@angular/core';
import { Tema } from './Tema';
import { TemaService } from './services/tema.service';

@Component({
  selector: 'app-temas',
  standalone: false,
  templateUrl: './temas.html',
  styleUrl: './temas.css',
})
export class Temas implements OnInit{
  temas!: Tema[];

  constructor(public temaService: TemaService ) {
  }

  ngOnInit(): void {
    this.temaService.getTemas().subscribe((temas) => {
      console.log('Temas recibidos:', temas);
      this.temas=temas;
    });
  }

}
