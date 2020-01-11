package service;

import model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarService {
    public List<Car> getCars() {
        List<Car> cars = new ArrayList<Car>();
        cars.add(new Car("911", "Porshe", "2-door coupe"));
        cars.add(new Car("Corolla", "Toyota", "Compact car"));
        cars.add(new Car("Ram pickup", "FCA US LLC", "Full-size pickup truck"));
        return cars;
    }
}
