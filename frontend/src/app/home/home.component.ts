import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ErrorHandlerServiceService } from '../error-handler-service.service';
import { Restaurant } from '../restaurant';
import { TranslateConfigServiceService } from '../translate-config-service.service';
import { ZonionsServService } from '../zonions-serv.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [ZonionsServService],
})
export class HomeComponent implements OnInit {
  restaurant = new Array<Restaurant>();
  public errorMessage: string = '';

  constructor(private zonionsService: ZonionsServService, 
    private translateConfigService:TranslateConfigServiceService,
    private errorHandlerService:ErrorHandlerServiceService) 
  {
  
  }

  ngOnInit(): void {
    this.getRestaurants();
  }

  changeLanguage(lang:string){
         this.translateConfigService.changeLanguage(lang);
  }
  getRestaurants() {
    // return this.zonionsService.getRestaurantList().subscribe((data) => {
    //   console.log(data);
    //   console.log(data.length);
    //   this.restaurant = data;
    // });

    return this.zonionsService.getRestaurantList().subscribe((data)=>{
      this.restaurant=data;
    },
    (error)=>{
        this.errorHandlerService.handleError(error);
        this.errorMessage=this.errorHandlerService.errorMessage;
    })
  }
}
