package org.majimena.test.service;

import org.majimena.test.domain.Project;
import org.majimena.test.domain.project.ProjectCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Created by todoken on 2015/06/06.
 */
public interface ProjectService {

    Optional<Project> findProjectById(Long projectId);

    Page<Project> findProjects(ProjectCriteria criteria, Pageable pageable);

    Optional<Project> saveProject(Project project);

    Optional<Project> updateProject(Project project);

}
