package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.ResourceUpdateDTO;
import hr.fer.progi.backend.model.Embeddable.ResourceId;
import hr.fer.progi.backend.model.Enum.ResourceType;
import hr.fer.progi.backend.model.HumanitarianOrganization;
import hr.fer.progi.backend.model.Resource;
import hr.fer.progi.backend.repository.HumanitarianOrganizationRepository;
import hr.fer.progi.backend.repository.ResourceRepository;
import hr.fer.progi.backend.repository.exception.InputIsNullException;
import hr.fer.progi.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Koristi Mockito ekstenziju za JUnit 5
class ResourceServiceImplTest {

    @Mock
    private ResourceRepository resourceRepository;

    @Mock
    private HumanitarianOrganizationRepository humanitarianOrganizationRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ResourceServiceImpl resourceService;

    private ResourceUpdateDTO resourceUpdateDTO;
    private ResourceId resourceId;
    private Resource mockResource;

    @BeforeEach
    void setUp() {
        // Postavljanje zajedničkih podataka za sve testove
        resourceId = new ResourceId(ResourceType.FOOD, "Zagreb");
        mockResource = new Resource(10, new HumanitarianOrganization("Org1"));
        mockResource.setId(resourceId);
    }

    @Test
    void updateResource_ValidQuantity() {
        // Redovni slučaj: Pozitivna količina
        resourceUpdateDTO = new ResourceUpdateDTO(20, ResourceType.FOOD, "Zagreb");

        when(resourceRepository.findById(any(ResourceId.class))).thenReturn(Optional.of(mockResource));
        when(resourceRepository.save(any(Resource.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Resource updatedResource = resourceService.updateResource(resourceUpdateDTO);

        assertNotNull(updatedResource);
        assertEquals(20, updatedResource.getQuantity());
        verify(resourceRepository, times(1)).save(mockResource);
    }

    @Test
    void updateResource_ZeroQuantity() {
        // Rubni uvjet: Količina je 0
        resourceUpdateDTO = new ResourceUpdateDTO(0, ResourceType.FOOD, "Zagreb");

        when(resourceRepository.findById(any(ResourceId.class))).thenReturn(Optional.of(mockResource));
        when(resourceRepository.save(any(Resource.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Resource updatedResource = resourceService.updateResource(resourceUpdateDTO);

        assertNotNull(updatedResource);
        assertEquals(0, updatedResource.getQuantity());
        verify(resourceRepository, times(1)).save(mockResource);
    }

    @Test
    void updateResource_NegativeQuantity() {
        // Izazivanje pogreške: Negativna količina
        resourceUpdateDTO = new ResourceUpdateDTO(-10, ResourceType.FOOD, "Zagreb");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> resourceService.updateResource(resourceUpdateDTO));
        assertEquals("Quantity cannot be negative.", exception.getMessage());

        verify(resourceRepository, never()).save(any(Resource.class));
    }

    @Test
    void updateResource_NonExistentResource() {
        // Nepostojeća funkcionalnost: Resurs ne postoji u bazi
        resourceUpdateDTO = new ResourceUpdateDTO(10, ResourceType.FOOD, "Zagreb");

        when(resourceRepository.findById(any(ResourceId.class))).thenReturn(Optional.empty());

        Exception exception = assertThrows(InputIsNullException.class, () -> resourceService.updateResource(resourceUpdateDTO));
        assertEquals("Resource with ID (FOOD, Zagreb) not found.", exception.getMessage());

        verify(resourceRepository, never()).save(any(Resource.class));
    }
}
