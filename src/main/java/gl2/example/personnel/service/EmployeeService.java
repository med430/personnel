package gl2.example.personnel.service;


import gl2.example.personnel.dto.EmployeeDTO;
import gl2.example.personnel.dto.EmployeeRequest;
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
import java.util.Optional;
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
