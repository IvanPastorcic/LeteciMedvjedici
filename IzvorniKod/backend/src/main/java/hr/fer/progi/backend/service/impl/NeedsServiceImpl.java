package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.NeedDTO;
import hr.fer.progi.backend.model.*;
import hr.fer.progi.backend.model.Embeddable.NeedId;
import hr.fer.progi.backend.model.Enum.NeedStatus;
import hr.fer.progi.backend.repository.NeedsRepository;
import hr.fer.progi.backend.repository.ReportRepository;
import hr.fer.progi.backend.repository.exception.InputIsNullException;
import hr.fer.progi.backend.service.NeedsService;
import hr.fer.progi.backend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NeedsServiceImpl implements NeedsService {

    private final NeedsRepository needsRepository;
    private final UserService userService;
    private final ReportRepository reportRepository;

    public NeedsServiceImpl(NeedsRepository needsRepository, UserService userService, ReportRepository reportRepository) {
        this.needsRepository = needsRepository;
        this.userService = userService;
        this.reportRepository = reportRepository;
    }

    @Override
    public List<Need> getAllNeeds() {
        return needsRepository.findAll();
    }

    @Override
    public Need findById(NeedId id) {
        return needsRepository.findNeedById(id);
    }

    @Override
    public Need changeStatus(NeedId id) {
        Need need = needsRepository.findNeedById(id);

        if (need == null){
            throw new InputIsNullException("Potreba ne postoji.");
        }

        need.setNeedStatus(NeedStatus.SOLVED);

        return need;
    }

    @Override
    public Need newNeed(NeedDTO dto) {
        //AppUser appUser = userService.loadCurrentUser();

        Report report = reportRepository.findById(dto.getId()).orElseThrow(() -> new InputIsNullException("Report with id " + dto.getId() + " not found."));
        Need need = new Need(dto.getType(), report, dto.getLocation(), dto.getQuantity());
        need.setNeedStatus(NeedStatus.PROCESSING);
        return needsRepository.save(need);
    }
}
