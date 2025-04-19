package gl2.example.personnel.service;


import gl2.example.personnel.model.Employee;
import gl2.example.personnel.model.Skill;
import gl2.example.personnel.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addSkillToEmployee(Long employeeId, Skill skill) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ConfigDataResourceNotFoundException("Employé non trouvé"));
        Skill skill = new Skill();
        skill.setName()
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
