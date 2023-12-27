package com.example.nymble_test.nymble_test.Model;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int passengerCapacity;

    @JsonManagedReference
    @OneToMany(mappedBy = "travelPackage", fetch = FetchType.EAGER)
    private List<Destination> itinerary;

    @JsonManagedReference
    @OneToMany(mappedBy = "travelPackage", fetch = FetchType.EAGER)
    private List<Passenger> passengers;


}
