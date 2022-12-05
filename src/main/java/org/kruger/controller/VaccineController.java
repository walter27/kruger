package org.kruger.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.kruger.model.Employee;
import org.kruger.model.Vaccine;
import org.kruger.service.EmployeeService;
import org.kruger.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vaccine")
public class VaccineController {

	@Autowired
	VaccineService vaccineService;
	
	@Autowired
	EmployeeService employeeService;

	@PostMapping("/save")
	public ResponseEntity<?> addVaccine(@RequestBody Vaccine vaccine) {
		Map<String, Object> response = new HashMap<>();
		try {
			Optional<Employee> employee = employeeService.findById(vaccine.getEmployee().getId());
			if (employee.isPresent()) {
				vaccineService.saveVaccine(vaccine);
				employeeService.addEmployee(vaccine.getEmployee());
				response.put("state", "ok");
				response.put("message", "registered vaccine");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}else {
				response.put("state", "error");
				response.put("message", "employee no found");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.put("state", "error");
			response.put("message", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
