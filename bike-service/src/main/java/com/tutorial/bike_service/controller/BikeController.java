package com.tutorial.bike_service.controller;

import com.tutorial.bike_service.entity.Bike;
import com.tutorial.bike_service.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {
    @Autowired
    BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll(){
        List<Bike> Bikes = bikeService.getAll();
        if(Bikes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(Bikes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getById(@PathVariable("id") int id){
        Bike Bike = bikeService.getBikeById(id);
        if(Bike==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Bike);
    }

    @PostMapping()
    public ResponseEntity<Bike> save(@RequestBody Bike Bike){
        Bike BikeNew = bikeService.save(Bike);
        return ResponseEntity.ok(BikeNew);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable("userId") int userId){
        List<Bike> Bikes = bikeService.byUserId(userId);
        return ResponseEntity.ok(Bikes);
    }
}
