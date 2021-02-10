package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.RestaurantModel;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.service.RestaurantService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/zonions")
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	//To add restaurant object
	@PostMapping("/restaurant")
	public ResponseEntity<RestaurantModel> save(@RequestBody RestaurantModel rest){
		return restaurantService.createRestaurant(rest);
	}
	
	
	//To add Image
	@PutMapping("/restaurantImage/{id}")
	public String imageUpload(@RequestParam MultipartFile file, @PathVariable int id) {
		return restaurantService.uploadImage(file,id);
	}
	
	//To get All restaurant
	@GetMapping("/restaurant")
	public List<RestaurantModel> getRestaurant(){
		return restaurantService.getAllRestaurant();
	}
	
	//To get restaurants By id
	@GetMapping("/restaurant/{id}")
	public ResponseEntity<RestaurantModel> getById(@PathVariable int id){
		return restaurantService.getRestaurantById(id);
	}
	
	//To get Restaurant by status
	@GetMapping("/restaurants/{status}")
	public RestaurantModel getByStatus(@PathVariable String status) {
		return restaurantService.getRestaurantByStatus(status);
	}
	
	//To delete restaurant By Id
	@DeleteMapping("/restaurant/{id}")
	public ResponseEntity<HttpStatus> deleteByRestaurantId(@PathVariable int id){
		return restaurantService.deleteById(id);
	}
	
	//To update Restaurant 
	@PutMapping("/restaurant/{id}")
	public ResponseEntity<RestaurantModel> updateRestaurant(@PathVariable int id, @RequestBody RestaurantModel rm){
		return restaurantService.updateRestaurant(id, rm);
	}
	
	//To get Image By name and restaurant Id
	@GetMapping("/file/{name}/{id}")
	//@GetMapping("/file/{name}")
	  public ResponseEntity<byte[]> getFileByName(@PathVariable String name,@PathVariable int id) {
		  System.out.println("name="+name+"Id="+id);
		    Optional<RestaurantModel> fileOptional = restaurantRepository.findByNameAndId(name,id);
		    
		    if(fileOptional.isPresent()) {
		      RestaurantModel file = fileOptional.get();
		      return ResponseEntity.ok()
		          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
		          .body(file.getPic());  
		    }
		    
		    return ResponseEntity.status(404).body(null);
		  }
	                 
      //To change restaurant status active/deactive	  
	  @PutMapping("/changestatus/{id}")
		public RestaurantModel statusChange(@PathVariable int id, @RequestBody RestaurantModel rm){
		  System.out.println("In change status of put....");
			return restaurantService.changeStatus(id, rm);
		}

}
