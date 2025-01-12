package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.ResourceDTO;
import hr.fer.progi.backend.dto.ResourceUpdateDTO;
import hr.fer.progi.backend.model.AppUser;
import hr.fer.progi.backend.model.Embeddable.ResourceId;
import hr.fer.progi.backend.model.Enum.ResourceType;
import hr.fer.progi.backend.model.HumanitarianOrganization;
import hr.fer.progi.backend.model.Resource;
import hr.fer.progi.backend.repository.HumanitarianOrganizationRepository;
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
    private final HumanitarianOrganizationRepository humanitarianOrganizationRepository;

    public ResourceServiceImpl(ResourceRepository resourceRepository, UserService userService, HumanitarianOrganizationRepository humanitarianOrganizationRepository) {
        this.resourceRepository = resourceRepository;
        this.userService = userService;
        this.humanitarianOrganizationRepository = humanitarianOrganizationRepository;
    }

    @Override
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    @Override
    public List<Resource> getAllResourcesByOrganisation() {
        AppUser user = userService.loadCurrentUser();
        HumanitarianOrganization organization = humanitarianOrganizationRepository.findByOrganizationName(user.getUsername());

        return resourceRepository.findByHumanitarianOrganization(organization);
    }


    @Override
    public Resource newResource(ResourceDTO dto) {
        AppUser appUser = userService.loadCurrentUser();

        // Fetch or create the HumanitarianOrganization
        HumanitarianOrganization organisation = humanitarianOrganizationRepository
                .findByOrganizationName(appUser.getUsername());
        if (organisation == null) {
            organisation = new HumanitarianOrganization(appUser.getUsername());
            humanitarianOrganizationRepository.save(organisation);
        }

        // Validate input
        String resourceAddress = dto.getLocation();
        if (resourceAddress == null || resourceAddress.trim().isEmpty()) {
            throw new IllegalArgumentException("Resource address cannot be null or empty");
        }

        ResourceType resourceType = dto.getType();
        if (resourceType == null) {
            throw new IllegalArgumentException("Resource type cannot be null");
        }

        // Create the embedded ID
        ResourceId resourceId = new ResourceId(resourceType, resourceAddress);

        // Create the Resource
        Resource resource = new Resource(dto.getQuantity(), organisation);
        resource.setId(resourceId);

        // Save the resource
        return resourceRepository.save(resource);
    }

/*
    @Override
    public Resource updateResource(ResourceUpdateDTO dto) {
        Resource resource = resourceRepository.findById(id).orElseThrow(() -> new InputIsNullException("Resource with id " + id + " not found."));

        resource.setQuantity(dto.getQuantity());

        return resourceRepository.save(resource);
    }
*/
@Override
public Resource updateResource(ResourceUpdateDTO resourceUpdateDTO) {

    System.out.println("ušli smo u servis");
    if (resourceUpdateDTO.getQuantity() < 0) {
        throw new IllegalArgumentException("Quantity cannot be negative.");
    }

    String address = resourceUpdateDTO.getAddress();
    ResourceType resourceType = resourceUpdateDTO.getResourceType();
    ResourceId resourceId = new ResourceId(resourceType, address);
    Resource resource = resourceRepository.findById(resourceId)
            .orElseThrow(() -> new InputIsNullException(
                    "Resource with ID (" + resourceId.getResourceType() + ", " + resourceId.getAddress() + ") not found."));

    resource.setQuantity(resourceUpdateDTO.getQuantity());
    return resourceRepository.save(resource);
}


}
