package gl2.example.personnel.dto;

import gl2.example.personnel.model.Skill;

public class SkillDTO {
    private String name;
    private String level;

    public SkillDTO(Skill skill) {
        this.name = skill.getName();
        this.level = skill.getLevel().name();
    }

    public String getName() {
        return name;
    }
    public String getLevel() {
        return level;
    }
}
