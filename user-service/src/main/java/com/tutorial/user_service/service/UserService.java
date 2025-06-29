package com.tutorial.user_service.service;

import com.tutorial.user_service.entity.User;
import com.tutorial.user_service.feingclients.BikeFeingClient;
import com.tutorial.user_service.feingclients.CarFeingClient;
import com.tutorial.user_service.model.Bike;
import com.tutorial.user_service.model.Car;
import com.tutorial.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeingClient carFeingClient;

    @Autowired
    BikeFeingClient bikeFeingClient;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        User userNew = userRepository.save(user);
        return userNew;
    }

    public List<Car> getCars(int userId) {
        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/byuser/" + userId, List.class);
        return cars;
    }

    public List<Bike> getBikes(int userId) {
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/byuser/" + userId, List.class);
        return bikes;
    }

    public Car saveCar(int userId, Car car) {
        car.setUserId(userId);
        Car carNew = carFeingClient.save(car);
        return carNew;
    }

    public Bike saveBike(int userId, Bike bike) {
        bike.setUserId(userId);
        Bike bikeNew = bikeFeingClient.save(bike);
        return bikeNew;
    }

    public Map<String, Object> getUserAndVehicles(int userId) {
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            result.put("Mensaje", "no existe el usuario");
            return result;
        }
        result.put("User", user);
        List<Car> cars = carFeingClient.getCars(userId);
        if (cars.isEmpty())
            result.put("Cars", "user no tiene coches");
        else
            result.put("Cars", cars);
        List<Bike> bikes = bikeFeingClient.getBikes(userId);
        if (bikes.isEmpty())
            result.put("Cars", "user no tiene motos");
        else
            result.put("Bikes", bikes);
        return result;
    }
}
