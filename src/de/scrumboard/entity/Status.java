/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.scrumboard.entity;

/**
 * The status of a Task
 * with a short or long description
 * 
 * @author MaggieSaurusRex
 */
public enum Status {
    TO_DO("To Do"),
    IN_PROGRESS("In progress"),
    TO_VERIFY("To verify"),
    DONE("Done");
    
    private final String shortDescription;
    
    
    private Status(String shortDescription){
        this.shortDescription = shortDescription;
    }
    
    public String getShortDescription() {
        return shortDescription;
    }

    @Override
    public String toString() {
        return shortDescription;
    }
    
    
}
