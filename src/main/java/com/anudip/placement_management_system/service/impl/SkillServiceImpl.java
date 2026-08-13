package com.anudip.placement_management_system.service.impl;

import com.anudip.placement_management_system.dto.skill.SkillRequest;
import com.anudip.placement_management_system.dto.skill.SkillResponse;
import com.anudip.placement_management_system.entity.Skill;

import com.anudip.placement_management_system.exception.ResourceNotFound;
import com.anudip.placement_management_system.mapper.SkillMapper;
import com.anudip.placement_management_system.repository.SkillRepository;
import com.anudip.placement_management_system.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    public SkillServiceImpl(
            SkillRepository skillRepository,
            SkillMapper skillMapper) {

        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
    }

    @Override
    public SkillResponse createSkill(SkillRequest request) {


        Skill skill = skillMapper.toEntity(request);

        Skill savedSkill = skillRepository.save(skill);

        return skillMapper.toResponse(savedSkill);
    }

    @Override
    public SkillResponse getSkillById(Long id) {

        Skill skill = skillRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFound(
                                "Skill not found with id: " + id
                        )
                );

        return skillMapper.toResponse(skill);
    }

    @Override
    public List<SkillResponse> getAllSkills() {

        return skillRepository.findAll()
                .stream()
                .map(skillMapper::toResponse)
                .toList();
    }

    @Override
    public SkillResponse updateSkill(
            Long id,
            SkillRequest request) {

        Skill skill = skillRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFound(
                                "Skill not found with id: " + id
                        )
                );



        skillMapper.updateEntity(skill, request);

        Skill updatedSkill = skillRepository.save(skill);

        return skillMapper.toResponse(updatedSkill);
    }

    @Override
    public void deleteSkill(Long id) {

        Skill skill = skillRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFound(
                                "Skill not found with id: " + id
                        )
                );

        skillRepository.delete(skill);
    }
}