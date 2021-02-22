import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { InternalServeErrorComponent } from './ErrorPages/internal-serve-error/internal-serve-error.component';
import { PageNotFoundErrorComponent } from './ErrorPages/page-not-found-error/page-not-found-error.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { RegistrationComponent } from './registration/registration.component';
import { RestaurantDetailComponent } from './restaurant-detail/restaurant-detail.component';
import { RestaurantListComponent } from './restaurant-list/restaurant-list.component';
import { RestaurantComponent } from './restaurant/restaurant.component';
import { UpdateRestaurantComponent } from './update-restaurant/update-restaurant.component';

const routes: Routes = [
  {path:"home",component:HomeComponent },
  {path:"login",component:LoginComponent},
  {path:"restaurant" , component:RestaurantComponent},
  {path:"restaurantDetail/:restaurantName", component:RestaurantDetailComponent},
  {path:"logout",component:LogoutComponent},
  {path:"update/:id",component:UpdateRestaurantComponent},
  {path:"admin",component:AdminComponent},
  {path:"restaurantList",component:RestaurantListComponent},
  {path:"registration",component:RegistrationComponent},
  {path:"500", component:InternalServeErrorComponent},
  {path:"404",component:PageNotFoundErrorComponent},
  {path:"",redirectTo:"home",pathMatch:'full'},
  {path:"**",component:PageNotFoundErrorComponent},
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

}
