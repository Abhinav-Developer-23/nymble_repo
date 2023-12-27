import com.example.nymble_test.nymble_test.Controller.Controller;
import com.example.nymble_test.nymble_test.Model.Activity;
import com.example.nymble_test.nymble_test.Model.Passenger;
import com.example.nymble_test.nymble_test.Model.TravelPackage;
import com.example.nymble_test.nymble_test.Repository.ActivityRepository;
import com.example.nymble_test.nymble_test.Repository.PassengerRepository;
import com.example.nymble_test.nymble_test.Repository.TravelPackageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ControllerTest {

    @Mock
    private TravelPackageRepository travelPackageRepository;

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private ActivityRepository activityRepository;

    @InjectMocks
    private Controller controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTravelPackageById_WhenPackageExists() {
        Long id = 1L;
        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setId(id);
        travelPackage.setPassengers(new ArrayList<>());

        when(travelPackageRepository.findById(id)).thenReturn(Optional.of(travelPackage));

        ResponseEntity<?> response = controller.getTravelPackageById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(travelPackage, response.getBody());
        assertEquals(null, travelPackage.getPassengers());

        verify(travelPackageRepository, times(1)).findById(id);
    }

    @Test
    void testGetTravelPackageById_WhenPackageDoesNotExist() {
        Long id = 1L;

        when(travelPackageRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<?> response = controller.getTravelPackageById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(travelPackageRepository, times(1)).findById(id);
    }

    @Test
    void testGetTravelPackagePassengersById_WhenPackageExists() {
        Long id = 1L;
        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setId(id);
        travelPackage.setItinerary(new ArrayList<>());

        when(travelPackageRepository.findById(id)).thenReturn(Optional.of(travelPackage));

        ResponseEntity<?> response = controller.getTravelPackagePassengersById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(travelPackage, response.getBody());
        assertEquals(null, travelPackage.getItinerary());

        verify(travelPackageRepository, times(1)).findById(id);
    }

    @Test
    void testGetTravelPackagePassengersById_WhenPackageDoesNotExist() {
        Long id = 1L;

        when(travelPackageRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<?> response = controller.getTravelPackagePassengersById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(travelPackageRepository, times(1)).findById(id);
    }

    @Test
    void testGetPassengerById_WhenPassengerExists() {
        Long id = 1L;
        Passenger passenger = new Passenger();
        passenger.setId(id);

        when(passengerRepository.findById(id)).thenReturn(Optional.of(passenger));

        ResponseEntity<?> response = controller.getPassengerById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(passenger, response.getBody());

        verify(passengerRepository, times(1)).findById(id);
    }

    @Test
    void testGetPassengerById_WhenPassengerDoesNotExist() {
        Long id = 1L;

        when(passengerRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<?> response = controller.getPassengerById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(passengerRepository, times(1)).findById(id);
    }

    @Test
    void testGetFreeActivities_WhenActivitiesExist() {
        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity());
        activities.add(new Activity());

        when(activityRepository.findActivitiesWithCapacityGreaterThanZero()).thenReturn(activities);

        ResponseEntity<?> response = controller.getFreeActivities();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(activities, response.getBody());

        verify(activityRepository, times(1)).findActivitiesWithCapacityGreaterThanZero();
    }

    @Test
    void testGetFreeActivities_WhenActivitiesDoNotExist() {
        when(activityRepository.findActivitiesWithCapacityGreaterThanZero()).thenReturn(new ArrayList<>());

        ResponseEntity<?> response = controller.getFreeActivities();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(activityRepository, times(1)).findActivitiesWithCapacityGreaterThanZero();
    }
}