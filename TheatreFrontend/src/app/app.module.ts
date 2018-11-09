import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { CardComponent } from './component/card/card.component';
import { BodyComponent } from './component/body/body.component';
import { HttpClientModule } from '@angular/common/http';
import { SeatComponent } from './component/seat/seat.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TheatreRegistrationFormComponent } from './component/theatre-registration-form/theatre-registration-form.component';
import { CustomValidators } from './services/custom_validators';
import { FormService } from './services/form';
import { RouterModule } from '@angular/router';
import { MatInputModule, MatButtonModule, MatSnackBarModule } from '@angular/material';
import { MaterialModule } from './material';


@NgModule({
  declarations: [
    AppComponent,
    CardComponent,
    BodyComponent,
    SeatComponent,
    TheatreRegistrationFormComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    MaterialModule,
    BrowserAnimationsModule,
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,

    RouterModule.forRoot([
      {path: 'registrationform', component: TheatreRegistrationFormComponent},
      {path: '**', component: BodyComponent}
    ])
  ],
  providers: [ CustomValidators, FormService],
  bootstrap: [AppComponent]
})
export class AppModule { }
