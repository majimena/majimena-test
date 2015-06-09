package org.majimena.test.service.impl;

import org.majimena.test.domain.Project;
import org.majimena.test.domain.User;
import org.majimena.test.domain.project.ProjectCriteria;
import org.majimena.test.repository.ProjectRepository;
import org.majimena.test.repository.UserRepository;
import org.majimena.test.security.SecurityUtils;
import org.majimena.test.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by todoken on 2015/06/06.
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Inject
    protected ProjectRepository projectRepository;

    @Inject
    protected UserRepository userRepository;

    @Override
    public Optional<Project> getProjectById(Long projectId) {
        Optional<Project> one = Optional.ofNullable(projectRepository.findOne(projectId));
        return one;
    }

    @Override
    public Page<Project> getProjects(ProjectCriteria criteria, Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public Optional<Project> saveProject(Project project) {
        String login = SecurityUtils.getCurrentLogin();
        Optional<User> owner = userRepository.findOneByLogin(login);
        owner.ifPresent(u -> {
            project.setOwner(u);
            projectRepository.save(project);
        });
        return Optional.of(project);
    }

    @Override
    public Optional<Project> updateProject(Project project) {
        Optional<Project> one = getProjectById(project.getId());
        one.ifPresent(p -> {
            p.setName(project.getName());
            p.setDescription(project.getDescription());
            projectRepository.save(p);
        });
        return one;
    }

    @Override
    public void deleteProject(Long projectId) {
        projectRepository.delete(projectId);
    }
}
