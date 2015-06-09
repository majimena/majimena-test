package org.majimena.test.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.majimena.test.Application;
import org.majimena.test.domain.Project;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @see ProjectRepository
 */
@RunWith(Enclosed.class)
public class ProjectRepositoryIT {

    @WebAppConfiguration
    @SpringApplicationConfiguration(classes = Application.class)
    public static class SampleTest extends AbstractDBUnitTest {

        @Inject
        private ProjectRepository sut;

        @Test
        @DatabaseSetup("classpath:/testdata/project.xml")
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

}
