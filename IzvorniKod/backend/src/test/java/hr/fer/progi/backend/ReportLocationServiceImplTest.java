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
class ReportLocationServiceImplTest {

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private SettlementRepository settlementRepository;

    @Mock
    private NaturalDisasterRepository naturalDisasterRepository;

    @Mock
    private UserService userService;

    @Mock
    private OAuth2Service oAuth2Service;

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
void newReport_ValidLocation() {
    // Priprema
    AppUser mockUser = new AppUser();
    mockUser.setEmail("valid@example.com");
    Settlement mockSettlement = new Settlement();
    NaturalDisaster mockDisaster = new NaturalDisaster();

    when(userService.loadCurrentUser()).thenReturn(mockUser);
    when(settlementRepository.findFirstBySettlementName("Zagreb")).thenReturn(mockSettlement);
    when(naturalDisasterRepository.save(any(NaturalDisaster.class))).thenReturn(mockDisaster);
    when(reportRepository.save(any(Report.class))).thenAnswer(invocation -> invocation.getArgument(0));

    // Akcija
    ReportDTO dto = new ReportDTO("Zagreb", DisasterType.FLOOD, "Poplava", "photo.jpg");
    Report result = reportService.newReport(dto);

    // Provjera
    assertNotNull(result);
    assertEquals("Zagreb", dto.getSettlementName()); // Provjera unosa mjesta nepogode
    verify(reportRepository, times(1)).save(any(Report.class));
}

    @Test
    void newReport_InvalidLocation() {
        // Priprema
        AppUser mockUser = new AppUser();
        mockUser.setEmail("invalid@example.com");

        when(userService.loadCurrentUser()).thenReturn(mockUser);
        when(settlementRepository.findFirstBySettlementName("Unknown")).thenReturn(null);

        // Akcija i provjera
        ReportDTO dto = new ReportDTO("Unknown", DisasterType.FLOOD, "Poplava", "photo.jpg");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reportService.newReport(dto));
        assertEquals("Settlement not found: Unknown", exception.getMessage());

        // Verifikacija da save nije pozvana
        verify(reportRepository, never()).save(any(Report.class));
    }

    @Test
    void newReport_EmptyLocation() {
        // Priprema
        AppUser mockUser = new AppUser();
        mockUser.setEmail("empty@example.com");

        when(userService.loadCurrentUser()).thenReturn(mockUser);

        // Akcija i provjera
        ReportDTO dto = new ReportDTO("", DisasterType.FLOOD, "Poplava", "photo.jpg");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reportService.newReport(dto));
        assertEquals("Settlement name cannot be empty", exception.getMessage());

        // Verifikacija da save nije pozvana
        verify(reportRepository, never()).save(any(Report.class));
    }

   @Test
void newReport_UnusualLocation() {
    // Priprema
    String unusualLocation = "🌍🔥";
    AppUser mockUser = new AppUser();
    mockUser.setEmail("unusual@example.com");
    Settlement mockSettlement = new Settlement();

    when(userService.loadCurrentUser()).thenReturn(mockUser);
    when(settlementRepository.findFirstBySettlementName(unusualLocation)).thenReturn(mockSettlement);
    when(naturalDisasterRepository.save(any(NaturalDisaster.class))).thenReturn(new NaturalDisaster());
    when(reportRepository.save(any(Report.class))).thenAnswer(invocation -> invocation.getArgument(0));

    // Akcija
    ReportDTO dto = new ReportDTO(unusualLocation, DisasterType.FLOOD, "Poplava", "photo.jpg");
    Report result = reportService.newReport(dto);

    // Provjera
    assertNotNull(result);
    assertEquals(unusualLocation, dto.getSettlementName()); // Provjera unosa mjesta nepogode
    verify(reportRepository, times(1)).save(any(Report.class));
}
}