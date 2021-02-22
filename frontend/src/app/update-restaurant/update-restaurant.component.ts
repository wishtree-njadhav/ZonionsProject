import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Restaurant } from 'src/app/restaurant';
import { from } from 'rxjs';
import {ZonionsServService} from '../zonions-serv.service';

@Component({
  selector: 'app-update-restaurant',
  templateUrl: './update-restaurant.component.html',
  styleUrls: ['./update-restaurant.component.css'],
  providers:[ZonionsServService]
})
export class UpdateRestaurantComponent implements OnInit {
  //restaurantForm:FormGroup;
  data:any;
  restaurantData:any;
  restaurant:Restaurant;
  id:number=0;
  imagename:string="";
  url:string="";
  finalurl:string="";
  file:any;
  t:number=0;
  q:number=0;

  open_time={
    hour:10,
    minute: 30
  }
  close_time={
    hour:21,
    minute: 30
  }
  constructor(private zonionsserve:ZonionsServService,private route: ActivatedRoute,private router: Router) { 
      this.restaurant=new Restaurant();
  }
  ngOnInit(): void {
    this.restaurant=new Restaurant();
    this.id=this.route.snapshot.params['id'];
    console.log(this.id)
    this.zonionsserve.getRestoId(this.id).subscribe(data=>{
      console.log(data)
      this.restaurant=data;
       this.t=parseInt(this.restaurant.open_time.slice(0,2))
       this.q=parseInt(this.restaurant.open_time.slice(3,5))
      this.open_time={ hour:this.t,minute:this.q}
      var ch:number=parseInt(this.restaurant.close_time.slice(0,2))
      var cm:number=parseInt(this.restaurant.close_time.slice(3,5))
      this.close_time={ hour:ch,minute:cm}
      console.log("image name",this.restaurant.name);
      this.finalurl="http://localhost:8080/zonions/file";
      this.url=`${this.finalurl}/${this.restaurant.name}/${this.id}`;
      
        console.log(this.url);
    })
  }
  updateResto(){
    
    this.zonionsserve.update(this.id,this.restaurant).subscribe(data=>{
      console.log(data);
     // alert("Updated Successfully");
      this.restaurant=new Restaurant();
      this.gotoRestaurantList();
    },error=>console.log(error)) 
  }
  onSubmit(){
    this.updateResto();
  }
  onChange(file:any){
      this.file=file;

  }
  updateImage(){
    console.log("I am in upload"+this.file);
    this.zonionsserve.pushFileToStorage(this.file,this.id).subscribe((resp:any)=>{
      console.log(resp);
    
    },(error:any)=>console.log(error))
  }
  gotoRestaurantList(){
    this.router.navigate(['restaurantList'])
  } 
  backEvent(){
    this.router.navigate(['admin']);

  }
}
