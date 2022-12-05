package org.kruger.repository;

import java.util.List;
import java.util.Optional;

import org.kruger.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findById(Long id);

	List<Employee> findByStateVaccination(Boolean stateVaccination);

}
