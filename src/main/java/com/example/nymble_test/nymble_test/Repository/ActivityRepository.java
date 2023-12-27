package com.example.nymble_test.nymble_test.Repository;

import com.example.nymble_test.nymble_test.Model.Activity;
import com.example.nymble_test.nymble_test.Model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    @Query(value = "SELECT * FROM activity WHERE capacity > 0", nativeQuery = true)
    List<Activity> findActivitiesWithCapacityGreaterThanZero();
}
