import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { from } from 'rxjs';
import { map } from 'rxjs/operators';
import { Restaurant } from '../restaurant';
import { ZonionsServService } from '../zonions-serv.service';

@Component({
  selector: 'app-restaurant-list',
  templateUrl: './restaurant-list.component.html',
  styleUrls: ['./restaurant-list.component.css'],
  providers: [ZonionsServService],
})
export class RestaurantListComponent implements OnInit {
  restaurantList=new Array<Restaurant> () ;
  id: number=0;
  restaurantObject=new Array<Restaurant>();
  constructor(
    private zonionsserve: ZonionsServService,
    private router: Router,
    private activatedRoute: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.reload();
  }

  reload() {
    this.zonionsserve.getRestaurantList().subscribe(
      (data) => {
        console.log(data);
        this.restaurantList = data;
        console.log(this.restaurantList);
      },
      (error) => console.log(error)
    );
  }

  deleteRestaurant(id: number) {
    this.zonionsserve.deleteResto(id).subscribe(
      (data) => {
        console.log(data);
        this.reload();
        alert('Deleted Successfully');
      },
      (error) => console.log(error)
    );
  }
  edit(id: number) {
    this.router.navigate(['update', id]);
  }
  back() {
    this.router.navigate(['admin']);
  }

  changeStatus(id: number) {
    console.log('id in change status=', id);
    this.zonionsserve.getRestoId(id).subscribe((resp) => {
      console.log(resp);
      this.restaurantObject = resp;
      console.log(this.restaurantObject);
      this.zonionsserve.changeStatus(id, this.restaurantObject).subscribe(
        (data) => {
          console.log(data);
          this.reload();
        },
        (error) => console.log(error)
      );
    });
  }
}
