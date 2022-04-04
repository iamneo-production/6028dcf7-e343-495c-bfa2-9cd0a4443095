import { Component, OnInit,ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { MenuDialogComponent } from '../menu-dialog/menu-dialog.component';
import { AuthService } from 'src/app/Services/auth.service';
import { Router } from '@angular/router';
import { DeleteConfirmationService } from 'src/app/Services/delete-confirmation.service';
@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  displayedColumns: string[]=['itemImageUrl','itemName','itemCategory','itemPrice','action'];
  dataSource!: MatTableDataSource<any>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(private dialog:MatDialog,private api:AuthService,private route:Router,private dialogservice:DeleteConfirmationService) { }
  conformation=false;
  ngOnInit(): void {
    this.getAllItems();
  }
  openDialog(){
    this.dialog.open(MenuDialogComponent, {
      width:'30%'
    }).afterClosed().subscribe(val=>{
      if(val==='Save'){
      this.getAllItems();
      }
    });
  }
  logout(){
    this.route.navigate(['admin/login'])
  }
  openDisplayUser(){
    this.route.navigate(["admin/displayUser"])
  }
  nextPage(){
    this.route.navigate(['admin/addon']);
  }
  backPage(){
    this.route.navigate(['admin/theme'])
  }
  openAddtheme(){
    this.route.navigate(["admin/theme"])
  }
  openAddmenu(){
    this.route.navigate(["admin/menu"])
  }
  openAddon(){
    this.route.navigate(["admin/addon"])
  }
    getAllItems(){
      this.api.getItem()
        .subscribe({
          next:(res)=>{
            this.dataSource=new MatTableDataSource(res);
            this.dataSource.paginator=this.paginator;
            this.dataSource.sort=this.sort;
          },
          error:(err)=>{
            alert("Error while fetching the datas")
          }
        })
      }
      onDelete(id:number){
        this.dialogservice.openConfirmDialog('Are you sure to delete this record ?')
        .afterClosed().subscribe(res =>{
          if(res){
            this.deleteItem(id);
          }
        });
      }

      
      deleteItem(id:number){
        this.api.deleteItem(id)
        .subscribe({
          next:(res)=>{
            alert("Item deleted Successfully")
            this.getAllItems();
          },
          error:()=>{
            alert("Error while deleting")
          }
        })
        }
        applyFilter(event: Event) {
          const filterValue = (event.target as HTMLInputElement).value;
          this.dataSource.filter = filterValue.trim().toLowerCase();
      
          if (this.dataSource.paginator) {
            this.dataSource.paginator.firstPage();
          }
        }
        editItem(row:any){
          this.dialog.open(MenuDialogComponent,{
            width:'30%',
            data:row
          }).afterClosed().subscribe(val=>{
            if(val==='update'){
            this.getAllItems();
            }
          })
        }

}
