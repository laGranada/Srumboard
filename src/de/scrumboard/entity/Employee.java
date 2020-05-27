/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.scrumboard.entity;

/**
 *
 * @author MaggieSaurusRex
 */
public class Employee {
    
    private long id;
    
    private String firstName;
    
    private String lastName;
    
    private String Street;
    
    private String city;
    
    private String country;
    
    private int zipCode;
    
    private String username;
    
    private String password;

    public Employee() {
    }

    /**
     * Only for Dao stub
     * @param id
     * @param firstName
     * @param lastName
     * @param Street
     * @param city
     * @param country
     * @param zipCode
     * @param username
     * @param password
     */
    public Employee(long id, String firstName, String lastName, String Street, String city, String country, int zipCode, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.Street = Street;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
        this.username = username;
        this.password = password;
    }
    
    

    //<editor-fold defaultstate="collapsed" desc="hashCode / equals">
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Employee other = (Employee) obj;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }   
    //</editor-fold>  

    public String toFullName(){
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }
    
    
}
