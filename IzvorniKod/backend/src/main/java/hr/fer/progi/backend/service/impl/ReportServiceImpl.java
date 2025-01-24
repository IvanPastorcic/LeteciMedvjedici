package hr.fer.progi.backend.service.impl;

import java.sql.Timestamp;
import java.util.List;

import hr.fer.progi.backend.dto.ReportStatusDTO;
import hr.fer.progi.backend.dto.SettlementDTO;
import hr.fer.progi.backend.model.*;
import hr.fer.progi.backend.repository.exception.InputIsNullException;
import hr.fer.progi.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.fer.progi.backend.dto.CoordinatesDTO;
import hr.fer.progi.backend.dto.ReportDTO;
import hr.fer.progi.backend.model.Enum.ReportStatus;
import hr.fer.progi.backend.model.Enum.Role;
import hr.fer.progi.backend.repository.NaturalDisasterRepository;
import hr.fer.progi.backend.repository.ReportRepository;
import hr.fer.progi.backend.repository.SettlementRepository;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportRepository reportRepository;

	@Autowired
	private SettlementRepository settlementRepository;

	@Autowired
	private NaturalDisasterRepository naturalDisasterRepository;

	@Autowired
	private GeocodingService geocodingService;

	private final NaturalDisasterService naturalDisasterService;

	@Autowired
	private UserService userService;
	private final OAuth2Service oAuth2Service;

	public ReportServiceImpl(NaturalDisasterService naturalDisasterService, OAuth2Service oAuth2Service) {
		this.naturalDisasterService = naturalDisasterService;
		this.oAuth2Service = oAuth2Service;
	}

	// TODO: nakon mi moramo razdijeliti getallreports. Admin ce moci dohvatiti ovaj
	// endpoint, ali građani će moći dohvatiti samo one kojima je status
	// "prihvaćeno"
	@Override
	public List<Report> getAllReports() {
		return reportRepository.findAll();
	}

	@Override
	public Report newReport(ReportDTO dto) {
		AppUser appUser = userService.loadCurrentUser();

		// provjera je li user napracio report u zadnjih sat vremena
		Timestamp oneHourAgo = new Timestamp(System.currentTimeMillis() - 3600 * 1000);
		boolean hasRecentReport = reportRepository.existsByAppUserAndTimeAfter(appUser, oneHourAgo);

		if (hasRecentReport && appUser.getRole().equals(Role.ROLE_USER) && !appUser.getUsername().equals("Anonimni korisnik")) {
			throw new IllegalArgumentException("You can only create one report per hour.");
		}

		// Provjera je li naziv naselja "Current Location"
		if (dto.getSettlementName() == null || dto.getSettlementName().trim().isEmpty()) {
			throw new IllegalArgumentException("Settlement name is required.");
		}

		Settlement settlement = null;
		CoordinatesDTO coordinates = null;
		SettlementDTO settlementDTO = null;
		String reportCoordinates = null;

		// imamo koordinate, trebamo settlement iz njih
		if (dto.getSettlementName().equalsIgnoreCase("Current Location")) {
			String[] parts = dto.getCoordinates().split(",");

			// Convert the parts into float values
			float lat = Float.parseFloat(parts[0].trim());
			float lon = Float.parseFloat(parts[1].trim());
			// coordinates = new CoordinatesDTO(lat, lon);
			settlementDTO = geocodingService.reverseGeocode(lat, lon);
			System.out.println(settlementDTO.getSettlementName());
			settlement = settlementRepository
					.findBySettlementNameIgnoringCaseAndSpaces(settlementDTO.getSettlementName());
			reportCoordinates = dto.getCoordinates();
		}

		// imamo ime naselja, trebamo koordinate iz njega
		if (!dto.getSettlementName().equalsIgnoreCase("Current Location")) {
			settlement = settlementRepository.findBySettlementNameIgnoringCaseAndSpaces(dto.getSettlementName());
			coordinates = geocodingService.geocode(dto.getSettlementName());
			reportCoordinates = coordinates.getStringCoordinates();
		}

		NaturalDisaster naturalDisaster = naturalDisasterService.getOrCreateNaturalDisaster(dto.getDisasterType(),
				settlement);

		Report report = new Report(ReportStatus.PROCESSING, getTime(), reportCoordinates,
				dto.getShortDescription() != null ? dto.getShortDescription() : "No description provided",
				dto.getPhoto() != null ? dto.getPhoto() : "", appUser, naturalDisaster);

		return reportRepository.save(report);
	}

	private Timestamp getTime() {
		long millis = System.currentTimeMillis();
		Timestamp date = new Timestamp(millis); // vraca jedan sat manje...
		return date;
	}

	public Report findById(Long id) {
		return reportRepository.findById(id).orElse(null);
	}

	@Override
	public List<Report> findByReportStatus(ReportStatus status) {
		return reportRepository.findByReportStatus(status);
	}

	@Override
	public Report deleteById(Long id) {
		Report r;
		if (reportRepository.findById(id).orElse(null) != null) {
			r = reportRepository.findById(id).orElse(null);
			reportRepository.deleteById(id);
			return r;
		}
		return null;
	}

	@Override
	public Report changeStatus(Long id, ReportStatusDTO dto) {
		System.out.println("servis " + dto.getStatus());
		Report report = reportRepository.findById(id)
				.orElseThrow(() -> new InputIsNullException("Report with id " + id + " not found."));

		if (dto.getStatus() == (ReportStatus.DENIED))
			report.setStatus(ReportStatus.DENIED);
		else if (dto.getStatus() == (ReportStatus.ACCEPTED))
			report.setStatus(ReportStatus.ACCEPTED);
		else if (dto.getStatus() == (ReportStatus.PROCESSING))
			report.setStatus(ReportStatus.PROCESSING);

		return reportRepository.save(report);
	}

	@Override
	public List<Report> getAcceptedReports() {
		return reportRepository.findAllByReportStatus(ReportStatus.ACCEPTED);
	}

	@Override
	public List<Report> getProcessingReports() {
		return reportRepository.findAllByReportStatus(ReportStatus.PROCESSING);
	}

	public List<Report> getDeniedReports() {
		return reportRepository.findAllByReportStatus(ReportStatus.DENIED);
	}

}
