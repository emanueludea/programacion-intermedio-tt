import { Component } from '@angular/core';
import { NgbAccordionModule, NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-materias',
  imports: [NgbAccordionModule, NgbAlertModule],
  templateUrl: './materias.html'
})
export class Materias {
  remove = true;
}
