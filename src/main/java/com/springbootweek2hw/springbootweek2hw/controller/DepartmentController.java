package com.springbootweek2hw.springbootweek2hw.controller;

import com.springbootweek2hw.springbootweek2hw.dto.DepartmentDTO;
import com.springbootweek2hw.springbootweek2hw.exceptions.ResourceNotFoundException;
import com.springbootweek2hw.springbootweek2hw.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments()
    {
           return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable(name="departmentId") Long deptId)
    {
        Optional<DepartmentDTO> departmentDTO=departmentService.getDepartmentById(deptId);
       return departmentDTO.map(departmentDTO1 -> ResponseEntity.ok(departmentDTO1))
               .orElseThrow(()-> new ResourceNotFoundException("Employee Not Found with id " +deptId));
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createNewDepartment(@RequestBody @Valid DepartmentDTO dept)
    {
        DepartmentDTO savedDept= departmentService.createNewDepartments(dept);
       return new ResponseEntity<>(savedDept, HttpStatus.CREATED);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartmentById(@PathVariable(name="departmentId") Long id, @RequestBody @Valid DepartmentDTO dept)
    {
        return ResponseEntity.ok(departmentService.updateDepartmentById(id,dept));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable(name="departmentId") Long id)
    {
        boolean gotDeleted=departmentService.deleteDepartmentById(id);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> partialUpdateDepartment(@RequestBody Map<String,Object> updates, @PathVariable(name="departmentId") Long id)
        {
            DepartmentDTO departmentDTO=departmentService.partialUpdateDepartmentById(updates,id);
            if(departmentDTO==null) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(departmentDTO);
        }
    }

