/*
 * Sienna-Rae Johnson
 * Fall 101 CSC 422 Software Engineering
 * Week 1/2 Assignment: Pet Database
 * 11/14/2021
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
    
    // toString method outputs: name age
    @Override
    public String toString() {
        return this.name + " " + String.valueOf(this.age);
    }
}