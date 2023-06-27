package com.example.demo.services;

import com.example.demo.dtos.SkillsDto;
import com.example.demo.exception.MedBookingException;
import com.example.demo.mappers.Mappers;
import com.example.demo.repositories.SkillsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillsService implements Mappers {
    private final SkillsRepository skillsRepository;

    public SkillsService(SkillsRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }

    public SkillsDto createSkills(SkillsDto skillsDto) {
        return mapperSkillsEntityToDto(skillsRepository.save(mapperSkillsDtoToEntity(skillsDto)));
    }

    public SkillsDto getSkillsById(Integer id) {
        return mapperSkillsEntityToDto(skillsRepository.findById(id)
                .orElseThrow(() -> new MedBookingException("Skills with id " + id + " does not exist.", LocalDateTime.now())));
    }

    public List<SkillsDto> getAllSkills() {
        return skillsRepository.findAll().stream()
                .map(o-> mapperSkillsEntityToDto(o)).collect(Collectors.toList());
    }

    public void deletedSkillsById(Integer id) {
        skillsRepository.findById(id)
                .orElseThrow(() -> new MedBookingException("Skills with id " + id + " does not exist.", LocalDateTime.now()));
        skillsRepository.deleteById(id);
    }

}
