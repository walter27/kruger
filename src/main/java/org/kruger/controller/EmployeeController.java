package org.kruger.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.kruger.model.Employee;
import org.kruger.model.Role;
import org.kruger.model.UserKruger;
import org.kruger.service.EmployeeService;
import org.kruger.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	VaccineService vaccineService;

	@PostMapping("/save")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
		Map<String, Object> response = new HashMap<>();
		try {
			employeeService.addEmployee(employee);
			response.put("state", "ok");
			response.put("message", "registered employee");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("state", "error");
			response.put("message", "internal server error");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/update")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
		Map<String, Object> response = new HashMap<>();
		try {
			Optional<Employee> employeeOptional = employeeService.findById(employee.getId());
			if (employeeOptional.isPresent()) {
				employeeService.addEmployee(employee);
				response.put("state", "ok");
				response.put("message", "employee updated");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			} else {
				response.put("state", "ok");
				response.put("message", "employee not found");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("state", "error");
			response.put("message", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Optional<Employee> employee = employeeService.findById(id);
			if (employee.isPresent()) {
				vaccineService.removeByEmployee(employee.get());
				response.put("state", "ok");
				response.put("message", "deleted employee");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			} else {
				response.put("state", "ok");
				response.put("message", "employee not found");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("state", "error");
			response.put("message", "internal server error");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/list")
	public ResponseEntity<?> listEmployee() {
		Map<String, Object> response = new HashMap<>();
		List<Employee> listEmployee = new ArrayList<>();
		try {
			listEmployee = employeeService.listEmployee();
			response.put("state", "ok");
			response.put("message", "list of employees");
			response.put("employees", listEmployee);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("state", "error");
			response.put("message", "internal server error");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/list/vaccine")
	public ResponseEntity<?> listEmployeeVaccine(@RequestParam("name") String name) {
		Map<String, Object> response = new HashMap<>();
		List<Employee> listEmployee = new ArrayList<>();
		try {
			listEmployee = employeeService.listEmployeeByVaccine(name);
			if (listEmployee.size() > 0) {
				response.put("state", "ok");
				response.put("message", "list of employees vaccine with ".concat(name));
				response.put("employees", listEmployee);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			} else {
				response.put("state", "ok");
				response.put("message", "results not found");
				response.put("employees", listEmployee);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("state", "error");
			response.put("message", "internal server error");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/list/vaccine/date")
	public ResponseEntity<?> listEmployeeByVaccineAndDate(
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
		Map<String, Object> response = new HashMap<>();
		List<Employee> listEmployee = new ArrayList<>();
		try {
			listEmployee = employeeService.listEmployeeByVaccineAndDate(startDate, endDate);
			if (listEmployee.size() > 0) {
				response.put("state", "ok");
				response.put("message", "list of employees");
				response.put("employees", listEmployee);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			} else {
				response.put("state", "ok");
				response.put("message", "results not found");
				response.put("employees", listEmployee);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("state", "error");
			response.put("message", "internal server error");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/list/vaccine/state")
	public ResponseEntity<?> listEmployeeState(@RequestParam("state") Boolean state) {
		Map<String, Object> response = new HashMap<>();
		List<Employee> listEmployee = new ArrayList<>();
		try {
			listEmployee = employeeService.findByStateVaccination(state);
			if (listEmployee.size() > 0) {
				response.put("state", "ok");
				response.put("message", "list of employes");
				response.put("employees", listEmployee);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			} else {
				response.put("state", "ok");
				response.put("message", "results not found");
				response.put("employees", listEmployee);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("state", "error");
			response.put("message", "internal server error");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/user")
	public ResponseEntity<?> generateUser(@RequestBody Map<String, Object> map) {
		Map<String, Object> response = new HashMap<>();
		Integer idEmployee = (Integer) map.get("idEmployee");
		String username = (String) map.get("username");
		String password = (String) map.get("password");
		Integer idRole = (Integer) map.get("idRole");
		try {
			Optional<Employee> employee = employeeService.findById(Long.valueOf(idEmployee));
			Optional<Role> role = employeeService.findRoleById(Long.valueOf(idRole));
			if (employee.isPresent()) {
				if (role.isPresent()) {
					UserKruger userKruger = employeeService.generateUser(username, password, role.get(),
							employee.get());
					userKruger.setPassword(null);
					response.put("state", "ok");
					response.put("message", "user registered");
					response.put("user", userKruger);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
				} else {
					response.put("state", "ok");
					response.put("message", "role not found");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
				}
			} else {
				response.put("state", "ok");
				response.put("message", "employee not found");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("state", "error");
			response.put("message", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
	}
}
