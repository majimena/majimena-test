package org.majimena.test.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.majimena.test.Application;
import org.majimena.test.domain.Project;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @see ProjectRepository
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ProjectRepositoryIT {

    @Inject
    private ProjectRepository sut;

    @Before
    public void initTest() {
    }

    @Test
    @Transactional
    public void sampleTest() throws Exception {
        // FIXME 拡張して実装したメソッドのみテストする
        // Get the table size before insert in database
        int databaseSizeBeforeCreate = sut.findAll().size();

        // Create the Project
        sut.save(new Project(null, "name", "description", null));

        // Validate the Project in the database
        List<Project> projects = sut.findAll();
        assertThat(projects).hasSize(databaseSizeBeforeCreate + 1);
        Project testProject = projects.get(projects.size() - 1);
        assertThat(testProject.getName()).isEqualTo("name");
        assertThat(testProject.getDescription()).isEqualTo("description");
        assertThat(testProject.getOwner()).isEqualTo(null);
    }

}
