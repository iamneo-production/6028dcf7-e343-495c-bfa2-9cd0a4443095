import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './Component/login/login.component';
import { SignupComponent } from './Component/signup/signup.component';
import { HomepageComponent } from './Component/homepage/homepage.component';
import { AddonComponent } from './Component/Addon/addon/addon.component';
import { MenuComponent } from './Component/Menu/menu/menu.component';
import { ThemeComponent } from './Component/Theme/theme/theme.component';
import { DisplayUserComponent } from './Component/display-user/display-user.component';
import{EditUserComponent} from './Component/edit-user/edit-user.component';

const routes: Routes = [ {path:'user/login',component:LoginComponent},
{path:'admin/login',component:LoginComponent},
{path:'',component:SignupComponent},
{path:'user/signup',redirectTo:'',component:SignupComponent,pathMatch:'full'},
{path:'admin/signup',redirectTo:'',component:SignupComponent,pathMatch:'full'},
{path:'admin/homepage',component:HomepageComponent},
{path:'admin/addon',component:AddonComponent},
{path:'admin/menu',component:MenuComponent},
{path:'admin/theme',component:ThemeComponent},
{path:'admin/displayUser',component:DisplayUserComponent},
{path:'admin/edituser/:id',component:EditUserComponent}]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
