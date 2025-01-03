package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.NeedDTO;
import hr.fer.progi.backend.model.Action;
import hr.fer.progi.backend.model.Need;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NeedsService {
    List<Need> getAllNeeds();

    Need findById(Long id);

    Need changeStatus(Long id);

    Need newNeed(NeedDTO dto);
}
