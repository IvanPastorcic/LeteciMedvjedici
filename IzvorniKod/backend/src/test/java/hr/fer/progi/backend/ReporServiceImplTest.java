package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.ReportDTO;
import hr.fer.progi.backend.model.*;
import hr.fer.progi.backend.model.Enum.DisasterType;
import hr.fer.progi.backend.repository.*;
import hr.fer.progi.backend.service.OAuth2Service;
import hr.fer.progi.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Koristi Mockito ekstenziju za JUnit 5
class ReportServiceImplTest {

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private SettlementRepository settlementRepository;

    @Mock
    private NaturalDisasterRepository naturalDisasterRepository;

    @Mock
    private UserService userService;

    @Mock
    private OAuth2Service oAuth2Service; // Dodano mockiranje OAuth2Service

    @InjectMocks
    private ReportServiceImpl reportService;

    @BeforeEach
    void setUp() throws Exception {
        // Ručno injektiranje poljnih ovisnosti koristeći refleksiju
        Field userServiceField = ReportServiceImpl.class.getDeclaredField("userService");
        userServiceField.setAccessible(true);
        userServiceField.set(reportService, userService);

        Field settlementRepositoryField = ReportServiceImpl.class.getDeclaredField("settlementRepository");
        settlementRepositoryField.setAccessible(true);
        settlementRepositoryField.set(reportService, settlementRepository);

        Field reportRepositoryField = ReportServiceImpl.class.getDeclaredField("reportRepository");
        reportRepositoryField.setAccessible(true);
        reportRepositoryField.set(reportService, reportRepository);

        Field naturalDisasterRepositoryField = ReportServiceImpl.class.getDeclaredField("naturalDisasterRepository");
        naturalDisasterRepositoryField.setAccessible(true);
        naturalDisasterRepositoryField.set(reportService, naturalDisasterRepository);

        Field oAuth2ServiceField = ReportServiceImpl.class.getDeclaredField("oAuth2Service");
        oAuth2ServiceField.setAccessible(true);
        oAuth2ServiceField.set(reportService, oAuth2Service);
    }

    @Test
    void newReport_ValidShortDescription() {
        // Priprema
        AppUser mockUser = new AppUser();
        mockUser.setEmail("email@example.com");
        Settlement mockSettlement = new Settlement();
        NaturalDisaster mockDisaster = new NaturalDisaster();

        when(userService.loadCurrentUser()).thenReturn(mockUser);
        when(settlementRepository.findFirstBySettlementName("Zagreb")).thenReturn(mockSettlement);
        when(naturalDisasterRepository.save(any(NaturalDisaster.class))).thenReturn(mockDisaster);
        when(reportRepository.save(any(Report.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Akcija
        ReportDTO dto = new ReportDTO("Zagreb", DisasterType.FLOOD, "Poplava u Zagrebu", "photo.jpg");
        Report result = reportService.newReport(dto);

        // Provjera
        assertNotNull(result);
        assertEquals("Poplava u Zagrebu", result.getShortDescription());
        verify(reportRepository, times(1)).save(any(Report.class));
    }

    @Test
    void newReport_MaxShortDescriptionLength() {
        // Priprema
        String longDescription = "A".repeat(255);
        AppUser mockUser = new AppUser();
        mockUser.setEmail("longdesc@example.com");
        Settlement mockSettlement = new Settlement();
        NaturalDisaster mockDisaster = new NaturalDisaster();

        when(userService.loadCurrentUser()).thenReturn(mockUser);
        when(settlementRepository.findFirstBySettlementName("Split")).thenReturn(mockSettlement);
        when(naturalDisasterRepository.save(any(NaturalDisaster.class))).thenReturn(mockDisaster);
        when(reportRepository.save(any(Report.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Akcija
        ReportDTO dto = new ReportDTO("Split", DisasterType.HURRICANE, longDescription, "photo.jpg");
        Report result = reportService.newReport(dto);

        // Provjera
        assertNotNull(result);
        assertEquals(longDescription, result.getShortDescription());
        verify(reportRepository, times(1)).save(any(Report.class));
    }

    @Test
    void newReport_MissingShortDescription() {
        // Priprema
        AppUser mockUser = new AppUser();
        mockUser.setEmail("missingdesc@example.com");
        Settlement mockSettlement = new Settlement();

        when(userService.loadCurrentUser()).thenReturn(mockUser);
        when(settlementRepository.findFirstBySettlementName("Osijek")).thenReturn(mockSettlement);
        when(naturalDisasterRepository.save(any(NaturalDisaster.class))).thenReturn(new NaturalDisaster());
        when(reportRepository.save(any(Report.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Akcija
        ReportDTO dto = new ReportDTO("Osijek", DisasterType.WILDFIRE, null, "photo.jpg");
        Report result = reportService.newReport(dto);

        // Provjera
        assertNotNull(result);
        assertNull(result.getShortDescription(), "Short description should be null");
        verify(reportRepository, times(1)).save(any(Report.class));
    }

    @Test
    void newReport_UnusualShortDescription() {
        // Priprema
        String unusualDescription = "🔥🌊💥 Nepogoda!";
        AppUser mockUser = new AppUser();
        mockUser.setEmail("unusual@example.com");
        Settlement mockSettlement = new Settlement();
        NaturalDisaster mockDisaster = new NaturalDisaster();

        when(userService.loadCurrentUser()).thenReturn(mockUser);
        when(settlementRepository.findFirstBySettlementName("Rijeka")).thenReturn(mockSettlement);
        when(naturalDisasterRepository.save(any(NaturalDisaster.class))).thenReturn(mockDisaster);
        when(reportRepository.save(any(Report.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Akcija
        ReportDTO dto = new ReportDTO("Rijeka", DisasterType.EARTHQUAKE, unusualDescription, "photo.jpg");
        Report result = reportService.newReport(dto);

        // Provjera
        assertNotNull(result);
        assertEquals(unusualDescription, result.getShortDescription());
        verify(reportRepository, times(1)).save(any(Report.class));
    }
}
