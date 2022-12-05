package org.kruger.repository;

import java.util.Optional;

import org.kruger.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Role, Long> {

	Optional<Role> findById(Long id);

}
