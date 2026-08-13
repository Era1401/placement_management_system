package com.anudip.placement_management_system.service;

import com.anudip.placement_management_system.dto.skill.SkillRequest;
import com.anudip.placement_management_system.dto.skill.SkillResponse;

import java.util.List;

public interface SkillService {

    SkillResponse createSkill(SkillRequest request);

    SkillResponse getSkillById(Long id);

    List<SkillResponse> getAllSkills();

    SkillResponse updateSkill(Long id, SkillRequest request);

    void deleteSkill(Long id);
}