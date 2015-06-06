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
    private ProjectRepository projectRepository;

    @Inject
    private UserRepository userRepository;

    @Override
    public Optional<Project> findProjectById(Long projectId) {
        Project one = projectRepository.findOne(projectId);
        Optional<Project> project = Optional.ofNullable(one);
//        project.ifPresent(project1 -> {
//            User user = userRepository.findOne(project1.getOwner());
//            project1.setOwner(user);
//        });
        return project;
    }

    @Override
    public Page<Project> findProjects(ProjectCriteria criteria, Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public Optional<Project> saveProject(Project project) {
        // ログインユーザーIDを取得
        String login = SecurityUtils.getCurrentLogin();
        Optional<User> owner = userRepository.findOneByLogin(login);
        project.setOwner(owner.get().getId());

        // プロジェクトを保存
        Project save = projectRepository.save(project);
        return Optional.of(save);
    }

}
