package com.athanasiou.spring.angular.restfulSpringBoot.car;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class CarHardcodedService {
	
	private static List<Car> todos = new ArrayList<>();
	private static long idCounter = 0;
	
	static {
		todos.add(new Car(++idCounter, "in28minutes","Learn to Dance 2", new Date(), false ));
		todos.add(new Car(++idCounter, "in28minutes","Learn about Microservices 2", new Date(), false ));
		todos.add(new Car(++idCounter, "in28minutes","Learn about Angular", new Date(), false ));
	}
	
	public List<Car> findAll() {
		return todos;
	}

	public Car save(Car todo) {
		if(todo.getId()==-1 || todo.getId()==0) {
			todo.setId(++idCounter);
			todos.add(todo);
		} else {
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}
	
	public Car deleteById(long id) {
		Car todo = findById(id);
		
		if(todo==null) return null;
		
		if(todos.remove(todo)) {
			return todo;
		}
		
		return null;
	}

	public Car findById(long id) {
		for(Car todo:todos) {
			if(todo.getId() == id) {
				return todo;
			}
		}
		
		return null;
	}
	
}
