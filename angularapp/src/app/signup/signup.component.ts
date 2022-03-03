import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { ConfirmValidation } from '../confirm-validation';
import { User } from '../user';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  user=new User();
   msg=''
  registerForm: FormGroup =new FormGroup({
    email:new FormControl(''),
    password:new FormControl('')
    })
  constructor(private formBuilder: FormBuilder, private authService:AuthService) { }
  submitted = false;
  ngOnInit(): void {this.registerForm = this.formBuilder.group({
    email: ['', [Validators.required, Validators.email]],
    username : ['',Validators.required,Validators.pattern],
    mobilenumber:['',[Validators.required,Validators.minLength(10),Validators.maxLength(10)],Validators.pattern],
    password: [
      '',
      [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(40)
      ]
    ],
   confirmPassword :['',Validators.required]
  },
  {validators:[ConfirmValidation.match('password', 'confirmPassword')]}
  )
  }
  get f(): { [key: string]: AbstractControl } {
    return this.registerForm.controls;
  }
  signupUser(): void {
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
  }
  else{
  this.authService.RegisterUserFromRemote(this.user).subscribe(
    _data=>{console.log("Response received");
           this.msg="Registration successful"},
    _error=>{(console.log("Error Occured"))}
  )};
    
}

}
