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
    
    public static void main(String[] args) {
        // Set up input variable
        Scanner input = new Scanner(System.in);
        int choice = 0;
        
        // Set up pet "database" saved in arraylist of Pet objects
        ArrayList<Pet> pets = new ArrayList<>();
        
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
                    // Display header, display all pets, then display footer
                    printHeader();
                    for (int i = 0; i < pets.size(); i++) {
                        printRow(i, pets.get(i).getName(), pets.get(i).getAge());
                    }
                    printFooter(pets.size());
                    break;
                
                // Add more pets
                case 2: 
                    // Instructions
                    System.out.println("Enter pets in 'name age' format." + 
                            " Enter 'done' to finish");
                    
                    // Continue to add pets until user enters "done"
                    String selection = "";
                    while (!selection.equals("done")) {
                        System.out.print("Add pet (name age): ");
                        selection = input.nextLine();
                        if (!selection.toLowerCase().equals("done")) {
                            String[] addNew = selection.split(" ");
                            Pet newPet = new Pet(addNew[0], Integer.parseInt(addNew[1]));
                            pets.add(newPet);
                        }
                    }
                    break;
                
                // Update existing pet
                case 3: 
                    System.out.println("Update pet" + " function not implemented.");
                    break;
                    
                // Remove existing pet
                case 4: 
                    System.out.println("Remove pet" + " function not implemented.");
                    break;
                    
                // Search pets by NAME
                case 5: 
                    System.out.println("Search pets by name" + " function not implemented.");
                    break;
                    
                // Search pets by AGE
                case 6: 
                    System.out.println("Search pets by age" + " function not implemented.");
                    break;
                    
                // Exit program
                case 7:
                    System.out.println("Goodbye!");
                    break;
                    
                // User selected out of bounds option
                default: 
                    System.out.println("This function is not yet available");
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
    
    // Print output header
    public static void printHeader() {
        printLine();
        System.out.printf("| %-2S | %-10S | %-3S |\n", "id", "name", "age");
        printLine();
    }
    
    public static void printRow(int id, String name, int age) {
        System.out.printf("| %-2S | %-10S | %-3S |\n", id, name, age);
    }
    
    // Print output footer
    public static void printFooter(int num) {
        printLine();
        System.out.println(num + " rows in set.");
    }
    
    public static void printLine() {
        System.out.println("+-----------------------+");
    }
}