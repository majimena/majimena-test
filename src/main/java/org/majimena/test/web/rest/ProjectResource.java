package org.majimena.test.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.majimena.test.domain.Project;
import org.majimena.test.domain.project.ProjectCriteria;
import org.majimena.test.service.ProjectService;
import org.majimena.test.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Project.
 */
@RestController
@RequestMapping("/api")
public class ProjectResource {

    private final Logger log = LoggerFactory.getLogger(ProjectResource.class);

    @Inject
    private ProjectService projectService;

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * POST  /projects -> Create a new project.
     */
    @Timed
    @RequestMapping(value = "/projects", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@Valid @RequestBody Project project) {
        if (project.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new project cannot already have an ID").build();
        }

        projectService.saveProject(project);
        return ResponseEntity.created(URI.create("/api/projects/" + project.getId())).build();
    }

    /**
     * PUT  /projects -> Updates an existing project.
     */
    @Timed
    @RequestMapping(value = "/projects", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody Project project) {
        if (project.getId() == null) {
            return create(project);
        }

        projectService.saveProject(project);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /projects -> get all the projects.
     */
    @Timed
    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public ResponseEntity<List<Project>> getAll(@RequestParam(value = "page", required = false) Integer offset,
                                                @RequestParam(value = "per_page", required = false) Integer limit) throws URISyntaxException {
        Pageable pageable = PaginationUtil.generatePageRequest(offset, limit);
        Page<Project> page = projectService.getProjects(new ProjectCriteria(), pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/projects", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /projects/:id -> get the "id" project.
     */
    @Timed
    @RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
    public ResponseEntity<Project> get(@PathVariable Long id) {
        Optional<Project> one = projectService.getProjectById(id);
        return one.map(project -> new ResponseEntity<>(project, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /projects/:id -> delete the "id" project.
     */
    @Timed
    @RequestMapping(value = "/projects/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }
}
