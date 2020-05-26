/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.scrumboard.entity;

import java.util.List;

/**
 *
 * @author MaggieSaurusRex
 */
public class Project {
    
     private long id;
     
     private String name;
     
     private List<Employee> members;
     
     private List<Task> tasks;
     
     private String description;

    public Project() {
    }
    
    /**
     * Only for Dao stub
     * @param id
     * @param name
     * @param members
     * @param tasks
     * @param description
     */
    public Project(long id, String name, List<Employee> members, List<Task> tasks, String description) {
        this.id = id;
        this.name = name;
        this.members = members;
        this.tasks = tasks;
        this.description = description;
    }
    
    
     
    public boolean addMember(Employee member){
        return members.add(member);
    }
    
    public boolean addTask(Task task){
       return tasks.add(task);
    }
    
    //<editor-fold defaultstate="collapsed" desc="hashCode / equals">
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Project other = (Project) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    //</editor-fold>  
    
    //<editor-fold defaultstate="collapsed" desc="Getter / Setter">
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return members;
    }

    public void setEmployees(List<Employee> employees) {
        this.members = employees;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //</editor-fold> 

    @Override
    public String toString() {
        return name;
    }
    
}
