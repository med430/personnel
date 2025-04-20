package gl2.example.personnel.dto;

import gl2.example.personnel.model.Skill;

public class SkillDTO {
    private Long id;
    private String name;
    private String level;

    public SkillDTO(Skill skill) {
        this.id = skill.getId();
        this.name = skill.getName();
        this.level = skill.getLevel().name();
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getLevel() {
        return level;
    }
}
