import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from "@angular/forms";
import { RegistrationService } from '../registration.service';

export interface Location {
  value: string;
}
export interface Genre {
  id: boolean;
  name: string;
}

@Component({
  selector: 'app-personal',
  templateUrl: './personal.component.html',
  styleUrls: ['./personal.component.css'],
  providers: [RegistrationService]
})
export class PersonalComponent implements OnInit {
  isLinear = true;
  selectedLocation
  userName
  userEmail
  userPassword
  userPhone_number
  genderOfUser
  userDateOfBirth = "";
  completeDetails = []
  selectGenre;
  gen = []
  public userDetails: FormGroup
  public stepDetails: FormGroup

  constructor(private formBuilder: FormBuilder, private userService: RegistrationService) { }

  ngOnInit() {
    // this.userDetails = this.formBuilder.group(
    //   {
    //     name: new FormControl("", Validators.compose([
    //       Validators.required,
    //       Validators.pattern("^[a-zA-Z]([._-]?[a-zA-Z0-9]+)*$"),
    //     ])),
    //     email: new FormControl("", Validators.email),
    //     password: new FormControl("", Validators.minLength(6))
    //   }
    // )

    // testing purpose

    this.userDetails = new FormGroup({
      name: new FormControl('', Validators.compose([
        Validators.required,
        Validators.maxLength(20),
        Validators.pattern("^[a-zA-Z]([._-]?[a-zA-Z0-9]+)*$")
      ])),
      password: new FormControl('', Validators.minLength(2)),
      passwordConfirm: new FormControl('', Validators.minLength(2)),
      email: new FormControl("", Validators.email)
    }, passwordMatchValidator);


    function passwordMatchValidator(g: FormGroup) {
      return g.get('password').value === g.get('passwordConfirm').value
        ? null : { 'mismatch': true };
    }
//    working code 
    this.stepDetails = this.formBuilder.group(
      {
        city: new FormControl(""),
        dob: new FormControl(""),
        gender: new FormControl(""),
        phone_number: new FormControl("", Validators.compose([
          Validators.minLength(10),
          Validators.maxLength(10),
          // Validators.pattern("^(\+91(-)?|91(-)?|0(-)?)?(9)[0-9]{9}$")
        ]))
      }
    )

  }
  locations: Location[] = [
    { value: "Bangalore" },
    { value: "Delhi" },
    { value: "Hyderabad" },
    { value: "Mumbai" },
    { value: "Chennai" },
    { value: "Guwahati" }
  ]

  saveDetails() {
    var m = {
      'email': this.completeDetails[0].email,
      'name': this.completeDetails[0].name,
      'password': this.completeDetails[0].password,
      'city': this.completeDetails[1].city,
      'phoneNumber': this.completeDetails[1].phone_number,
      'gender': this.completeDetails[1].gender,
      'genre': this.gen
    }
    this.userService.saveUserDetails(m).subscribe(com => {
      console.log("saved");
    })
  }

  onSubmitUserDetails(value) {
    console.log(value)
    this.completeDetails.push(value)
  }
  onSubmitStepDetails(value) {

    console.log(value)
    this.completeDetails.push(value)
    console.log(this.completeDetails[1])

  }
  onSubmitStep2Details(value) {
    console.log(value)
    this.gen.push(value)

  }
  validation_messages = {
    'name': [
      { type: 'required', message: 'Username is required' },
      { type: 'pattern', message: 'Username should start with a letter and no spaces' }
    ]
  }
}

