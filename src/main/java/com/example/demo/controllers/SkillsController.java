package com.example.demo.controllers;

import com.example.demo.dtos.SkillsDto;
import com.example.demo.entities.Skills;
import com.example.demo.services.SkillsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("skills")
public class SkillsController {

    private final SkillsService skillsService;

    public SkillsController(SkillsService skillsService) {
        this.skillsService = skillsService;
    }

    @PostMapping
    ResponseEntity<SkillsDto> createSkill (@RequestBody @Valid SkillsDto skillsDto){
        return ResponseEntity.ok(skillsService.createSkills(skillsDto));
    }

    @GetMapping("getAll")
    ResponseEntity<List<SkillsDto>> getAllSkills(){
        return ResponseEntity.ok(skillsService.getAllSkills());
    }

    @GetMapping
    ResponseEntity<SkillsDto> getSkillsByID (@RequestParam Integer id){
        return ResponseEntity.ok(skillsService.getSkillsById(id));
    }

    @DeleteMapping
    ResponseEntity<Void> deletedSkill (@RequestParam Integer id){
        skillsService.deletedSkillsById(id);
        return ResponseEntity.noContent().build();
    }
}
