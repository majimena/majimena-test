package org.majimena.test.web.rest;

import mockit.Mocked;
import mockit.NonStrictExpectations;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.majimena.test.Application;
import org.majimena.test.domain.Project;
import org.majimena.test.domain.project.ProjectCriteria;
import org.majimena.test.service.ProjectService;
import org.majimena.test.web.rest.util.PaginationUtil;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProjectResource REST controller.
 *
 * @see ProjectResource
 */
@RunWith(Enclosed.class)
public class ProjectResourceTest {

    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION = "UPDATED_TEXT";

    private static final Long DEFAULT_OWNER = 0L;
    private static final Long UPDATED_OWNER = 1L;

    @RunWith(SpringJUnit4ClassRunner.class)
    @SpringApplicationConfiguration(classes = Application.class)
    @WebAppConfiguration
    public static class PostTest {

        private MockMvc mockMvc;

        @Inject
        private ProjectResource sut;

        @Mocked
        private ProjectService projectService;

        @Before
        public void setup() {
            mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
            sut.setProjectService(projectService);
        }

        @Test
        public void createProject() throws Exception {
            final Project testData = Project.builder().name(DEFAULT_NAME).description(DEFAULT_DESCRIPTION).build();
            new NonStrictExpectations() {{
                projectService.saveProject(testData);
                result = Optional.of(testData.builder().id(1L).build());
            }};

            mockMvc.perform(post("/api/projects")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(testData)))
                .andDo(print())
                .andExpect(status().isCreated());
        }

        @Test
        public void checkNameIsRequired() throws Exception {
            final Project testData = Project.builder().description(DEFAULT_DESCRIPTION).build();

            mockMvc.perform(post("/api/projects")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(testData)))
                .andDo(print())
                .andExpect(status().isBadRequest());
        }

        @Test
        public void checkDescriptionIsRequired() throws Exception {
            final Project testData = Project.builder().name(DEFAULT_NAME).build();

            mockMvc.perform(post("/api/projects")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(testData)))
                .andDo(print())
                .andExpect(status().isBadRequest());
        }
    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @SpringApplicationConfiguration(classes = Application.class)
    @WebAppConfiguration
    public static class GetListTest {

        private MockMvc mockMvc;

        @Inject
        private ProjectResource sut;

        @Mocked
        private ProjectService projectService;

        @Before
        public void setup() {
            mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
            sut.setProjectService(projectService);
        }

        @Test
        public void getAllProjects() throws Exception {
            final Project testData1 = Project.builder().id(1L).name(DEFAULT_NAME).description(DEFAULT_DESCRIPTION).build();
            final Project testData2 = Project.builder().id(2L).name(DEFAULT_NAME).description(DEFAULT_DESCRIPTION).build();
            final Pageable pageable = PaginationUtil.generatePageRequest(1, 1);
            new NonStrictExpectations() {{
                projectService.getProjects(new ProjectCriteria(), pageable);
                result = new PageImpl(Arrays.asList(testData1, testData2), pageable, 2);
            }};

            mockMvc.perform(get("/api/projects")
                .param("page", "1")
                .param("per_page", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"));
//                .andExpect(jsonPath("$.[*].id").value(hasItem(1)))
//                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
//                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
        }
    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @SpringApplicationConfiguration(classes = Application.class)
    @WebAppConfiguration
    public static class GetTest {

        private MockMvc mockMvc;

        @Inject
        private ProjectResource sut;

        @Mocked
        private ProjectService projectService;

        @Before
        public void setup() {
            mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
            sut.setProjectService(projectService);
        }

        @Test
        public void getProject() throws Exception {
            new NonStrictExpectations() {{
                projectService.getProjectById(1L);
                result = Optional.of(Project.builder().id(1L).name(DEFAULT_NAME).description(DEFAULT_DESCRIPTION).build());
            }};

            mockMvc.perform(get("/api/projects/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(DEFAULT_NAME)))
                .andExpect(jsonPath("$.description", is(DEFAULT_DESCRIPTION)));
        }

        @Test
        public void getNonExistingProject() throws Exception {
            new NonStrictExpectations() {{
                projectService.getProjectById(Long.MAX_VALUE);
                result = Optional.empty();
            }};

            mockMvc.perform(get("/api/projects/{id}", Long.MAX_VALUE))
                .andDo(print())
                .andExpect(status().isNotFound());
        }
    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @SpringApplicationConfiguration(classes = Application.class)
    @WebAppConfiguration
    public static class PutTest {

        private MockMvc mockMvc;

        @Inject
        private ProjectResource sut;

        @Mocked
        private ProjectService projectService;

        @Before
        public void setup() {
            mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
            sut.setProjectService(projectService);
        }

        @Test
        public void updateProject() throws Exception {
            final Project testData = Project.builder().id(1L).name(DEFAULT_NAME).description(DEFAULT_DESCRIPTION).build();
            new NonStrictExpectations() {{
                projectService.updateProject(testData);
                result = Optional.of(testData);
            }};

            mockMvc.perform(put("/api/projects")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(testData)))
                .andDo(print())
                .andExpect(status().isOk());
        }
    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @SpringApplicationConfiguration(classes = Application.class)
    @WebAppConfiguration
    public static class DeleteTest {

        private MockMvc mockMvc;

        @Inject
        private ProjectResource sut;

        @Mocked
        private ProjectService projectService;

        @Before
        public void setup() {
            mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
            sut.setProjectService(projectService);
        }

        @Test
        public void deleteProject() throws Exception {
            final Project testData = Project.builder().id(1L).name(DEFAULT_NAME).description(DEFAULT_DESCRIPTION).build();
            new NonStrictExpectations() {{
                projectService.updateProject(testData);
                result = Optional.of(testData);
            }};

            mockMvc.perform(delete("/api/projects/{id}", 1L)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
        }
    }
}
