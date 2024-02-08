import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {

  public dialogMsg: string = 'Deseja continuar com essa ação?';
  public leftButtonLabel: string = 'Cancelar';
  public rightButtonLabel: string = 'Ok';

  constructor(public dialogRef: MatDialogRef<DialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
  }

  public clickLeftButton() {
    this.dialogRef.close(false);
  }

  public clickRighButton() {
    this.dialogRef.close(true);
  }

}
