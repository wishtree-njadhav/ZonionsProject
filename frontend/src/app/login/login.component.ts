import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  login: FormGroup;
  isSubmitted: boolean=false;
  data: any;
  isloggedIn: boolean=false;
  constructor(private router: Router) {
    this.login = new FormGroup({
      username: new FormControl(''),
      password: new FormControl(''),
    });
  }

  ngOnInit(): void {}
  checkLogin(fvalue:any) {
    this.data = fvalue;
    console.log(this.data.username);
    this.isSubmitted = true;
    if (
      this.data.username === 'admin@gmail.com' &&
      this.data.password === 'admin123'
    ) {
      this.isloggedIn = true;
      sessionStorage.setItem('login', 'true');
      this.router.navigate(['/admin']);
      alert('Login Successfull');
    } else {
      alert('please enter correct username or password');
    }
  }

  signin(){
    this.router.navigate(['/registration']);
  }
}
