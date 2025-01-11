package hr.fer.progi.backend.service.impl;

import java.sql.Timestamp;
import java.util.List;

import hr.fer.progi.backend.dto.ReportStatusDTO;
import hr.fer.progi.backend.model.*;
import hr.fer.progi.backend.repository.exception.InputIsNullException;
import hr.fer.progi.backend.service.OAuth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hr.fer.progi.backend.dto.ReportDTO;
import hr.fer.progi.backend.model.Enum.ReportStatus;
import hr.fer.progi.backend.repository.NaturalDisasterRepository;
import hr.fer.progi.backend.repository.ReportRepository;
import hr.fer.progi.backend.repository.SettlementRepository;
import hr.fer.progi.backend.service.ReportService;
import hr.fer.progi.backend.service.UserService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportRepository reportRepository;

	@Autowired
	private SettlementRepository settlementRepository;

	@Autowired
	private NaturalDisasterRepository naturalDisasterRepository;

	@Autowired
	private UserService userService;
	private final OAuth2Service oAuth2Service;

	public ReportServiceImpl(OAuth2Service oAuth2Service) {
		this.oAuth2Service = oAuth2Service;
	}



	//TODO: nakon mi moramo razdijeliti getallreports. Admin ce moci dohvatiti ovaj endpoint, ali građani će moći dohvatiti samo one kojima je status "prihvaćeno"
	@Override
	public List<Report> getAllReports() {
		return reportRepository.findAll();
	}

	@Override
	public Report newReport(ReportDTO dto) {
		AppUser appUser = userService.loadCurrentUser();
		Settlement settlement = settlementRepository.findFirstBySettlementName(dto.getSettlementName()); // service kada dodam

		// for testing
//		AppUser testUser = new AppUser("testuser@example.com", "testuser");
//		userService.insertUser(testUser);

		NaturalDisaster naturalDisaster = new NaturalDisaster(dto.getDisasterType(), settlement);
		naturalDisaster = naturalDisasterRepository.save(naturalDisaster);

		Report report = new Report(ReportStatus.PROCESSING, getTime(), dto.getShortDescription(), dto.getPhoto(),
				appUser, naturalDisaster); 
		return reportRepository.save(report);
	}

	private Timestamp getTime() {
		long millis = System.currentTimeMillis();  
		Timestamp date = new Timestamp(millis);  //vraca jedan sat manje...
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
		Report report = reportRepository.findById(id)
				.orElseThrow(() -> new InputIsNullException("Report with id " + id + " not found."));


		if(dto.getStatus().equals(ReportStatus.DENIED))
			report.setStatus(ReportStatus.DENIED);
		else if(dto.getStatus().equals(ReportStatus.ACCEPTED))
			report.setStatus(ReportStatus.ACCEPTED);
		else if(dto.getStatus().equals(ReportStatus.PROCESSING))
			report.setStatus(ReportStatus.PROCESSING);

		return report;
	}

}
