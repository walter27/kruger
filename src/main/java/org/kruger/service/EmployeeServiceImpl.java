package org.kruger.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.kruger.model.Employee;
import org.kruger.model.Role;
import org.kruger.model.UserKruger;
import org.kruger.model.Vaccine;
import org.kruger.repository.EmployeeRepository;
import org.kruger.repository.RolRepository;
import org.kruger.repository.UserRepository;
import org.kruger.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	VaccineRepository vaccineRepository;

	@Autowired
	RolRepository rolRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public List<Employee> listEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public void addEmployee(Employee employee) {

		employeeRepository.save(employee);
	}

	@Override
	public Optional<Employee> findById(Long id) {
		return employeeRepository.findById(id);
	}

	@Override
	public List<Employee> listEmployeeByVaccine(String name) {
		List<Vaccine> listaVaccine = new ArrayList<>();
		List<Employee> listEmployee = new ArrayList<>();
		try {
			listaVaccine = vaccineRepository.findByName(name);
			if (listaVaccine.size() > 0) {
				listEmployee = listaVaccine.stream().map(Vaccine::getEmployee).collect(Collectors.toList());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listEmployee;
	}

	@Override
	public List<Employee> listEmployeeByVaccineAndDate(Date dateStart, Date dateEnd) {
		List<Vaccine> listaVaccine = new ArrayList<>();
		List<Employee> listEmployee = new ArrayList<>();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		try {
			listaVaccine = vaccineRepository.findByRangeDates(formater.format(dateStart), formater.format(dateEnd));
			if (listaVaccine.size() > 0) {
				listEmployee = listaVaccine.stream().map(Vaccine::getEmployee).collect(Collectors.toList());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listEmployee;
	}

	@Override
	public List<Employee> findByStateVaccination(Boolean stateVaccination) {
		return employeeRepository.findByStateVaccination(stateVaccination);
	}

	@Override
	public UserKruger generateUser(String username, String password, Role role, Employee employee) {
		UserKruger userKruger = new UserKruger();
		List<Role> listRole = new ArrayList<>();
		listRole.add(role);
		try {
			userKruger.setEnabled(true);
			userKruger.setUsername(username);
			userKruger.setPassword(passwordEncoder.encode(password));
			userKruger.setRoles(listRole);
			userKruger.setEmployee(employee);
			userRepository.save(userKruger);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userKruger;
	}

	@Override
	public Optional<Role> findRoleById(Long id) {
		return rolRepository.findById(id);
	}

}
