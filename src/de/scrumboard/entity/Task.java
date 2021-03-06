/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.scrumboard.entity;

import java.io.Serializable;

/**
 *
 * @author MaggieSaurusRex
 */
public class Task implements Serializable {
    private long id;
    
    private String name;
    
    private String description;
    
    private Employee editor;
    
    private Status status;

    
    public Task() {
    }
    
    /**
     * 
     * @param name
     * @param description
     * @param editor
     */
    public Task(String name, String description, Employee editor, Status status) {
        this.name = name;
        this.description = description;
        this.editor = editor;
        this.status = status;
    }

    /**
     * Only for Dao stub
     * @param id
     * @param name
     * @param description
     * @param editor
     */
    public Task(long id, String name, String description, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }
    
    
    
    //<editor-fold defaultstate="collapsed" desc="hashCode / equals">
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Task other = (Task) obj;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getEditor() {
        return editor;
    }

    public void setEditor(Employee editor) {
        this.editor = editor;
    }
    
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    //</editor-fold> 

}
