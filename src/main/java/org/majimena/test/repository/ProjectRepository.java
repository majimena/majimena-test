package org.majimena.test.repository;

import org.majimena.test.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Project entity.
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
