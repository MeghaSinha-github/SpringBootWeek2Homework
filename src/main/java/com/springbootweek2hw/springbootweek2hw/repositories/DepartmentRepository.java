package com.springbootweek2hw.springbootweek2hw.repositories;

import com.springbootweek2hw.springbootweek2hw.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

}
