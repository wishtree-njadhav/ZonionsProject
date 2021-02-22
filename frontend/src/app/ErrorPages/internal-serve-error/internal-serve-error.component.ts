import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-internal-serve-error',
  templateUrl: './internal-serve-error.component.html',
  styleUrls: ['./internal-serve-error.component.css']
})
export class InternalServeErrorComponent implements OnInit {
  public errorMessage: string = "500 SERVER ERROR, CONTACT ADMINISTRATOR!!!!";
  constructor() { }

  ngOnInit(): void {
  }

}
