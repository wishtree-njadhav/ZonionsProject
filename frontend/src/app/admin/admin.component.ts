import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent implements OnInit {
  islogged: String="false";
  flag: boolean=false;
  constructor() {}

  checkLoggedIn() {
    //this.islogged = sessionStorage.getItem('login');
    console.log(this.islogged);
    if (this.islogged === 'true') {
      this.flag = true;
    } else {
      alert('Please Login first');
    }
  }
  ngOnInit(): void {}
}
