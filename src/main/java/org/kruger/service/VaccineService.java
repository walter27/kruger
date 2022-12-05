package org.kruger.service;

import org.kruger.model.Employee;
import org.kruger.model.Vaccine;

public interface VaccineService {

	void saveVaccine(Vaccine vaccine);
	
	void removeByEmployee(Employee employee);
}
