import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './Component/login/login.component';
import { SignupComponent } from './Component/signup/signup.component';
import { HomepageComponent } from './Component/homepage/homepage.component';

const routes: Routes = [ {path:'user/login',component:LoginComponent},
{path:'admin/login',component:LoginComponent},
{path:'',component:SignupComponent},
{path:'user/signup',redirectTo:'',component:SignupComponent,pathMatch:'full'},
{path:'admin/signup',redirectTo:'',component:SignupComponent,pathMatch:'full'},
{path:'admin/homepage',component:HomepageComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
