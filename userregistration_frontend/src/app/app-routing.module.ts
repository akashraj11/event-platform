import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PersonalComponent } from './personal/personal.component';

import { GenreComponent } from './genre/genre.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AddressComponent } from './address/address.component'
const routes: Routes = [
  {path:'personal',component:PersonalComponent},
  {path:'genre',component:GenreComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponent = [PersonalComponent,GenreComponent,NavbarComponent,AddressComponent]
