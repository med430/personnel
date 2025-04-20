package gl2.example.personnel.service;


import gl2.example.personnel.dto.EmployeeDTO;
import gl2.example.personnel.dto.EmployeeRequest;
import gl2.example.personnel.dto.SkillDTO;
import gl2.example.personnel.dto.SkillRequest;
import gl2.example.personnel.exception.ResourceNotFoundException;
import gl2.example.personnel.model.Employee;
import gl2.example.personnel.model.Skill;
import gl2.example.personnel.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeDTO createEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setPosition(request.getPosition());
        employee.setSalary(request.getSalary());

        request.getSkills().forEach(skillRequest -> {
            Skill skill = new Skill();
            skill.setName(skillRequest.getName());
            skill.setLevel(skillRequest.getLevel());
            employee.addSkill(skill);
        });

        return new EmployeeDTO(employeeRepository.save(employee));
    }

    public EmployeeDTO addSkillToEmployee(Long employeeId, SkillRequest request) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employé non trouvé"));

        Skill skill = new Skill();
        skill.setName(request.getName());
        skill.setLevel(request.getLevel());
        employee.addSkill(skill);

        return new EmployeeDTO(employeeRepository.save(employee));
    }

    @Transactional
    public EmployeeDTO updateEmployee(Long id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        // Mise à jour des champs
        employee.setName(request.getName());
        employee.setPosition(request.getPosition());
        employee.setSalary(request.getSalary());

        // Mise à jour des compétences
        updateEmployeeSkills(employee, request.getSkills());

        return new EmployeeDTO(employeeRepository.save(employee));
    }

    private void updateEmployeeSkills(Employee employee, List<SkillRequest> skillRequests) {
        // Créer un Map pour un accès plus efficace
        Map<String, SkillRequest> requestMap = skillRequests.stream()
                .collect(Collectors.toMap(SkillRequest::getName, Function.identity()));

        // Supprimer les compétences absentes du request
        employee.getSkills().removeIf(skill -> !requestMap.containsKey(skill.getName()));

        // Mettre à jour/ajouter les compétences
        requestMap.forEach((name, skillRequest) -> {
            Optional<Skill> existingSkill = employee.getSkills().stream()
                    .filter(s -> s.getName().equals(name))
                    .findFirst();

            if (existingSkill.isPresent()) {
                // Mise à jour de la compétence existante
                Skill skill = existingSkill.get();
                skill.setLevel(skillRequest.getLevel());
            } else {
                // Ajout d'une nouvelle compétence
                Skill newSkill = new Skill();
                newSkill.setName(name);
                newSkill.setLevel(skillRequest.getLevel());
                employee.addSkill(newSkill);
            }
        });
    }

    public EmployeeDTO updateEmployeeSkills(Long employeeId, List<SkillRequest> skillRequests) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isPresent()) {
            this.updateEmployeeSkills(employee.get(), skillRequests);
            return new EmployeeDTO(employeeRepository.save(employee.get()));
        }
        return null;
    }

    @Transactional
    public SkillDTO updateSkill(Long employeeId, Long skillId, SkillRequest request) {
        // Vérifier que l'employé existe
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        // Trouver la compétence spécifique
        Skill skill = employee.getSkills().stream()
                .filter(s -> s.getId().equals(skillId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Skill", "id", skillId));

        // Mise à jour
        skill.setName(request.getName());
        skill.setLevel(request.getLevel());

        return new SkillDTO(skill);
    }

    public SkillDTO getSkillById(Long employeeId, Long skillId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        Optional<Skill> skill = Optional.ofNullable(employee.get().getSkills().stream().filter(s -> s.getId().equals(skillId)).findFirst().orElse(null));
        return new SkillDTO(skill.get());
    }

    @Transactional
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(EmployeeDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public EmployeeDTO getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(EmployeeDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Employé non trouvé"));
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employé non trouvé"));
        employeeRepository.delete(employee);
    }
}
