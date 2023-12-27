package com.example.nymble_test.nymble_test;

import com.example.nymble_test.nymble_test.Model.TravelPackage;
import com.example.nymble_test.nymble_test.Repository.PassengerRepository;
import com.example.nymble_test.nymble_test.Repository.TravelPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.nymble_test.nymble_test.Model.Passenger;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class NymbleTestApplication implements CommandLineRunner {


    @Autowired
    TravelPackageRepository travelPackageRepository;
	@Autowired
	PassengerRepository passengerRepository;

    public static void main(String[] args) {
        SpringApplication.run(NymbleTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        List<TravelPackage> travelPackages = Arrays.asList(
                TravelPackage.builder().name("Shilong package").passengerCapacity(15).build(),
                TravelPackage.builder().name("Kasol").passengerCapacity(20).build(),
                TravelPackage.builder().name("Kashmir Package").passengerCapacity(50).build(),
                TravelPackage.builder().name("Manali Package").passengerCapacity(50).build()
        );
        travelPackageRepository.saveAll(travelPackages);

        TravelPackage travelPackage = travelPackages.get(0);
        List<Passenger> samplePassengers = Arrays.asList(
                Passenger.builder().name("John Doe").passengerType("Standard").balance(100.0).travelPackage(travelPackage).build(),
                Passenger.builder().name("Jane Smith").passengerType("standard").balance(500.0).travelPackage(travelPackage).build(),
                Passenger.builder().name("Alice Johnson").passengerType("gold").balance(200.0).travelPackage(travelPackage).build(),
                Passenger.builder().name("Bob Williams").passengerType("gold").balance(600.0).travelPackage(travelPackage).build(),
                Passenger.builder().name("Eva Davis").passengerType("premium").balance(300.0).travelPackage(travelPackage).build()
        );
		passengerRepository.saveAll(samplePassengers);
    }
}
