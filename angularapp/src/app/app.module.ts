import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import { MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignupComponent } from './Component/signup/signup.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './Component/login/login.component';
import { HomepageComponent } from './Component/homepage/homepage.component';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import{MatInputModule} from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';
import{MatSliderModule} from "@angular/material/slider";
import {MatToolbarModule} from '@angular/material/toolbar';
import { AddonComponent } from './Component/Addon/addon/addon.component';
import { AddonDialogComponent } from './Component/Addon/addon-dialog/addon-dialog.component';
import { DeleteConfirmationComponent } from './Component/delete-confirmation/delete-confirmation.component';
import {MenuComponent} from './Component/Menu/menu/menu.component';
import { MenuDialogComponent } from './Component/Menu/menu-dialog/menu-dialog.component';
import { ThemeComponent } from './Component/Theme/theme/theme.component';
import { ThemeDialogComponent } from './Component/Theme/theme-dialog/theme-dialog.component';
import { DisplayUserComponent } from './Component/display-user/display-user.component';
import { EditUserComponent } from './Component/edit-user/edit-user.component';


@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    LoginComponent,
    HomepageComponent,
    AddonComponent,
    AddonDialogComponent,
    DeleteConfirmationComponent,
    MenuComponent,
    MenuDialogComponent,
    ThemeComponent,
    ThemeDialogComponent,
    DisplayUserComponent,
    EditUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    FormsModule,
    MatIconModule,
    HttpClientModule,
    CommonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    MatCardModule,
    MatGridListModule,MatSliderModule,MatToolbarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
