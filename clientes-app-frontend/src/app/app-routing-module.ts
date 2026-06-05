import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Temas } from './temas/temas';
import { Modulos } from './modulos/modulos';

const routes: Routes = [
  { path: '', component: Temas },
  { path: 'modulos', component: Modulos },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
