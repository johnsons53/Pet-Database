/*
 * Sienna-Rae Johnson
 * Fall 101 CSC 422 Software Engineering
 * Week 1 assignment: pet database
 * 11/07/2021
 */
package PetDb;

import petdb.Pet;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Sienna-Rae
 */
public class PetDb {
    // Set up pet "database" saved in arraylist of Pet objects
    public static ArrayList<Pet> pets = new ArrayList<>();
    
    public static void main(String[] args) {
        // Set up input variable
        Scanner input = new Scanner(System.in);
        int choice = 0;
        
        // Set up variables to use in switch statement
        String selection = "";
        int count = 0;
        int id = 0;
        
        // Print start message 
        System.out.println("Pet Database Program.");
        
        // Repeat operations until user enters 7
        while (choice != 7) {
            printMenu();
            choice = input.nextInt();
            input.nextLine();
            System.out.println();
            
            switch(choice) {
                // View all pets
                case 1: 
                    printAllPets();
                    break;
                
                // Add more pets
                case 2: 
                    // Instructions
                    System.out.println("Enter pets in (name age) format." + 
                            " Enter 'done' to finish.");
                    
                    // Continue to add pets until user enters "done"
                    selection = "";
                    while (!selection.equals("done")) {
                        
                        // Add one pet at a time
                        System.out.print("Add pet (name age): ");
                        selection = input.nextLine().trim();
                        
                        // Check if user entered "done". Continue if not
                        if (!selection.toLowerCase().equals("done")) {
                            addNewPet(selection);
                        }
                    }
                    break;
                
                // Update existing pet
                case 3: 
                    // Show all pets, then prompt user for pet id to update
                    printAllPets();
                    System.out.println();
                    System.out.print("Enter pet ID to update: ");
                    id = input.nextInt();
                    input.nextLine();
                    
                    // Prompt user to enter new pet name and age
                    System.out.println("Enter new name and new age: ");
                    selection = input.nextLine().trim();
                    
                    // Display new pet info to user
                    System.out.println(pets.get(id).toString() + 
                            " changed to " + selection);
                    String[] updatePet = selection.split(" ");
                    
                    // Update pet info
                    pets.get(id).setName(updatePet[0]);
                    pets.get(id).setAge(Integer.parseInt(updatePet[1]));
                    break;
                    
                // Remove existing pet
                case 4: 
                    // Show all pets, then prompt user for pet id to remove
                    printAllPets();
                    System.out.println();
                    System.out.print("Enter pet ID to remove: ");
                    id = input.nextInt();
                    input.nextLine();
                    
                    // Display "pet removed" message
                    System.out.println(pets.get(id).toString() + " is removed.");
                    
                    // Remove pet from db
                    pets.remove(id);
                    break;
                    
                // Search pets by NAME
                case 5: 
                    // Prompt user for name search criteria
                    System.out.print("Enter a name to search: ");
                    selection = input.nextLine().trim();
                    
                    // Print all applicable rows to user in addition to header and footer
                    printHeader();
                    // Display one row per pet for all pets
                    count = 0;
                    for (int i = 0; i < pets.size(); i++) {
                        // Only print row if pet name matches user search criteria
                        if (pets.get(i).getName().equals(selection)) {
                            printRow(i, pets.get(i).getName(), pets.get(i).getAge());
                            count++;
                        }
                    }
                    printFooter(count);
                    break;
                    
                // Search pets by AGE
                case 6: 
                    // Prompt user for age search criteria
                    System.out.print("Enter an age to search: ");
                    choice = input.nextInt();
                    input.nextLine();
                    
                    // Print all applicable rows to user in addition to header and footer
                    printHeader();
                    // Display one row per pet for all pets
                    count = 0;
                    for (int i = 0; i < pets.size(); i++) {
                        // Only print row if pet age matches user search criteria
                        if (pets.get(i).getAge() == choice) {
                            printRow(i, pets.get(i).getName(), pets.get(i).getAge());
                            count++;
                        }
                    }
                    printFooter(count);
                    
                    // reset choice value
                    choice = 6;
                    break;
                    
                // Exit program
                case 7:
                    System.out.println("Goodbye!");
                    break;
                    
                // User selected out of bounds option
                default: 
                    System.out.println("Selection is out-of-bounds. Enter a number 1-7");
                    break;
            }
        }
    }
    
    // Print options menu
    public static void printMenu() {
        System.out.print("\nWhat would you like to do?\n" + 
                "1) View all pets\n" + 
                "2) Add more pets\n" + 
                "3) Update an existing pet\n" + 
                "4) Remove an existing pet\n" + 
                "5) Search pets by name\n" + 
                "6) Search pets by age\n" + 
                "7) Exit program\n" + 
                "\nYour choice: ");
    }
    
    public static void addNewPet(String petInfo) {
        // Save user input as array of strings
        String[] addNew = petInfo.split(" ");

        // Enter new pet into pets ArrayList
        Pet newPet = new Pet(addNew[0], Integer.parseInt(addNew[1]));
        pets.add(newPet);
    }
    
    public static void printAllPets() {
        // Print header
        printHeader();
        // Display one row per pet for all pets
        for (int i = 0; i < pets.size(); i++) {
            printRow(i, pets.get(i).getName(), pets.get(i).getAge());
        }
        
        // Print footer 
        printFooter(pets.size());
    }
    
    // Print display header
    public static void printHeader() {
        printLine();
        System.out.printf("| %-2S | %-10S | %-3S |\n", "id", "name", "age");
        printLine();
    }
    
    // Print display footer
    public static void printFooter(int num) {
        printLine();
        System.out.println(num + " row(s) in set.");
    }
    
    public static void printRow(int id, String name, int age) {
        System.out.printf("| %2d | %-10s | %3d |\n", id, name, age);
    }
    
    public static void printLine() {
        System.out.println("+-----------------------+");
    }
}