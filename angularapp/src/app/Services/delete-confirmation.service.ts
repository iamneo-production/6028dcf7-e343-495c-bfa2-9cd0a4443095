import { Injectable } from '@angular/core';
import { DeleteConfirmationComponent } from '../Component/delete-confirmation/delete-confirmation.component';
import { MatDialog } from '@angular/material/dialog';
@Injectable({
  providedIn: 'root'
})
export class DeleteConfirmationService {

  constructor(private dialog:MatDialog) { }
  openConfirmDialog(msg:String){
    return this.dialog.open(DeleteConfirmationComponent,{
       width: '390px',
       panelClass: 'confirm-dialog-container',
       disableClose: true,
       position: { top: "10px" },
       data :{
         message : msg
       }
     });
   }
}
