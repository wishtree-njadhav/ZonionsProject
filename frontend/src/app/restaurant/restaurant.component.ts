import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ZonionsServService } from '../zonions-serv.service';

@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.component.html',
  styleUrls: ['./restaurant.component.css'],
  providers: [ZonionsServService],
})
export class RestaurantComponent implements OnInit {
  isSubmitted:boolean=true;
  data: any;
  file: any;
  restaurantId: any;
  restaurantData: any;
  open_time: string="";
  close_time: string="";
  time = { hour: 13, minute: 30 };
  restaurantForm= new FormGroup({
    restaurantName:new FormControl('', [Validators.required, 
                   Validators.pattern('^(?:[A-Za-z]+)(?:[A-Za-z0-9 _]*)$')]),
    address: new FormControl('', [Validators.required]),
    phone_no: new FormControl('',[Validators.required,Validators.pattern('[7-9][0-9]{9}')]),
    open_time: new FormControl('',[Validators.required]),
    close_time:new FormControl('',[Validators.required])
  });

  constructor(
    private zonionsserve: ZonionsServService,
    private router: Router) {
    // this.restaurantForm = new FormGroup({
    //   restaurantName: new FormControl(
    //     '',
    //     Validators.compose([
    //       Validators.required,
    //       Validators.pattern('^(?:[A-Za-z]+)(?:[A-Za-z0-9 _]*)$'),
    //     ])
    //   ),
    //   address: new FormControl('', Validators.compose([Validators.required])),
    //   phone_no: new FormControl(
    //     '',
    //     Validators.compose([
    //       Validators.required,
    //       Validators.pattern('[7-9][0-9]{9}'),
    //     ])
    //   ),
    //   open_time: new FormControl(''),
    //   close_time: new FormControl(''),

    //   // updated_date:new FormControl('')
    // });
  }
  ngOnInit(): void {}
  get f(){
    return this.restaurantForm.controls;
  }
  saveRestaurant(fvalue:any) {
    console.log(this.restaurantForm.value);
    this.data = fvalue;
    console.log('console data object=', this.data);
    console.log('close time on data object=', this.data.close_time);
    this.close_time =
      this.data.close_time.hour + ':' + this.data.close_time.minute;
    this.data.close_time = this.close_time;
    this.open_time =
      this.data.open_time.hour + ':' + this.data.open_time.minute;
    this.data.open_time = this.open_time;
    this.isSubmitted = true;
    this.zonionsserve.createRestaurant(this.restaurantForm.value).subscribe(
      (res) => {
        console.log('after adding resto', res);
        this.restaurantData = res;

        console.log('id from restaurant data', this.restaurantData.id);
      },
      (error) => console.log(error)
    );
  }
  uploadFile() {
    console.log('I am in upload' + this.file);
    this.zonionsserve
      .pushFileToStorage(this.file, this.restaurantData.id)
      .subscribe(
        (resp:any) => {
          console.log(resp);
          this.router.navigate(['restaurantList']);
        },
        (error:any) => console.log(error)
      );
  }
  onFileChangeEvent(file:any) {
    this.file = file;
    console.log('file change event=', this.file);
  }

  // restaurantName_validation_message = {
  //   restaurantName: [
  //     { type: 'required', message: 'Username is required' },
  //     {
  //       type: 'pattern',
  //       message: 'Your restaurant must contain only numbers and letters',
  //     },
  //   ],
  // };
  // address_validation_message = {
  //   address: [{ type: 'required', message: 'Address is required' }],
  // };
  // phone_no_validation_message = {
  //   phone_no: [
  //     { type: 'required', message: 'phone number is required' },
  //     {
  //       type: 'pattern',
  //       message: 'phone should be start with 7/8/9 and should be 10 digit',
  //     },
  //   ],
  // };
  backEvent() {
    this.router.navigate(['admin']);
  }
}
