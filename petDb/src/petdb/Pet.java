/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petdb;

/**
 *
 * @author Sienna-Rae
 */
public class Pet {
    private String name;
    private int age;
    
    // Constructor
    public Pet(String newName, int newAge) {
        this.name = newName;
        this.age = newAge;
    }
    
    // No-arg constructor
    public Pet() {}
    
    // Getter methods (return variable)
    public String getName() {
        return this.name;
    }
    
    public int getAge() {
        return this.age;
    }
    
    // Setter methods (set variable)
    public void setName(String newName) {
        this.name = newName;
    }
    
    public void setAge(int newAge) {
        this.age = newAge;
    }
}