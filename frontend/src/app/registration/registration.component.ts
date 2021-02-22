import { ClassGetter } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { TranslateConfigServiceService } from '../translate-config-service.service';
import { Registration } from './Registration';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registration:Registration=new Registration();
  submitted=false;
  placeholder:string="";

  constructor(private translateConfigService:TranslateConfigServiceService) { 
    
  }

  ngOnInit(): void {
  }
  changeLanguage(lang:string){
    this.translateConfigService.changeLanguage(lang);
}
  newRegistration(){
    this.submitted=false;
    this.registration=new Registration();
  }
  
  save(){
      console.log(this.registration);
  }
  onSubmit(){
    this.submitted=true;
    alert("submit clicked");
    this.save();
  }

}
