package hr.fer.progi.backend.controller;

import hr.fer.progi.backend.dto.NeedDTO;
import hr.fer.progi.backend.dto.ReportDTO;
import hr.fer.progi.backend.model.Action;
import hr.fer.progi.backend.model.Need;
import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.repository.exception.InputIsNullException;
import hr.fer.progi.backend.service.NeedsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//controller for managing users needs for shelter, food, water ...
@RestController
@RequestMapping("/needs")
public class NeedsController {

    private final NeedsService needsService;

    public NeedsController(NeedsService needsService) {
        this.needsService = needsService;
    }

    @GetMapping("/all")
    List<Need> needs() {
        return needsService.getAllNeeds();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Need> findNeedById(@PathVariable Long id){
        Need need = needsService.findById(id);

        if(need == null){
            throw new InputIsNullException("Potreba ne postoji");
        }

        return ResponseEntity.ok(need);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Need> changeStatus(@PathVariable Long id){
        Need need = needsService.changeStatus(id);

        return ResponseEntity.ok(need);
    }

//users can add their own needs so humanitarian organisations can see who needs help and where
    @PostMapping("/add")
    public ResponseEntity<Need> newNeed(@RequestBody NeedDTO dto){
        Need need = needsService.newNeed(dto);
        return ResponseEntity.ok(need);
    }

}