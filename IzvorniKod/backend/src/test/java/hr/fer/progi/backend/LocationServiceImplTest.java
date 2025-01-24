package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.model.Location;
import hr.fer.progi.backend.model.Settlement;
import hr.fer.progi.backend.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Koristi Mockito ekstenziju za JUnit 5
class LocationServiceImplTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationServiceImpl locationService;

    private Settlement mockSettlement;

    @BeforeEach
    void setUp() {
        // Postavljanje zajedničkog podataka za sve testove
        mockSettlement = new Settlement();
        mockSettlement.setSettlementName("Zagreb");
    }

    @Test
    void addLocation_ValidCoordinates() {
        // Redovni slučaj: Ispravan unos koordinata
        String validCoordinates = "45.8150,15.9819";
        Location location = new Location(validCoordinates, mockSettlement);

        when(locationRepository.save(any(Location.class))).thenReturn(location);

        Location savedLocation = locationRepository.save(location);

        assertNotNull(savedLocation);
        assertEquals(validCoordinates, savedLocation.getGeographicalCoordinates());
        verify(locationRepository, times(1)).save(location);
    }

    @Test
    void addLocation_BoundaryCoordinates() {
        // Rubni uvjeti: Maksimalne i minimalne koordinate
        String maxCoordinates = "180.0,90.0";
        String minCoordinates = "-180.0,-90.0";

        Location maxLocation = new Location(maxCoordinates, mockSettlement);
        Location minLocation = new Location(minCoordinates, mockSettlement);

        when(locationRepository.save(any(Location.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Location savedMaxLocation = locationRepository.save(maxLocation);
        Location savedMinLocation = locationRepository.save(minLocation);

        assertNotNull(savedMaxLocation);
        assertEquals(maxCoordinates, savedMaxLocation.getGeographicalCoordinates());

        assertNotNull(savedMinLocation);
        assertEquals(minCoordinates, savedMinLocation.getGeographicalCoordinates());

        verify(locationRepository, times(2)).save(any(Location.class));
    }

    @Test
    void addLocation_InvalidCoordinates() {
        // Izazivanje pogreške: Negativne ili nepravilne koordinate
        String invalidCoordinates = "-200.0,91.0";
        Location location = new Location(invalidCoordinates, mockSettlement);

        Location savedLocation = locationRepository.save(location);

        assertNotNull(savedLocation); // Test provjerava hoće li nepravilne koordinate biti spremljene
        assertEquals(invalidCoordinates, savedLocation.getGeographicalCoordinates());
        verify(locationRepository, times(1)).save(location);
    }

    @Test
    void addLocation_NonGeographicalString() {
        // Nepostojeća funkcionalnost: Unos stringa umjesto koordinata
        String invalidString = "invalid_coordinates";
        Location location = new Location(invalidString, mockSettlement);

        Location savedLocation = locationRepository.save(location);

        assertNotNull(savedLocation); // Test provjerava hoće li se spremiti string
        assertEquals(invalidString, savedLocation.getGeographicalCoordinates());
        verify(locationRepository, times(1)).save(location);
    }

    @Test
    void addLocation_CoordinatesExceedMaximumValues() {
        // Slučaj: Koordinate koje prelaze maksimalne dopuštene vrijednosti
        String exceedingMaxCoordinates = "181.0,91.0"; // Veće od maksimalnih koordinata
        Location location = new Location(exceedingMaxCoordinates, mockSettlement);

        //when(locationRepository.save(any(Location.class))).thenReturn(location);

        Location savedLocation = locationRepository.save(location);

        assertNotNull(savedLocation); // Test provjerava da je lokacija spremljena
        assertEquals(exceedingMaxCoordinates, savedLocation.getGeographicalCoordinates());
        verify(locationRepository, times(1)).save(location);
    }

    @Test
    void addLocation_CoordinatesBelowMinimumValues() {
        // Slučaj: Koordinate koje su manje od minimalnih dopuštenih vrijednosti
        String belowMinCoordinates = "-181.0,-91.0"; // Manje od minimalnih koordinata
        Location location = new Location(belowMinCoordinates, mockSettlement);

        //when(locationRepository.save(any(Location.class))).thenReturn(location);

        Location savedLocation = locationRepository.save(location);

        assertNotNull(savedLocation); // Test provjerava da je lokacija spremljena
        assertEquals(belowMinCoordinates, savedLocation.getGeographicalCoordinates());
        verify(locationRepository, times(1)).save(location);
    }

}
