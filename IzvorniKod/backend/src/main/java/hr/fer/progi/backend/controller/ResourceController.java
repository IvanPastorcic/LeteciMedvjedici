package hr.fer.progi.backend.controller;

import hr.fer.progi.backend.dto.ReportDTO;
import hr.fer.progi.backend.dto.ResourceDTO;
import hr.fer.progi.backend.dto.ResourceUpdateDTO;
import hr.fer.progi.backend.model.Embeddable.ResourceId;
import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.model.Resource;
import hr.fer.progi.backend.repository.exception.InputIsNullException;
import hr.fer.progi.backend.service.ResourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//controller for managing humanitarian organizations resources like shelters, water, food items, sand bags...
@RestController
@RequestMapping("/resource")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }


    @GetMapping("/all")
    @Secured("ROLE_HUMANITARIAN")
    List<Resource> getResources(){
        return resourceService.getAllResources();

    }

/*
    @GetMapping("/all/{id}")
    @Secured("ROLE_HUMANITARIAN")
    List<Resource> getResourcesByOrganisation(@PathVariable Long id){
        List<Resource> organisationResources = resourceService.getAllResourcesByOrganisation(id);

        if(organisationResources == null){
            throw new InputIsNullException("Organizacija nema resurse.");
        }

        return organisationResources;

    }*/

    @GetMapping("/own")
    @Secured("ROLE_HUMANITARIAN")
    List<Resource> getResourcesByOrganisation(){
        List<Resource> organisationResources = resourceService.getAllResourcesByOrganisation();

        if(organisationResources == null){
            throw new InputIsNullException("Organizacija nema resurse.");
        }

        return organisationResources;

    }


    @PostMapping("/add/new")
    @Secured("ROLE_HUMANITARIAN")
    public ResponseEntity<Resource> newResource(@RequestBody ResourceDTO dto){

        Resource resource =  resourceService.newResource(dto);
        return ResponseEntity.ok(resource);
    }


    @PatchMapping("/update")
    @Secured("ROLE_HUMANITARIAN")
    public ResponseEntity<Resource> updateResource(@RequestBody ResourceUpdateDTO dto){
        System.out.println("ušli smo u patch DTO: " + dto );
        Resource res = resourceService.updateResource(dto);
        return ResponseEntity.ok(res);
    }

}
