package com.example.nymble_test.nymble_test.Controller;

import com.example.nymble_test.nymble_test.Model.Activity;
import com.example.nymble_test.nymble_test.Model.Passenger;
import com.example.nymble_test.nymble_test.Model.TravelPackage;
import com.example.nymble_test.nymble_test.Repository.ActivityRepository;
import com.example.nymble_test.nymble_test.Repository.PassengerRepository;
import com.example.nymble_test.nymble_test.Repository.TravelPackageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class Controller {

    @Autowired
    TravelPackageRepository travelPackageRepository;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    ActivityRepository activityRepository;

    @GetMapping("/travel-package")
    public ResponseEntity<?> getTravelPackageById(@RequestParam("id") Long id) {
        Optional<TravelPackage> travelPackage = travelPackageRepository.findById(id);
        if (travelPackage.isPresent()) {

            TravelPackage resultPackage=travelPackage.get();
            resultPackage.setPassengers(null);
            return ResponseEntity.ok(resultPackage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/travel-package-passengers")
    public ResponseEntity<?> getTravelPackagePassengersById(@RequestParam("id") Long id) {
        Optional<TravelPackage> travelPackage = travelPackageRepository.findById(id);
        if (travelPackage.isPresent()) {

            TravelPackage resultPackage=travelPackage.get();
            resultPackage.setItinerary(null);
            return ResponseEntity.ok(resultPackage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/passengers")
    public ResponseEntity<?> getPassengerById(@RequestParam("id") Long id) {
        Optional<Passenger> passenger = passengerRepository.findById(id);
        if (passenger.isPresent()) {

            Passenger resultPackage=passenger.get();
            return ResponseEntity.ok(resultPackage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get-free-activities")
    public ResponseEntity<?> getFreeActivities() {
        List<Activity> activities = activityRepository.findActivitiesWithCapacityGreaterThanZero();
        if (!activities.isEmpty()) {

            return ResponseEntity.ok(activities);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
