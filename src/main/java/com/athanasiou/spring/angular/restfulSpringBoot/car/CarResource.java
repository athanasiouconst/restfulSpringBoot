package com.athanasiou.spring.angular.restfulSpringBoot.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class CarResource {
	
	@Autowired
	private CarHardcodedService carService;
	
	@GetMapping("/users/{username}/cars")
	public List<Car> getAllCars(@PathVariable String username){
		return carService.findAll();
	}

	@GetMapping("/users/{username}/cars/{id}")
	public Car getCar(@PathVariable String username, @PathVariable long id){
		return carService.findById(id);
	}

	//DELETE /users/{username}/cars/{id}
	@DeleteMapping("/users/{username}/cars/{id}")
	public ResponseEntity<Void> deleteCar(
            @PathVariable String username, @PathVariable long id){

		Car car = carService.deleteById(id);
		
		if(car!=null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	

	//Edit/Update a car
	//PUT /users/{user_name}/cars/{todo_id}
	@PutMapping("/users/{username}/cars/{id}")
	public ResponseEntity<Car> updateCar(
            @PathVariable String username,
            @PathVariable long id, @RequestBody Car car){

		Car carUpdated = carService.save(car);
		
		return new ResponseEntity<Car>(car, HttpStatus.OK);
	}
	
	@PostMapping("/users/{username}/cars")
	public ResponseEntity<Void> updateCar(
            @PathVariable String username, @RequestBody Car car){

		Car createdCar = carService.save(car);

		//Location
		//Get current resource url
		///{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdCar.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

		
}
