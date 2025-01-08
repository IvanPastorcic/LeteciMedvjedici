package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.ResourceDTO;
import hr.fer.progi.backend.dto.ResourceUpdateDTO;
import hr.fer.progi.backend.model.AppUser;
import hr.fer.progi.backend.model.Embeddable.ResourceId;
import hr.fer.progi.backend.model.Resource;
import hr.fer.progi.backend.repository.ResourceRepository;
import hr.fer.progi.backend.repository.exception.InputIsNullException;
import hr.fer.progi.backend.service.ResourceService;
import hr.fer.progi.backend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;
    private final UserService userService;

    public ResourceServiceImpl(ResourceRepository resourceRepository, UserService userService) {
        this.resourceRepository = resourceRepository;
        this.userService = userService;
    }

    @Override
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    @Override
    public List<Resource> getAllResourcesByOrganisation(Long id) {
        return resourceRepository.findByHumanitarianOrganizationId(id);
    }


    //add a new resource (TODO: dohvatiti o kojoj se humanitarnoj organizaciji radi, nešto poput load current user, ali za organizaciju)
    @Override
    public Resource newResource(ResourceDTO dto) {
        Resource resource = new Resource(dto.getType(), dto.getLocation(), dto.getQuantity());

        return resourceRepository.save(resource);
    }

    @Override
    public Resource updateResource(ResourceId id, ResourceUpdateDTO dto) {
        Resource resource = resourceRepository.findById(id);

        resource.setQuantity(dto.getQuantity());

        return resourceRepository.save(resource);
    }


}
