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
    TO_DO("Offen", "Aufgabe muss noch erledigt werden"),
    IN_PROGRESS("Bearbeitung", "Aufgabe wird bearbeitet"),
    TO_VERIFY("Verifizierung", "Aufgabe wird verifiziert"),
    DONE("Fertig", "Aufgabe wurde fertiggestellt");
    
    private final String shortDescription;
    
    private final String description;
    
    private Status(String shortDescription, String description){
        this.shortDescription = shortDescription;
        this.description = description;
    }
    
    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }
}
