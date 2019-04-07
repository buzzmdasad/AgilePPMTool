package com.ppmtool.ppmtool.services;

import com.ppmtool.ppmtool.domain.Project;
import com.ppmtool.ppmtool.exceptions.ProjectIdException;
import com.ppmtool.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
   private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch(Exception e){
            throw new ProjectIdException("Project Id '"+project.getProjectIdentifier().toUpperCase()+"' already exist");
        }
    }
    public Project findProjectById(String projectId ){
        Project project=projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(null==project){
            throw new ProjectIdException("Project does not exist");
        }
        return project;
    }
    public Iterable<Project>findAllProjects(){
        return projectRepository.findAll();
    }
    public void deleteProjectByIdentifier(String projectId){
         Project project= projectRepository.findByProjectIdentifier(projectId);
        if(project==null){
            throw new ProjectIdException("Can not delete '"+projectId+"'. This project id does not exist");
        }
        projectRepository.delete(project);
    }
}
