package gl2.example.personnel.model;

import gl2.example.personnel.model.enums.SkillLevel;
import jakarta.persistence.*;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private SkillLevel level;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
