import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/Services/auth.service';
import { User } from 'src/app/user';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';


@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  id!:number;
  user:User = new User();
  constructor(private authservice: AuthService,
    private route: ActivatedRoute,
    private router: Router) { }
  

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.authservice.getUsersById(this.id).subscribe(data=>{
      this.user=data;
    },error => console.log(error));
  }
  updateUser(){
    this.authservice.edituser(this.id,this.user).subscribe(data=>{console.log(data);
    this.user=new User();
  this.goToUserList();},error=>console.log(error))
  }
  onSubmit(){
    this.authservice.edituser(this.id, this.user).subscribe( data =>{
      this.goToUserList();
    }
    , error => console.log(error));
  }
  

  goToUserList(){
    this.router.navigate(['admin/displayUser']);
  }

}
