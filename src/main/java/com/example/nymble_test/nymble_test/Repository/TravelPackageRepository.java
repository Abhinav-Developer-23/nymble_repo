package com.example.nymble_test.nymble_test.Repository;

import com.example.nymble_test.nymble_test.Model.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {

}
