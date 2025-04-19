package gl2.example.personnel.dto;

import gl2.example.personnel.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeDTO {
    private Long id;
    private String name;
    private String position;
    private double salary;
    private List<SkillDTO> skills;

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.position = employee.getPosition();
        this.skills = employee.getSkills().stream().map(SkillDTO::new).collect(Collectors.toList());
    }

    public static Optional<EmployeeDTO> from(Optional<Employee> employee) {
        return employee.map(EmployeeDTO::new);
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPosition() {
        return position;
    }
    public double getSalary() {
        return salary;
    }
    public List<SkillDTO> getSkills() {
        return skills;
    }
}
