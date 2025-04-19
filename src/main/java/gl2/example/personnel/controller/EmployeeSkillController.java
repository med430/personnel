package gl2.example.personnel.controller;

import gl2.example.personnel.dto.EmployeeDTO;
import gl2.example.personnel.dto.SkillRequest;
import gl2.example.personnel.model.Employee;
import gl2.example.personnel.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/employees/{employeeId}/skills")
public class EmployeeSkillController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> addSkill(
            @PathVariable Long employeeId,
            @Valid @RequestBody SkillRequest request) {
        EmployeeDTO response = employeeService.addSkillToEmployee(employeeId, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<EmployeeDTO> updateSkill(
            @PathVariable Long employeeId,
            @Valid @RequestBody SkillRequest request
    ) {
        EmployeeDTO response = employeeService.getEmployeeById(employeeId).getSkills();
    }
}
