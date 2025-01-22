package hr.fer.progi.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.fer.progi.backend.model.Location;
import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.model.Settlement;
import hr.fer.progi.backend.model.Enum.DisasterType;
import hr.fer.progi.backend.repository.LocationRepository;
import hr.fer.progi.backend.service.LocationService;
import hr.fer.progi.backend.service.ReportService;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    private ReportService reportService;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> getAllReported() {
        List<Report> allReports = reportService.getAllReports();
        List<Location> locations = new ArrayList<>();
        
        // Iteriramo kroz sve izvještaje i dohvaćamo odgovarajuće lokacije
        for (Report report : allReports) {
            Long id = report.getId();
            Report rep = reportService.findById(id);
            Settlement settlement = rep.getDisaster().getSettlement();
            Location location = locationRepository.findBySettlement(settlement).orElse(null);
            
            // Provjeravamo da li je pronađena lokacija
            if (location != null) {
                locations.add(location);  // Dodajemo cijeli objekt Location
            }
        }

        return locations;
    }

    @Override
    public List<Location> getCoordinatesByType(DisasterType disasterType) {
        List<Report> allReports = reportService.getAllReports();
        List<Location> locations = new ArrayList<>();
        
        // Filtriramo izvještaje prema vrsti katastrofe i dohvaćamo odgovarajuće lokacije
        for (Report report : allReports) {
            if (report.getDisaster().getDisasterType().equals(disasterType)) {
                Long id = report.getId();
                Report rep = reportService.findById(id);
                Settlement settlement = rep.getDisaster().getSettlement();
                Location location = locationRepository.findBySettlement(settlement).orElse(null);
                
                // Provjeravamo da li je pronađena lokacija
                if (location != null) {
                    locations.add(location);  // Dodajemo cijeli objekt Location
                }
            }
        }

        return locations;
    }
}
