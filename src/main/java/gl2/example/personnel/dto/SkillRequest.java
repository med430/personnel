package gl2.example.personnel.dto;

import gl2.example.personnel.model.enums.SkillLevel;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

public class SkillRequest {
    @NotBlank(message = "Le nom de la compétence est obligatoire")
    private String name;

    @NotNull
    private SkillLevel level;


    public SkillRequest(@NotNull String name, @NotNull SkillLevel level) {
        this.name = name;
        this.level = level;
    }


    // getters et setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public SkillLevel getLevel() {
        return level;
    }
    public void setLevel(SkillLevel level) {
        this.level = level;
    }
}
