package com.anudip.placement_management_system.mapper;

import com.anudip.placement_management_system.dto.skill.SkillRequest;
import com.anudip.placement_management_system.dto.skill.SkillResponse;
import com.anudip.placement_management_system.entity.Skill;
import org.springframework.stereotype.Component;

@Component
public class SkillMapper {

    public Skill toEntity(SkillRequest request) {

        Skill skill = new Skill();

        skill.setName(request.getName());

        return skill;
    }

    public SkillResponse toResponse(Skill skill) {

        SkillResponse response = new SkillResponse();

        response.setId(skill.getId());
        response.setName(skill.getName());

        return response;
    }

    public void updateEntity(
            Skill skill,
            SkillRequest request) {

        skill.setName(request.getName());
    }
}