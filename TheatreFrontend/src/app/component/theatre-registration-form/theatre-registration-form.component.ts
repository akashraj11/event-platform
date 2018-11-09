import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { CustomValidators } from '../../services/custom_validators';
import {  FormService } from '../../services/form';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Theatre } from '../../domain/theatre';
import { ProducerService } from '../../services/producer-service';
import { _MatFormFieldMixinBase } from '@angular/material';



@Component({
  selector: 'app-theatre-registration-form',
  templateUrl: './theatre-registration-form.component.html',
  styleUrls: ['./theatre-registration-form.component.css']
})
export class TheatreRegistrationFormComponent implements OnInit {
  email = "abcfd@gmail.com";
  public noOfSeatsGold= 0;
  noOfSeatsSilver= 0;
  noOfColumns1: 0;
  noOfRows1: 0;
  noOfColumns2: 0;
  noOfRows2: 0;
  public rows: string[];
	public cols: number[];
  i: number;
  j =0;
	public rows2: string[];
  public cols2: number[];
  

  public goldSeats = [];
  public silverSeats = [];
  id  = this.formService.getId();
  sub: any;
  public theatre:Theatre = {
    theatreId: this.id ,
    theatreName: '',
    theatreCity: '',
    theatreAddress: '',
    "screenLayout":
    {      
       "category":[
             {
            "type":"gold",
            "noOfColumns":3,
            "noOfRows":3,
            "seatLayout":[
                     {
                    "seatNumber":"A0"
                     }
                   ]
            },
            {
              "type":"silver",
              "noOfColumns":3,
              "noOfRows":3,
              "seatLayout":[]
              }
       ]
     }
  };



  public signUpForm: FormGroup;
  public formErrors = {

  };

  constructor(
    public form: FormBuilder,
    public formService: FormService,
    public snackbar: MatSnackBar,
    private producerService: ProducerService
  ) {}

  // initiate component
  public ngOnInit() {
    this.buildForm();
  }
  
  public submit() {
    this.formService.setId(this.id+1);
    console.log(this.id)
    // mark all fields as touched
    this.formService.markFormGroupTouched(this.signUpForm);

    this.theatre.theatreId = this.id;
    this.theatre.screenLayout.category[0].noOfColumns = this.noOfColumns1  ;
    this.theatre.screenLayout.category[0].noOfRows = this.noOfRows1  ;
    this.theatre.screenLayout.category[1].noOfColumns = this.noOfColumns2  ;
    this.theatre.screenLayout.category[1].noOfRows = this.noOfRows2  ;
    this.noOfSeatsGold = this.noOfColumns1 * this.noOfRows1;
    this.noOfSeatsSilver = this.noOfColumns2 * this.noOfRows2;

    this.rows = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'].slice(0, this.noOfRows1);
    this.cols = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10].slice(0, this.noOfColumns1);


    this.rows2 = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T'].slice(this.noOfRows1,this.noOfRows1+this.noOfRows2);
    this.cols2 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10].slice(0, this.noOfColumns2);

    for (let item of this.rows) {
      for (let item1 of this.cols) {
          this.goldSeats.push(item+item1);
        }
    }

    for (let item of this.rows2) {
      for (let item1 of this.cols2) {
          this.silverSeats.push(item+item1);
        }
    }
    console.log('gold', this.goldSeats);
    console.log('silver', this.silverSeats);
    this.theatre.screenLayout.category[0].seatLayout = this.goldSeats;
    this.theatre.screenLayout.category[1].seatLayout = this.silverSeats;

    this.theatre.screenLayout.category[0].type = 'Gold Class';
    this.theatre.screenLayout.category[1].type = 'Silver Class';

    this.producerService.addTheatre(this.email, this.theatre);
    this.id  = this.formService.getId();

    // right before we submit our form to the server we check if the form is valid
    // if not, we pass the form to the validateform function again. Now with check dirty false
    // this means we check every form field independent of wether it's touched 
    if (this.signUpForm.valid) {
      this.snackbar.open('Succesfully submitted a valid form. yay!', 'Close', {
        duration: 3000,
      });
      this.signUpForm.reset();
    } else {
      this.formErrors = this.formService.validateForm(this.signUpForm, this.formErrors, false)
    }
  }
  
  // build the user edit form
  public buildForm() {
    this.signUpForm = this.form.group({
      theatreId: ['', [Validators.required, CustomValidators.validateCharacters]],
      theatreName: ['', [Validators.required, CustomValidators.validateCharacters]],
      theatreCity: ['', [Validators.required, CustomValidators.validateCharacters]],
      theatreAddress: ['', [Validators.required, CustomValidators.validateCharacters]],
      noOfColumns1: ['', [Validators.required, CustomValidators.validateNumber]],
      noOfRows1:['', [Validators.required, CustomValidators.validateNumber]],
      noOfColumns2:['', [Validators.required, CustomValidators.validateNumber]],
      noOfRows2:['', [Validators.required, CustomValidators.validateNumber]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });

    // on each value change we call the validateForm function
    // We only validate form controls that are dirty, meaning they are touched
    // the result is passed to the formErrors object
    this.signUpForm.valueChanges.subscribe((data) => {
      this.formErrors = this.formService.validateForm(this.signUpForm, this.formErrors, true)
    });
  }
}
