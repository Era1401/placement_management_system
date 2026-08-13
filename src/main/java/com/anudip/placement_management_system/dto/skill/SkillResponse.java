package com.anudip.placement_management_system.dto.skill;

public class SkillResponse {

    private Long id;
    private String name;

    public SkillResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}