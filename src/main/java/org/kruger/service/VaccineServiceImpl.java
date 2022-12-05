package org.kruger.service;

import org.kruger.model.Employee;
import org.kruger.model.Vaccine;
import org.kruger.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VaccineServiceImpl implements VaccineService {
	
	@Autowired
	VaccineRepository vaccineRepository;

	@Override
	public void saveVaccine(Vaccine vaccine) {
		vaccineRepository.save(vaccine);
		
	}

	@Transactional
	@Override
	public void removeByEmployee(Employee employee) {
		vaccineRepository.removeByEmployee(employee);		
	}

}
