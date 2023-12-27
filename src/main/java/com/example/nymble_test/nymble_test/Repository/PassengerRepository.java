package com.example.nymble_test.nymble_test.Repository;

import com.example.nymble_test.nymble_test.Model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
