import { Component, OnInit } from '@angular/core';
import { User } from '../../user';
import { Router } from '@angular/router';
import { AbstractControl, FormBuilder,  FormControl,  FormGroup,  Validators } from '@angular/forms';
import { AuthService } from '../../Services/auth.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user=new User();
  error1:any
   messageFromService!: string;
   errorFromService!: string;
  loginForm: FormGroup =new FormGroup({
    email:new FormControl(''),
    password:new FormControl('')
    })
    title = 'validation';

  constructor(private formBuilder: FormBuilder,private authService:AuthService,private route:Router) { }
  submitted = false;
  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(40)
        ]
      ]
    })
  }
  get f(): { [key: string]: AbstractControl } {
    return this.loginForm.controls;
  }
  LoginUser(): void {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }
    else{
    this.authService.LoginUserFromRemote(this.user).subscribe({
      
      next:(response) => {
        this.handleSuccessfulResponse.bind(response);
        alert(response.message);
        this.goToHomepage();
      },
     error :(error1)=> {
      this.handleErrorResponse(error1.message);
      alert(error1.message); 
     
    }}
      
    )};
  }
    goToHomepage(){
     this.route.navigate(['admin/homepage'])
    }
    handleSuccessfulResponse(response: any){
      this.messageFromService=response
      console.log(response)
    }
    handleErrorResponse(error1:any){
      this.errorFromService=error1
      console.log(error1)
    }

}
