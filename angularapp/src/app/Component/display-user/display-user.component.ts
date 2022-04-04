import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/Services/auth.service';
import { User } from 'src/app/user';
import { Router } from '@angular/router';
import { DeleteConfirmationComponent } from '../delete-confirmation/delete-confirmation.component';
import { DeleteConfirmationService } from 'src/app/Services/delete-confirmation.service';
@Component({
  selector: 'app-display-user',
  templateUrl: './display-user.component.html',
  styleUrls: ['./display-user.component.css']
})
export class DisplayUserComponent implements OnInit {
  user:User[] | undefined;
  constructor(private authservice:AuthService,private router:Router,private dialogservice:DeleteConfirmationService) { }

  ngOnInit(): void {
    this.getuser();
  }
  private getuser(){
    this.authservice.getUserList().subscribe(data =>{
      this.user=data;
      console.log(data)
    })

  }

  edituser(id:number)
  {
    
    this.router.navigate(['admin/edituser',id]);
  }
  onDelete(id:number){
    this.dialogservice.openConfirmDialog('Are you sure to delete this record ?')
    .afterClosed().subscribe(res =>{
      if(res){
        this.deleteuser(id);
      }
    });
  }

  deleteuser(id : number)
  {
    this.authservice.deleteuser(id).subscribe(data =>{
      console.log(data);
      this.getuser();

    })
  }
 
  logout(){
    this.router.navigate(['admin/login'])
  }
  
  openDisplayUser(){
    this.router.navigate(["admin/displayuser"])
  }
  openAddtheme(){
    this.router.navigate(["admin/theme"])
  }
  openAddmenu(){
    this.router.navigate(["admin/menu"])
  }
  openAddon(){
    this.router.navigate(["admin/addon"])
  }


}
