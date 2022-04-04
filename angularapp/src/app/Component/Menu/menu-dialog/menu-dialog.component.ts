import { Component, OnInit,Inject } from '@angular/core';
import { FormGroup,FormBuilder,Validators } from '@angular/forms';

import{MatDialogRef,MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Data } from '@angular/router';
import { asLiteral } from '@angular/compiler/src/render3/view/util';
import { AuthService } from 'src/app/Services/auth.service';

@Component({
  selector: 'app-menu-dialog',
  templateUrl: './menu-dialog.component.html',
  styleUrls: ['./menu-dialog.component.css']
})
export class MenuDialogComponent implements OnInit {
  itemForm !:FormGroup;
  actionbtn:string= "Save"
  constructor(private formBuilder : FormBuilder , 
    private api:AuthService ,
    @Inject(MAT_DIALOG_DATA) public editData:any,
    private dialogRef:MatDialogRef<MenuDialogComponent> ) { }

  ngOnInit(): void {
    this.itemForm=this.formBuilder.group({
      itemImageUrl:['',Validators.required],
      itemName:['',Validators.required],
      itemCategory:['',Validators.required],
      itemPrice:['',[Validators.required,Validators.pattern]]

    })
    
    if(this.editData){
      this.actionbtn="update";
      this.itemForm.controls['itemImageUrl'].setValue(this.editData.itemImageUrl);
      this.itemForm.controls['itemName'].setValue(this.editData.itemName);
      this.itemForm.controls['itemCategory'].setValue(this.editData.itemCategory);
      this.itemForm.controls['itemPrice'].setValue(this.editData.itemPrice);
    }
  }
  addItem(){
    if(!this.editData)
    {
      if(this.itemForm.valid){
     this.api.postItem(this.itemForm.value)
     .subscribe({
       next:(res)=>{
         alert("Item added successfully")
         this.itemForm.reset();
         this.dialogRef.close('Save');
         },
       error:()=>{
         alert("Error while adding the product")
       }
     })
   }
   }
    else{
      if(this.itemForm.valid){
     this.updateItem()}
     else{
      alert("Error while updating the item")
     }
   }
   }
   updateItem(): any{
      this.api.putItem(this.itemForm.value,this.editData.id)
      .subscribe({
        next:(res)=>{
          alert("Item updated successfully");
          this.itemForm.reset();
          this.dialogRef.close('update');
        },
        error:()=>{
         alert("Error while updating the product");
       }
      })
   }
   public errorHandling = (control: string, error: string) => {
    return this.itemForm.controls[control].hasError(error);
  }

}
