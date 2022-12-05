package org.kruger.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.kruger.model.Employee;
import org.kruger.model.Role;
import org.kruger.model.UserKruger;

public interface EmployeeService {

	List<Employee> listEmployee();

	void addEmployee(Employee empployee);

	Optional<Employee> findById(Long id);

	List<Employee> listEmployeeByVaccine(String name);

	List<Employee> listEmployeeByVaccineAndDate(Date startDate, Date endDate);

	List<Employee> findByStateVaccination(Boolean stateVaccination);

	UserKruger generateUser(String username, String password, Role role, Employee employee);

	Optional<Role> findRoleById(Long id);

}
