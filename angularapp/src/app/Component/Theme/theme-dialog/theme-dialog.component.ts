import { Component, OnInit,Inject } from '@angular/core';
import { FormGroup,FormBuilder,Validators } from '@angular/forms';
import{MatDialogRef,MAT_DIALOG_DATA} from '@angular/material/dialog';
import { AuthService } from 'src/app/Services/auth.service';

@Component({
  selector: 'app-theme-dialog',
  templateUrl: './theme-dialog.component.html',
  styleUrls: ['./theme-dialog.component.css']
})
export class ThemeDialogComponent implements OnInit {
  themeForm !:FormGroup;
  actionbtn:string= "Save"
  constructor(private formBuilder : FormBuilder , 
    private api:AuthService ,
    @Inject(MAT_DIALOG_DATA) public editData:any,
    private dialogRef:MatDialogRef<ThemeDialogComponent>) { }

  ngOnInit(): void {
    this.themeForm=this.formBuilder.group({
      themeName:['',Validators.required],
      imageUrl:['',Validators.required],
      photographerDetails:['',Validators.required],
      videographerDetails:['',Validators.required],
      returnGift:['',Validators.required],
      themeCost:['',[Validators.required,Validators.pattern]],
      themeDescription:['',Validators.required],
    })
    
    if(this.editData){
      this.actionbtn="update";
      this.themeForm.controls['themeName'].setValue(this.editData.themeName);
      this.themeForm.controls['imageUrl'].setValue(this.editData.imageUrl);
      this.themeForm.controls['photographerDetails'].setValue(this.editData.photographerDetails);
      this.themeForm.controls['videographerDetails'].setValue(this.editData.videographerDetails);
      this.themeForm.controls['returnGift'].setValue(this.editData.returnGift);
      this.themeForm.controls['themeCost'].setValue(this.editData.themeCost);
      this.themeForm.controls['themeDescription'].setValue(this.editData.themeDescription);
    }
  }
  addItem(){
    if(!this.editData)
    {
      if(this.themeForm.valid){
     this.api.postTheme(this.themeForm.value)
     .subscribe({
       next:(res)=>{
         alert("Item added successfully")
         this.themeForm.reset();
         this.dialogRef.close('Save');
         },
       error:()=>{
         alert("Error while adding the product")
       }
     })
   }
   }
    else{
      if(this.themeForm.valid){
     this.updateItem()
      }
   }
   }
   updateItem(): any{
      this.api.putTheme(this.themeForm.value,this.editData.id)
      .subscribe({
        next:(res)=>{
          alert("Item updated successfully");
          this.themeForm.reset();
          this.dialogRef.close('update');
        },
        error:()=>{
         alert("Error while updating the product");
       }
      })
   }
   public errorHandling = (control: string, error: string) => {
    return this.themeForm.controls[control].hasError(error);
  }

}
