import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(private route:Router) { }

  ngOnInit(): void {
  }
  openAddtheme(){
    this.route.navigate(["admin/theme"])
  }
  openDisplayUser(){
    this.route.navigate(["admin/displayUser"])
  }
  openAddmenu(){
    this.route.navigate(["admin/menu"])
  }
  openAddon(){
    this.route.navigate(["admin/addon"])
  }
  logout(){
    this.route.navigate(['admin/login'])
  }


}
