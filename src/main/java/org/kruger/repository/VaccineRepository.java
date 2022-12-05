package org.kruger.repository;

import java.util.List;

import org.kruger.model.Employee;
import org.kruger.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

	List<Vaccine> findByName(String name);

	@Query(nativeQuery = true, value = "SELECT * FROM KRUG_VACCINE WHERE VACC_DATE BETWEEN TO_TIMESTAMP(CONCAT (?1,' 00:00:00'), 'YYYY-MM-DD HH24:MI:SS') AND TO_TIMESTAMP(CONCAT (?2,' 23:59:59'), 'YYYY-MM-DD HH24:MI:SS')")
	List<Vaccine> findByRangeDates(String dateStart, String dateEnd);
	
	void removeByEmployee(Employee employee);

}
