package com.springbootweek2hw.springbootweek2hw.services;

import com.springbootweek2hw.springbootweek2hw.dto.DepartmentDTO;
import com.springbootweek2hw.springbootweek2hw.entities.DepartmentEntity;
import com.springbootweek2hw.springbootweek2hw.exceptions.ResourceNotFoundException;
import com.springbootweek2hw.springbootweek2hw.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }


    public List<DepartmentDTO> getAllDepartments()
    {
        List<DepartmentEntity> departmentEntities= departmentRepository.findAll();
        return departmentEntities
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO createNewDepartments(DepartmentDTO inputDepartment) {
        DepartmentEntity toBeSavedDepartment= modelMapper.map(inputDepartment,DepartmentEntity.class);
        DepartmentEntity toSavedDepartment= departmentRepository.save(toBeSavedDepartment);
        return  modelMapper.map(toSavedDepartment,DepartmentDTO.class);
    }

    public Optional<DepartmentDTO> getDepartmentById(Long deptId) {
        Optional<DepartmentEntity> deptEntity = departmentRepository.findById(deptId);
        return deptEntity.map(deptEntity1-> modelMapper.map(deptEntity1,DepartmentDTO.class));
    }

    public DepartmentDTO updateDepartmentById(Long id, DepartmentDTO dept) {
        isExistsByDepartmentId(id);
        DepartmentEntity deptEntity = modelMapper.map(dept, DepartmentEntity.class);
        deptEntity.setId(id);
        DepartmentEntity savedEntity= departmentRepository.save(deptEntity);
        return modelMapper.map(savedEntity,DepartmentDTO.class);
    }
    public boolean deleteDepartmentById(Long id) {
        isExistsByDepartmentId(id);
        departmentRepository.deleteById(id);
        return true;
    }
    public DepartmentDTO partialUpdateDepartmentById(Map<String, Object> updates, Long id) {
        isExistsByDepartmentId(id);
        DepartmentEntity departmentEntity= departmentRepository.findById(id).get();
        updates.forEach((field, value)->{
                Field fieldToBeUpdated = ReflectionUtils.findRequiredField(DepartmentEntity.class, field);
                fieldToBeUpdated.setAccessible(true);
                ReflectionUtils.setField(fieldToBeUpdated,departmentEntity, value);
            });
            return modelMapper.map(departmentRepository.save(departmentEntity), DepartmentDTO.class);
    }
    private void isExistsByDepartmentId(Long id) {
        boolean exists=  departmentRepository.existsById(id);
        if(!exists) throw new ResourceNotFoundException("Department Not Found with id " +id);
    }


}
