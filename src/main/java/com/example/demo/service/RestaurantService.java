package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.RestaurantModel;
import com.example.demo.repository.RestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RestaurantService {
	@Autowired
	private RestaurantRepository restaurantRepository;
	String status="active";
	LocalTime time=LocalTime.now();
	String updatedTime=time.toString();
	
	//Service To add restaurant object
	public ResponseEntity<RestaurantModel> createRestaurant(@RequestBody RestaurantModel rm){
		try {
			
			RestaurantModel _restaurant=restaurantRepository.save(new RestaurantModel(rm.getRestaurantName(),rm.getAddress(),rm.getPhone_no(),
					rm.getOpen_time(),rm.getClose_time(),status,updatedTime));
			return new ResponseEntity<>(_restaurant,HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//Service To add Image
	public String uploadImage(@RequestParam("file") MultipartFile file,@PathVariable int id) {
		
		Optional<RestaurantModel> restData=restaurantRepository.findById(id);
		try {
		if(restData.isPresent()) {
			RestaurantModel restModel=restData.get();
			restModel.setName(file.getOriginalFilename());
			restModel.setMimetype(file.getContentType());
			restModel.setPic(file.getBytes());
			
			restaurantRepository.save(restModel);
			
		}
		return "File uploaded successfully! -> filename = " + file.getOriginalFilename();
		}
		catch (Exception e) {
			return "FAIL! Maybe You had uploaded the file before or the file's size > 500KB";
		}
		
	}
	
	//Service to get all restaurant
	public List<RestaurantModel> getAllRestaurant(){
		return restaurantRepository.findAll();
	}
	
	//Service to get restaurant by id
	public ResponseEntity<RestaurantModel>  getRestaurantById(@PathVariable int id){
		Optional<RestaurantModel> restaurant=restaurantRepository.findById(id);
		if(restaurant.isPresent()) {
			return new ResponseEntity<RestaurantModel>(restaurant.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Service to get restaurant 
	public RestaurantModel getRestaurantByStatus(@PathVariable String status){
		RestaurantModel data=restaurantRepository.findByStatus(status);
		return data;
	}
	
	public ResponseEntity<HttpStatus> deleteById(@PathVariable int id){
		try {
			restaurantRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public ResponseEntity<RestaurantModel> updateRestaurant(@PathVariable int id,@RequestBody RestaurantModel resmodel){
		Optional<RestaurantModel> restData=restaurantRepository.findById(id);
		if(restData.isPresent()) {
			RestaurantModel rs=restData.get();
			rs.setRestaurantName(resmodel.getRestaurantName());
			rs.setAddress(resmodel.getAddress());
			rs.setPhone_no(resmodel.getPhone_no());
			rs.setOpen_time(resmodel.getOpen_time());
			rs.setClose_time(resmodel.getClose_time());
			rs.setStatus(resmodel.getStatus());
			rs.setUpdatedTime(updatedTime);
			return new ResponseEntity<RestaurantModel>(restaurantRepository.save(rs),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	public RestaurantModel changeStatus(@PathVariable int id,@PathVariable RestaurantModel resm) {
		
		String st="deactive";
		String st1="active";
			
		    Optional<RestaurantModel> restdata=restaurantRepository.findById(id);
		    
		    RestaurantModel rm=restdata.get();
		   
		    if(restdata.isPresent()) {
			
				if(resm.getStatus().equals("active")) {
					rm.setStatus(st);
					
				}
				else {
					rm.setStatus(st1);
				
				}
		    }
		    
		return restaurantRepository.save(rm);
	}
    
}
