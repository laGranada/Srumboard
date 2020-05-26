/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.scrumboard.service.stub;

import java.util.ArrayList;
import java.util.List;
import de.scrumboard.entity.Employee;
import de.scrumboard.entity.Project;
import de.scrumboard.entity.Status;
import de.scrumboard.entity.Task;
import de.scrumboard.service.ScrumboardService;

/**
 *
 * @author MaggieSaurusRex
 */
public class ScrumboardDaoStub implements ScrumboardService{
    
    private List<Employee> employees = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    
    /**
     * Generates two projects, each with three employees and three tasks 
     */
    public ScrumboardDaoStub() {
        // generates six employees and tasks
        for(int i = 1; i < 7 ; i++){
            employees.add(new Employee(i, 
                                    "Frida " + i, 
                                    "Kahlo " + i, 
                                    "Street " + i, 
                                    "city " + i, 
                                    "country" + i, 
                                    22348, 
                                    "username" + i, 
                                    "abCd" + i));
            
            tasks.add(new Task(i, 
                               "Task " + i, 
                               "description " + i,
                                Status.TO_DO));
        
        }//end for
        
        //generate two projects
        projects.add(new Project(1, 
                                 "Project 1", 
                                 employees.subList(0, 2), 
                                 tasks.subList(0, 2), 
                                 "description 1"));
        projects.add(new Project(1, 
                                 "Project 2", 
                                 employees.subList(3, 5), 
                                 tasks.subList(3, 5), 
                                 "description 2"));        
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employees;
    }

    @Override
    public Employee findEmployeeById(long id) {
        return employees.stream().filter(emp -> emp.getId() == id).findFirst().get();
    }

    @Override
    public Employee findEmployeeByUsername(String username) {
        return employees.stream().filter(emp -> emp.getUsername().equals(username)).findFirst().get();
    }

    @Override
    public List<Project> findAllProjects() {
        return projects;
    }

    @Override
    public Project findProjectById(long id) {
        return projects.stream().filter(p -> p.getId() == id).findFirst().get();
    }

    @Override
    public List<Task> findAllTasks() {
        return tasks;
    }

    @Override
    public Task findTaskById(long id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst().get();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return new Employee(employees.size() + 1, 
                                    employee.getFirstName(), 
                                    employee.getLastName(), 
                                    employee.getStreet(), 
                                    employee.getCity(), 
                                    employee.getCountry(),
                                    employee.getZipCode(), 
                                    employee.getUsername(), 
                                    employee.getPassword());
    }

    @Override
    public Task createTask(Task task) {
        return new Task(tasks.size() + 1, task.getName(), task.getDescription(), task.getStatus());
    }

    @Override
    public Project createProject(Project project) {
        return new Project(projects.size(), 
                           project.getName(), 
                           project.getEmployees(), 
                           project.getTasks(), 
                           project.getDescription());
    }

    @Override
    public void updateEmployee(Employee employee) {
        for(Employee e : employees){
            if(e.getId() == employee.getId()){
                e = employee;
            }
        }
    }

    @Override
    public void updateTask(Task task) {
        for(Task t : tasks){
            if(t.getId() == task.getId()){
                t = task;
            }
        }
    }

    @Override
    public void updateProject(Project project) {
        for(Project p : projects){
            if(p.getId() == project.getId()){
                p = project;
            }
        }
    }

    @Override
    public boolean deleteEmployee(long id) {
        for(Employee e : employees){
            if(e.getId() == id){
               return employees.remove(e);
            }
        }
        return false;
    }

    @Override
    public boolean deleteTask(long id) {
        for(Task t : tasks){
            if(t.getId() == id){
                return tasks.remove(t);
            }
        }
        return false;
    }

    @Override
    public boolean deleteProject(long id) {
        for(Project p : projects){
            if(p.getId() == id){
                return projects.remove(p);
            }
        }
        return false;
    }
    
}
