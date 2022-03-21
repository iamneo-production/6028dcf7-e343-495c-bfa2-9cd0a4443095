import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../Services/auth.service';
import { ConfirmValidation } from '../../confirm-validation';
import { User } from '../../user';
import { Router } from '@angular/router';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  user=new User();
  error1:any
  messageFromService!: string;
  errorFromService!: string;
  submitted=true;
   registerForm: FormGroup =new FormGroup(
     {
     email:new FormControl(''),
     password:new FormControl(''),
     username:new FormControl(''),
     mobileNumber:new FormControl(''),
     confirmPassword:new FormControl('')
   })
   title = 'validation';
   constructor(private formBuilder: FormBuilder,private authService:AuthService,private route:Router) {}
   
   ngOnInit(): void {
     
     this.registerForm = this.formBuilder.group({
       email: ['', [Validators.required, Validators.email]],
       password: [
         '',
         [
           Validators.required,
           Validators.minLength(8),
           Validators.maxLength(40)
         ]
       ],
       username : ['',[Validators.required,Validators.pattern,Validators.minLength(5)]],
       mobileNumber:['',[Validators.required,Validators.minLength(10),Validators.maxLength(10),Validators.pattern]],
       confirmPassword :['',[Validators.required]]
     },
     {validators:[ConfirmValidation.match('password', 'confirmPassword')]}
     )
   }
   
   get f(): { [key: string]: AbstractControl } {
     return this.registerForm.controls;
   }
   
   
   SignupUser(): void {
       this.submitted=true;
      // stop here if form is invalid
       if (this.registerForm.invalid) {
           return;
       }
       else{
        this.authService.RegisterUserFromRemote(this.user).subscribe({

        next:(response)=> { 
          this.handleSuccessfulResponse.bind(response);
         alert(response.message);},
        error:(error1) => {this.handleErrorResponse(error1.message);
       alert(error1.message) }})
        
       }
       }
       goToLogin(){
        this.route.navigate(['/admin/login'])
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
     