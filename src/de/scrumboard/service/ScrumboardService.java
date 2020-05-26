/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.scrumboard.service;

import java.util.List;
import de.scrumboard.entity.Employee;
import de.scrumboard.entity.Project;
import de.scrumboard.entity.Task;

/**
 * Interface that defines persistence dependant actions.
 * 
 * @author MaggieSaurusRex
 */
public interface ScrumboardService {
    
    public List<Employee> findAllEmployees();
    
    public Employee findEmployeeById(long id);
    
    public Employee findEmployeeByUsername(String username);
    
    public List<Project> findAllProjects();
    
    public Project findProjectById(long id);
    
    public List<Task> findAllTasks();
    
    public Task findTaskById(long id);
    
    public Employee createEmployee(Employee employee);
    
    public Task createTask(Task task);
    
    public Project createProject(Project project);
    
    public void updateEmployee(Employee employee);
    
    public void updateTask(Task task);
    
    public void updateProject(Project project);
    
     public boolean deleteEmployee(long id);
    
    public boolean deleteTask(long id);
    
    public boolean deleteProject(long id);
    

}
