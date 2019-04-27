package com.athanasiou.spring.angular.restfulSpringBoot.car;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class CarHardcodedService {
	
	private static List<Car> cars = new ArrayList<>();
	private static long idCounter = 0;
	
	static {

		cars.add(new Car(++idCounter, "costas Spring", "Ford 1","2005", new Date(), false ));
		cars.add(new Car(++idCounter, "costas Spring","Ford 2","2006", new Date(), false ));
		cars.add(new Car(++idCounter, "costas Spring","Ford 3","2007", new Date(), false ));
		cars.add(new Car(++idCounter, "costas Spring","Ford 4","2008", new Date(), false ));
		cars.add(new Car(++idCounter, "costas Spring","Ford 5","2009", new Date(), false ));
		cars.add(new Car(++idCounter, "costas Spring","Ford 6","2009", new Date(), false ));
	}
	
	public List<Car> findAll() {
		return cars;
	}

	public Car save(Car car) {
		if(car.getId()==-1 || car.getId()==0) {
			car.setId(++idCounter);
			cars.add(car);
		} else {
			deleteById(car.getId());
			cars.add(car);
		}
		return car;
	}
	
	public Car deleteById(long id) {
		Car car = findById(id);
		
		if(car==null) return null;
		
		if(cars.remove(car)) {
			return car;
		}
		
		return null;
	}

	public Car findById(long id) {
		for(Car car:cars) {
			if(car.getId() == id) {
				return car;
			}
		}
		
		return null;
	}
	
}
