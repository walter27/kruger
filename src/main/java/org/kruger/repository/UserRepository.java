package org.kruger.repository;

import org.kruger.model.UserKruger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<UserKruger, Long> {

	public UserKruger findByUsername(String username);
}
