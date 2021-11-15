/*
 * Sienna-Rae Johnson
 * Fall 101 CSC 422 Software Engineering
 * Week 2 assignment: Pet Database
 * Milestone 1: Save/Load text file
 * 11/14/2021
 */
package PetDb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
    final private static String FILENAME = "pets.txt";
    
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
        
        // Read "pets.txt" file and save pets from file
        try {
            readFile();
        }
        catch (FileNotFoundException ex){
            System.out.println("Exception occurred: file could not be found.");
        }
        
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
                        
                        if (pets.size() == 5) {
                            System.out.println("Error: Database is full.");
                            break;
                        }
                        
                        // Check if user entered "done". Continue if not
                        if (!selection.toLowerCase().equals("done")) {
                            // Add new pet to pets arraylist (-1 means pet is not in database yet)
                            addOrUpdatePet(selection, -1);
                        } // end else 
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
                    
                    // check if entered id is a valid arraylist index
                    if (id >= 0 && id <= (pets.size()-1)) {
                        // Prompt user to enter new pet name and age
                        System.out.print("Enter new name and new age: ");
                        selection = input.nextLine().trim();

                        // Update pet in database
                        addOrUpdatePet(selection, id);
                    }
                    else {
                        printIndexDoesNotExistError(id);
                    }
                    break;
                    
                // Remove existing pet
                case 4: 
                    // Show all pets, then prompt user for pet id to remove
                    printAllPets();
                    System.out.println();
                    System.out.print("Enter pet ID to remove: ");
                    id = input.nextInt();
                    input.nextLine();
                    
                    if (id >= 0 && id <= (pets.size()-1)) {
                        // Display "pet removed" message
                        System.out.println(pets.get(id).toString() + " is removed.");

                        // Remove pet from db
                        pets.remove(id);
                    }
                    else {
                        printIndexDoesNotExistError(id);
                    }
                    
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
                    
                // Save pets to text file and exit program
                case 7:
                    try {
                        writeFile();
                    }
                    catch (FileNotFoundException ex) {
                        System.out.println("Exception occurred: file not found.");
                    }
                    
                    System.out.println("Goodbye!");
                    break;
                    
                // User selected out of bounds option
                default: 
                    System.out.println("Selection is out-of-bounds. Enter a number 1-7");
                    break;
            }
        }
    }
    
    // Read pets.txt file and input into pets ArrayList
    public static void readFile() throws FileNotFoundException {
        // Read pet file using Scanner
        Scanner readFile = new Scanner(new File(FILENAME));
        
        // Read all lines in file and add to pets ArrayList
        while (readFile.hasNext()) {
            loadPet(readFile.nextLine().trim());
        }
        
        // Close file
        readFile.close();
    }
    
    public static void writeFile() throws FileNotFoundException {
        // Open file for writing
        PrintWriter writeFile = new PrintWriter(FILENAME);
        
        // If no pets, empty file. Otherwise, write all pets to file
        if (pets.isEmpty()) {
            writeFile.print("");
        }
        else {
            // Write pets to file
            /* Write the first pet from pets ArrayList to file, then delete from ArrayList
               Repeat until ArrayList is empty */
            while (!pets.isEmpty()) {
                writeFile.println(pets.get(0).toString());
                pets.remove(0);
            }
        }
        
        // Close file
        writeFile.close();
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
    
    public static void loadPet(String petInfo) {
        // Save user input as array of strings
        String[] addNew = petInfo.split(" ");

        // Enter new pet into pets ArrayList
        Pet newPet = new Pet(addNew[0], Integer.parseInt(addNew[1]));
        pets.add(newPet);
    }
    
    /* Add or update pet in database. 
     * If new pet, index = -1
     * If updating pet, index is index of pet in database
     */
    public static void addOrUpdatePet(String petInfo, int index) {
        // Check input for 2 values (user input contains space)
        if (!petInfo.contains(" ")) {
            printInputError(petInfo);
        }
        // Check for multiple spaces in input
        else if ( petInfo.lastIndexOf(" ") != petInfo.indexOf(" ") ) {
            printInputError(petInfo);
        }
        else {
            // Save user input as array of strings
            String[] pet = petInfo.split(" ");    

            // Check age to verify that it is in integer format
            int age = 0;
            try {
                // Convert age input to integer. Throw exception if unable to do so
                age = stringToInt(pet[1]);

                // Verify that age is between 1 - 20
                if (age < 1 || age > 20) {
                    printInputError(petInfo);
                }
                else {
                    // Determine ADD PET or UPDATE PET
                    if (index == -1) {
                        // Enter new pet into pets ArrayList
                        Pet newPet = new Pet(pet[0], age);
                        pets.add(newPet);
                    }
                    else {
                        // Display new pet info to user
                        System.out.println(pets.get(index).toString() + 
                                " changed to " + petInfo);
                        // Update pet info
                        pets.get(index).setName(pet[0]);
                        pets.get(index).setAge(age);
                    }
                }
            } // end try
            catch (NumberFormatException ex) {
                printInputError(petInfo);
            } // end catch
        } // end else
    }
    
    public static void printInputError(String input) {
        System.out.println("Error: " + input + " is not a valid input.");
    }
    
    public static void printIndexDoesNotExistError(int index) {
        System.out.println("Error: ID " + index + " does not exist.");
    }
    
    public static int stringToInt(String input) throws NumberFormatException {
        return Integer.parseInt(input);
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