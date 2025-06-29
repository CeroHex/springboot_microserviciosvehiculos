package com.tutorial.user_service.feingclients;

import com.tutorial.user_service.model.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//@FeignClient(name = "bike-service", url = "http://localhost:8003")
@FeignClient(name = "bike-service")
public interface BikeFeingClient {

    @PostMapping("/bike")
    Bike save(@RequestBody Bike bike);

    @GetMapping("/bike/byuser/{userId}")
    List<Bike> getBikes(@PathVariable("userId") int userId);
}
