import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Injectable({
  providedIn: 'root'
})
export class TranslateConfigServiceService {

  constructor(private translateService:TranslateService) {
    this.translateService.use('en');
   }
  changeLanguage(lang:string){
    this.translateService.use(lang)

  }
  get(placeholder:string):any{
    this.translateService.get(placeholder);
  }
}
