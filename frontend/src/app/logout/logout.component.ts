import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  islooged:boolean=false;
  logged:string="";
  constructor(private route:Router) { }

  ngOnInit(): void {
    this.getlog();
  }
  getlog(){
    //this.logged=sessionStorage.getItem("login");
    console.log(this.logged)
    if(this.logged){
      sessionStorage.removeItem("login");
    }
    this.route.navigate(['/home'])
    
  }
}
