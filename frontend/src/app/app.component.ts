import { Component } from '@angular/core';
import { TranslateConfigServiceService } from './translate-config-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'zonions';
  //loggedIn:string=sessionStorage.getItem("login");
  constructor(private translateConfigService:TranslateConfigServiceService){

  }
  changeLang(lang:string){
    this.translateConfigService.changeLanguage(lang);
    console.log(lang)
  }
}
