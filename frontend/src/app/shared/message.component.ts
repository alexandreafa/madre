import { FormControl } from '@angular/forms';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-message',
  template: `
    <div *ngIf="temErro()" class="p-message p-messages-error">
      {{ text }}
    </div>
  `,
  styles: [`
    .p-messages-error {
      margin: 0;
      margin-top: 4px;
    }
  `]
})
export class MessageComponent {

  @Input() error: string;
  @Input() control: FormControl;
  @Input() text: string;

  temErro(): boolean {
    return this.control.hasError(this.error) && this.control.dirty;
  }

}