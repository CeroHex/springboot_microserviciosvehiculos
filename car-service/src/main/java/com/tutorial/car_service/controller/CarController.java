package com.tutorial.car_service.controller;

import com.tutorial.car_service.entity.Car;
import com.tutorial.car_service.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAll(){
        List<Car> Cars = carService.getAll();
        if(Cars.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(Cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable("id") int id){
        Car Car = carService.getCarById(id);
        if(Car==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Car);
    }

    @PostMapping()
    public ResponseEntity<Car> save(@RequestBody Car Car){
        Car CarNew = carService.save(Car);
        return ResponseEntity.ok(CarNew);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Car>> getByUserId(@PathVariable("userId") int userId){
        List<Car> Cars = carService.byUserId(userId);
        return ResponseEntity.ok(Cars);
    }
}
