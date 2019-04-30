package com.athanasiou.spring.angular.restfulSpringBoot.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class CarJpaResource {
	
	@Autowired
	private CarHardcodedService carService;

	@Autowired
	private CarJpaRepository carJpaRepository;

	
	@GetMapping("/jpa/users/{username}/cars")
	public List<Car> getAllCars(@PathVariable String username){
		return carJpaRepository.findByUsername(username);
		//return todoService.findAll();
	}

	@GetMapping("/jpa/users/{username}/cars/{id}")
	public Car getCar(@PathVariable String username, @PathVariable long id){
		return carJpaRepository.findById(id).get();
		//return todoService.findById(id);
	}

	//DELETE /users/{username}/cars/{id}
	@DeleteMapping("/jpa/users/{username}/cars/{id}")
	public ResponseEntity<Void> deleteCar(
            @PathVariable String username, @PathVariable long id){
		

		carJpaRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
		//return ResponseEntity.notFound().build();
	}
	

	//Edit/Update a car
	//PUT /users/{user_name}/cars/{todo_id}
	@PutMapping("/jpa/users/{username}/cars/{id}")
	public ResponseEntity<Car> updateCar(
            @PathVariable String username,
            @PathVariable long id, @RequestBody Car car){
		
		Car carUpdated = carJpaRepository.save(car);
		
		return new ResponseEntity<Car>(car, HttpStatus.OK);
	}
	
	@PostMapping("/jpa/users/{username}/cars")
	public ResponseEntity<Void> createCar(
            @PathVariable String username, @RequestBody Car car){

		car.setUsername(username);
		Car createdCar = carJpaRepository.save(car);

		//Location
		//Get current resource url
		///{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdCar.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
		
}
