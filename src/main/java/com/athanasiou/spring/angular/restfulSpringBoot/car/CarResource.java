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
public class CarResource {
	
	@Autowired
	private CarHardcodedService carservice;
	
	@GetMapping("/users/{username}/cars")
	public List<Car> getAllcars(@PathVariable String username){
		return carservice.findAll();
	}

	@GetMapping("/users/{username}/cars/{id}")
	public Car getTodo(@PathVariable String username, @PathVariable long id){
		return carservice.findById(id);
	}

	//DELETE /users/{username}/cars/{id}
	@DeleteMapping("/users/{username}/cars/{id}")
	public ResponseEntity<Void> deleteTodo(
            @PathVariable String username, @PathVariable long id){

		Car todo = carservice.deleteById(id);
		
		if(todo!=null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	

	//Edit/Update a Todo
	//PUT /users/{user_name}/cars/{todo_id}
	@PutMapping("/users/{username}/cars/{id}")
	public ResponseEntity<Car> updateTodo(
            @PathVariable String username,
            @PathVariable long id, @RequestBody Car todo){

		Car todoUpdated = carservice.save(todo);
		
		return new ResponseEntity<Car>(todo, HttpStatus.OK);
	}
	
	@PostMapping("/users/{username}/cars")
	public ResponseEntity<Void> updateTodo(
            @PathVariable String username, @RequestBody Car todo){

		Car createdTodo = carservice.save(todo);
		
		//Location
		//Get current resource url
		///{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
		
}
