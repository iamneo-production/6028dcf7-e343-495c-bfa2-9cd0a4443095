import { Component, OnInit, ViewChild } from '@angular/core';
import { AbstractControl, FormBuilder,  FormControl,  FormGroup,  Validators } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AuthService } from '../../../Services/auth.service';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AddonDialogComponent } from '../addon-dialog/addon-dialog.component';
import { DeleteConfirmationService } from 'src/app/Services/delete-confirmation.service';


@Component({
  selector: 'app-addon',
  templateUrl: './addon.component.html',
  styleUrls: ['./addon.component.css']
})
export class AddonComponent implements OnInit {
  displayedColumns: string[]=['addonsName','addonsPrice','imageUrl','action'];
 dataSource!: MatTableDataSource<any>;
 @ViewChild(MatPaginator) paginator!: MatPaginator;
 @ViewChild(MatSort) sort!: MatSort;

  constructor(private dialog:MatDialog,private api:AuthService,private route:Router,private deleteCongirmationService:DeleteConfirmationService) { }
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
  ngOnInit(): void {
    this.getAllItems();
  }
  getAllItems(){
    this.api.getAddon()
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
      openDialog(){
        this.dialog.open(AddonDialogComponent, {
          width:'30%'
        }).afterClosed().subscribe(val=>{
          if(val==='Save'){
          this.getAllItems();
          }
        });
      }
      
      previousPage(){
        this.route.navigate(['admin/menu'])
      }
      onDelete(id:number){
        this.deleteCongirmationService.openConfirmDialog('Are you sure to delete this record ?')
        .afterClosed().subscribe(res =>{
          if(res){
            this.deleteItem(id);
          }
        });
      }
    
          deleteItem(id:number){
            this.api.deleteAddon(id)
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
              this.dialog.open(AddonDialogComponent,{
                width:'30%',
                data:row
              }).afterClosed().subscribe(val=>{
                if(val==='update'){
                this.getAllItems();
                }
              })
            }
    }


