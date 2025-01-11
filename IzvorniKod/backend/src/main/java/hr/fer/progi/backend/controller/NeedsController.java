package hr.fer.progi.backend.controller;

import hr.fer.progi.backend.dto.NeedDTO;
import hr.fer.progi.backend.dto.ReportDTO;
import hr.fer.progi.backend.model.Action;
import hr.fer.progi.backend.model.Embeddable.NeedId;
import hr.fer.progi.backend.model.Need;
import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.repository.exception.InputIsNullException;
import hr.fer.progi.backend.service.NeedsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


//controller for managing users needs for shelter, food, water ...
@RestController
@RequestMapping("/needs")
public class NeedsController {

    private final NeedsService needsService;

    public NeedsController(NeedsService needsService) {
        this.needsService = needsService;
    }


    @GetMapping("/all")
   // @Secured({"ROLE_ADMIN", "ROLE_HUMANITARIAN", "ROLE_AUTHORITY"})
    List<Need> needs() {
        return needsService.getAllNeeds();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Need> findNeedById(@PathVariable NeedId id){
        Need need = needsService.findById(id);

        if(need == null){
            throw new InputIsNullException("Potreba ne postoji");
        }

        return ResponseEntity.ok(need);
    }


    @PatchMapping("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_HUMANITARIAN"})
    public ResponseEntity<Need> changeStatus(@PathVariable NeedId id){
        Need need = needsService.changeStatus(id);

        return ResponseEntity.ok(need);
    }

//users can add their own needs so humanitarian organisations can see who needs help and where

    /*
    @PostMapping("/add")
    @Secured("ROLE_USER")
    public ResponseEntity<Need> newNeed(@RequestBody NeedDTO dto){
        Need need = needsService.newNeed(dto);
        return ResponseEntity.ok(need);
    }*/

    @PostMapping("/add")
    @Secured("ROLE_USER")
    public ResponseEntity<List<Need>> newNeeds(@RequestBody List<NeedDTO> dtoList) {
        List<Need> needs = dtoList.stream()
                .map(dto -> needsService.newNeed(dto))
                .collect(Collectors.toList());

        return ResponseEntity.ok(needs);
    }


}
