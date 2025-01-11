package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.ResourceDTO;
import hr.fer.progi.backend.dto.ResourceUpdateDTO;
import hr.fer.progi.backend.model.Embeddable.ResourceId;
import hr.fer.progi.backend.model.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResourceService {

    List<Resource> getAllResources();

    List<Resource> getAllResourcesByOrganisation();

    Resource newResource(ResourceDTO dto);

    Resource updateResource(ResourceUpdateDTO dto);
}
