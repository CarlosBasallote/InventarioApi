package org.zerhusen.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerhusen.model.security.Authority;
import org.zerhusen.model.security.AuthorityName;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	
	Authority findFirstByName(AuthorityName name);

}
