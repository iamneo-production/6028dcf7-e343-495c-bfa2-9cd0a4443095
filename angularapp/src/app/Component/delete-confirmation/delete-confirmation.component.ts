import { Component, OnInit,Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-delete-confirmation',
  templateUrl: './delete-confirmation.component.html',
  styleUrls: ['./delete-confirmation.component.css']
})
export class DeleteConfirmationComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data:any,
  public dialogRef: MatDialogRef<DeleteConfirmationComponent>) { }

  ngOnInit(): void {
  }
  closeDialog() {
    this.dialogRef.close(false);
  }
}
