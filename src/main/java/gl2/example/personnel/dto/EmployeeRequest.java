package gl2.example.personnel.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.*;

public class EmployeeRequest {
    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères")
    private String name;

    @NotBlank(message = "Le poste est obligatoire")
    private String position;

    @Positive(message = "Le salaire doit être positif")
    private double salary;

    @Valid
    private List<SkillRequest> skills;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public List<SkillRequest> getSkills() {
        return skills;
    }
    public void setSkills(List<SkillRequest> skills) {
        this.skills = skills;
    }
}
