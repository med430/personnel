package gl2.example.personnel.controller;

import gl2.example.personnel.dto.EmployeeDTO;
import gl2.example.personnel.dto.SkillDTO;
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

    @GetMapping
    public ResponseEntity<List<SkillDTO>> getSkills(
            @PathVariable Long employeeId
    ) {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId).getSkills());
    }

    @GetMapping("/{skillId}")
    public ResponseEntity<SkillDTO> getSkill(
            @PathVariable Long employeeId,
            @PathVariable Long skillId
    ) {
        return ResponseEntity.ok(employeeService.getSkillById(employeeId, skillId));
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> addSkill(
            @PathVariable Long employeeId,
            @Valid @RequestBody SkillRequest request) {
        EmployeeDTO response = employeeService.addSkillToEmployee(employeeId, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<EmployeeDTO> updateAllSkills(
            @PathVariable Long employeeId,
            @Valid @RequestBody List<SkillRequest> skillRequests) {

        EmployeeDTO updatedEmployee = employeeService.updateEmployeeSkills(employeeId, skillRequests);
        if(updatedEmployee!=null) {
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{skillId}")
    public ResponseEntity<SkillDTO> updateSkill(
            @PathVariable Long employeeId,
            @PathVariable Long skillId,
            @Valid @RequestBody SkillRequest skillRequest) {

        SkillDTO updatedSkill = employeeService.updateSkill(employeeId, skillId, skillRequest);
        return ResponseEntity.ok(updatedSkill);
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<SkillDTO> deleteSkill(
            @PathVariable Long employeeId,
            @PathVariable Long skillId
    ) {
        SkillDTO removedSkill = employeeService.deleteEmployeeSkill(employeeId, skillId);
        return ResponseEntity.ok(removedSkill);
    }
}
