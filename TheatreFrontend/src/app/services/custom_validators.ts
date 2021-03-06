import { FormControl, Validators } from '@angular/forms';

// setup simple regex for white listed characters
const validCharacters = /[^\s\w,.:&\/()+%'`@-]/;
const validNumbers = /^[0-9]+/;

// create your class that extends the angular validator class
export class CustomValidators extends Validators {
  
  // create a static method for your validation
  static validateCharacters(control: FormControl) {
     
    // first check if the control has a value
    if (control.value && control.value.length > 0) {
       
      // match the control value against the regular expression
      const matches = control.value.match(validCharacters);
      
      // if there are matches return an object, else return null.
      return matches && matches.length ? { invalid_characters: matches } : null;
    } else {
      return null;
    }
  }



  static validateNumber(control: FormControl) {
     
    // first check if the control has a value
    if (control.value && control.value.length > 0) {
       
      // match the control value against the regular expression
      const matches = control.value.match(validNumbers);
      
      // if there are matches return an object, else return null.
      return matches && matches.length ? { invalid_characters: matches } : null;
    } else {
      return null;
    }
  }
}