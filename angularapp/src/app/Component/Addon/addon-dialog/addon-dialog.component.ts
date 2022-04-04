import { Component, OnInit,Inject } from '@angular/core';
import { FormGroup,FormBuilder,Validators } from '@angular/forms';

import{MatDialogRef,MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Data } from '@angular/router';
import { asLiteral } from '@angular/compiler/src/render3/view/util';
import { AuthService } from 'src/app/Services/auth.service';

@Component({
  selector: 'app-addon-dialog',
  templateUrl: './addon-dialog.component.html',
  styleUrls: ['./addon-dialog.component.css']
})
export class AddonDialogComponent implements OnInit {
 
    addonForm !:FormGroup;
    actionbtn:string= "Save"
    constructor(private formBuilder : FormBuilder , 
      private api:AuthService ,
      @Inject(MAT_DIALOG_DATA) public editData:any,
      private dialogRef:MatDialogRef<AddonDialogComponent> ){ }
   
  

  ngOnInit(): void {
    this.addonForm=this.formBuilder.group({
      addonsName:['',Validators.required],
      addonsPrice:['',[Validators.required,Validators.pattern]],
      imageUrl:['',Validators.required],

    })
    
    
    if(this.editData){
      this.actionbtn="update";
      this.addonForm.controls['addonsName'].setValue(this.editData.addonsName);
      this.addonForm.controls['addonsPrice'].setValue(this.editData.addonsPrice);
      this.addonForm.controls['imageUrl'].setValue(this.editData.imageUrl);
     
    }
  }
    addItem(){
      if(!this.editData)
      {
        if(this.addonForm.valid){
       this.api.postAddon(this.addonForm.value)
       .subscribe({
         next:(res)=>{
           alert("Addon added successfully")
           this.addonForm.reset();
           this.dialogRef.close('Save');
           },
         error:()=>{
           alert("Error while adding the product")
         }
       })
     }
     }
      else{
        if(this.addonForm.valid){
       this.updateItem()}
       else{
        alert("Error while updating the product")
       }
     }
     }
     updateItem(): any{
        this.api.putAddon(this.addonForm.value,this.editData.id)
        .subscribe({
          next:(res)=>{
            alert("Item updated successfully");
            this.addonForm.reset();
            this.dialogRef.close('update');
          },
          error:()=>{
           alert("Error while updating the product");
         }
        })
     }
     public errorHandling = (control: string, error: string) => {
      return this.addonForm.controls[control].hasError(error);
    }
  }


