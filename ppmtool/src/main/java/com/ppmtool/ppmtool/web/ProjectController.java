package com.ppmtool.ppmtool.web;

import com.ppmtool.ppmtool.domain.Project;
import com.ppmtool.ppmtool.exceptions.ProjectIdException;
import com.ppmtool.ppmtool.services.MapValidationErrorService;
import com.ppmtool.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;
    @PostMapping("")
    public ResponseEntity<?>createNewProject(@Valid @RequestBody Project project, BindingResult result){
       ResponseEntity<?> errorMap=mapValidationErrorService.MapValidationEntity(result);
       if(errorMap!=null) return errorMap;
        Project project1=projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);

    }
    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId){
        Project project =projectService.findProjectById(projectId);
     return new ResponseEntity<Project>(project,HttpStatus.OK);
    }
    @GetMapping("/all")
    public Iterable<Project> getAllProjects(){
        return projectService.findAllProjects();
    }
   @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
       projectService.deleteProjectByIdentifier(projectId.toUpperCase());
       return new ResponseEntity<String>("Project Id '"+projectId+"' got deleted successfully",HttpStatus.OK);
   }
   /*@PostMapping("/{project}")
    public ResponseEntity<?> updateProject(@RequestBody Project project){
        Project project1=projectService.saveOrUpdateProject(project);
        return new ResponseEntity<String>("project '"+project.getProjectName()+"' updated successfully",HttpStatus.OK);
   }*/
}
