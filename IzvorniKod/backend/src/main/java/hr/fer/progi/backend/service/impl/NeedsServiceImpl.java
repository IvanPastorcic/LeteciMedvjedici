package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.NeedDTO;
import hr.fer.progi.backend.model.*;
import hr.fer.progi.backend.model.Enum.NeedStatus;
import hr.fer.progi.backend.model.Enum.NeedType;
import hr.fer.progi.backend.model.Enum.ReportStatus;
import hr.fer.progi.backend.repository.NeedsRepository;
import hr.fer.progi.backend.repository.exception.InputIsNullException;
import hr.fer.progi.backend.service.NeedsService;
import hr.fer.progi.backend.service.UserService;

import java.util.List;

public class NeedsServiceImpl implements NeedsService {

    private final NeedsRepository needsRepository;
    private final UserService userService;

    public NeedsServiceImpl(NeedsRepository needsRepository, UserService userService) {
        this.needsRepository = needsRepository;
        this.userService = userService;
    }

    @Override
    public List<Need> getAllNeeds() {
        return needsRepository.findAll();
    }

    @Override
    public Need findById(Long id) {
        return needsRepository.findNeedById(id);
    }

    @Override
    public Need changeStatus(Long id) {
        Need need = needsRepository.findNeedById(id);

        if (need == null){
            throw new InputIsNullException("Potreba ne postoji.");
        }

        need.setNeedStatus(NeedStatus.SOLVED);

        return need;
    }

    @Override
    public Need newNeed(NeedDTO dto) {
        AppUser appUser = userService.loadCurrentUser();

        Need need = new Need(dto.getType(), appUser, dto.getLocation());
        need.setNeedStatus(NeedStatus.PROCESSING);
        return needsRepository.save(need);
    }
}
