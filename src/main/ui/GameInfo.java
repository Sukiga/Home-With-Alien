package ui;

import model.alien.Alien;
import model.petfood.Inventory;
import model.petfood.PetHomes;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static ui.TerminalGame.*;

// represents the gathered information of the game, including the commands of pethome,
// inventory, quit, snow (a pet), fireballs (a pet), making a new pet, and also inputs from user, 
// start (game progress), pethome, inventory, and a new pet from the user
public class GameInfo {
    
    private static final String PETHOME_COMMAND = "pethome";  // command for pet home
    private static final String INVENTORY_COMMAND = "inventory"; // command for inventory
    private static final String SAVE_COMMAND = "save"; // command for saving
    private static final String SAVE_NAME = "save"; // name of saving
    private static final String LOAD_COMMAND = "load"; // command for loading
    private static final String LOAD_NAME = "load";
    private static final String QUIT_COMMAND = "quit"; // command for quitting
    private static final String SNOW_COMMAND = "snow"; // command for the pet snow
    private static final String NEW_COMMAND = "new"; // command for making a new pet
    private static final String NEW_TWO_COMMAND = "new2"; // command for the second new pet

    private final Scanner input; // user inputs
    private boolean start; // game progress
    private PetHomes petHome; // a pet home
    private Inventory inventory; // an inventory
    private Alien newPet; // a new pet from the user
    private Alien newPet2; // another new pet from the user

    private static final String JSON_STORE = "./data/petHome.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: petHome is set to petHome and inventory is set to inventory
    //          input is instantiated as a new scanner and start is true
    public GameInfo(PetHomes petHome, Inventory inventory) {
        input = new Scanner(System.in);
        start = true;
        this.petHome = petHome;
        this.inventory = inventory;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: processes user input
    public void handleUserInput() {
        printInstructions();
        String playerInput;

        while (start) {
            playerInput = getPlayerInput();
            findOption(playerInput);
        }

    }

    // MODIFIES: given user input
    // EFFECTS: return a string representation of the input after organizing
    private String getPlayerInput() {
        String playerInput = "";
        if (input.hasNext()) {
            playerInput = input.nextLine();
            playerInput = organizeString(playerInput);
        }
        return playerInput;
    }

    // MODIFIES: given str
    // EFFECTS: organize the provided input by changing it to lower case and cancelling spaces
    //          and replacing wrong signs
    private String organizeString(String str) { // this function is taken from lecture lab fitlifegym
        str = str.toLowerCase();
        str = str.trim();
        str = str.replaceAll("\"|'", "");
        return str;
    }


    // EFFECTS: read given input and return methods that  go to pethome, inventory, 
    // quiting the program or show retry instructions
    private void findOption(String input) {
        if (input.equals(PETHOME_COMMAND)) {
            printPetHome();
        } else if (input.equals(INVENTORY_COMMAND)) {
            printInventoryOptions();
        } else if (input.equals(SAVE_COMMAND)) {
            savePetHome();
        } else if (input.equals(LOAD_COMMAND)) {
            loadPetHome();
        } else if (input.equals(QUIT_COMMAND)) {
            start = false;
            endProgram();
        } else {
            System.out.println("Sorry, I didn't understand that command. Please try again.");
            printInstructions();
        }
    }

    // EFFECTS: displays menu of options to user
    private void printInstructions() {
        System.out.println("Enter " + PETHOME_COMMAND + " to view " + PETHOME_NAME);
        System.out.println("Enter " + INVENTORY_COMMAND + " to view " + INVENTORY_NAME);
        System.out.println("Enter " + SAVE_COMMAND + " to " + SAVE_NAME);
        System.out.println("Enter " + LOAD_COMMAND + " to " + LOAD_NAME + " your last progress.");
        System.out.println("To quit enter " + QUIT_COMMAND + ".");
    }

    // EFFECTS: displays menu of options to user
    private void printDecision() {
        System.out.println("Enter a to accessorize");
        System.out.println("Enter f to feed your pets\n");
        printInstructions();
    }

    // EFFECTS: processes user inputs
    private void findDecision(String input) {
        if (input.equalsIgnoreCase("a")) {
            printAccessorize();
        } else if (input.equalsIgnoreCase("f")) {
            printFeedPet();
        } else if (input.equalsIgnoreCase(QUIT_COMMAND)) {
            start = false;
            endProgram();
        } else {
            printInstructions();
            findOption(input);
        }
    }

    // EFFECTS: displays menu of options to user and conducts accessorization to pets
    private void printAccessorize() {
        System.out.println("\nWhich pet do you want to accessorize?");
        System.out.println(petHome.showPetNames());
        String name = String.valueOf(input.nextLine());
        System.out.println("\nWhich accessory do you want to use?");
        System.out.println("Black/ White/ Silver/ Gold/ Plain");
        String accessory = String.valueOf(input.nextLine());
        System.out.println(petHome.accessorizePet(name, accessory));
        handleUserInput();
    }


    // EFFECTS: displays menu of options to user and conducts feeding to pets
    private void printFeedPet() {
        System.out.println("\nWhich pet do you want to feed?");
        System.out.println(petHome.showPetNames());
        String name = String.valueOf(input.nextLine());
        System.out.println("\nHow many pet food do you want to feed it?");
        int foodAmount = Integer.valueOf(input.nextLine());
        if (inventory.getPetFood() <= 0) {
            System.out.println("You have no food left.");
        } else if (inventory.getPetFood() - foodAmount < 0) {
            System.out.println("You don't have enough food to feed them.");
        } else {
            inventory.getFood(foodAmount);
            System.out.println(petHome.feedPet(name, foodAmount));
        }
        handleUserInput(); 
    }
 

    // EFFECTS: displays petHome to user
    private void printPetHome() {
        System.out.println("\nYou have " + petHome.getNumPets() + " pets.");
        System.out.println("Enter " + SNOW_COMMAND + " to view " + ALIEN_SNOW_NAME);
        if (petHome.getNumPets() >= 2 && newPet != null) {
            System.out.println("Enter " + newPet.getName() + " to view " + newPet.getName());
        }
        if (petHome.getNumPets() >= 3 && newPet2 != null) {
            System.out.println("Enter " + newPet2.getName() + " to view " + newPet2.getName());
        }
        System.out.println("Enter " + NEW_COMMAND + " to make a new pet\n");
        printInstructions();
        String petCommand = String.valueOf(input.nextLine());
        findPets(petCommand);
    }

    // EFFECTS: read user inputs and return suitable pet information according to input
    private void findPets(String input) {
        if (input.equalsIgnoreCase(SNOW_COMMAND)) {
            printPetPage("snow");
        // } else if (input.equalsIgnoreCase(FIREBALLS_COMMAND)) {
        //     printPetPage("fireballs");
        } else if (input.equalsIgnoreCase(NEW_COMMAND)) {
            printNewPet();
        } else if (input.equalsIgnoreCase(NEW_TWO_COMMAND)) {
            printNewPet();
        } else if (newPet != null && newPet.getName().equalsIgnoreCase(input)) {
            printPetPage(newPet.getName());
        } else {
            printInstructions();
            findOption(input);
        }
    }

    // EFFECTS: displays menu of options to user and returns Pet information to user
    private void printPetPage(String name) {
        System.out.println(petHome.showPet(name));
        handleUserInput();
    }

    // EFFECTS: prints out inventory options to user
    private void printInventoryOptions() {
        System.out.println("\nPetfood: " + inventory.getPetFood());
        System.out.println("Accessories: BLACK, WHITE, GOLD, SILVER, PLAIN\n");
        printDecision();
        String letter = String.valueOf(input.nextLine());
        findDecision(letter);
    }

    // EFFECTS: stop the program
    public void endProgram() { 
        System.out.println("Quitting...");
        input.close();
    }

    // MODIFIES: newPet(if petHome is not full)
    // EFFECTS: displays options to user and conducts a new pet creation
    public void printNewPet() {   
        if (petHome.getNumPets() < 2) {
            try {
                makeNewPet();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for age. Please enter a number.");
            } catch (Exception e) {
                System.out.println("An error occurred while creating the pet: " + e.getMessage());
            } 
        } else if (petHome.getNumPets() < 3) {
            try {
                makeNewPetTwo();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for age. Please enter a number.");
            } catch (Exception e) {
                System.out.println("An error occurred while creating the pet: " + e.getMessage());
            }
        } else {
            System.out.println("\nYour Pet Home is Full.");
        }
        handleUserInput();
    }

    public void makeNewPet() {
        System.out.println("What is its name?");
        String name = String.valueOf(input.nextLine());
        System.out.println("How old is " + name + "?");
        int age = Integer.valueOf(input.nextLine());
        System.out.println("What is " + name + "'s identity ID?");
        String identity = String.valueOf(input.nextLine());
        System.out.println("Where did you find " + name + "?");
        String story = String.valueOf(input.nextLine());
        newPet = new Alien(name, age, identity, story);
        System.out.println("Created new pet: " + newPet.getName());
        System.out.println("What does " + name + " look like? (CIRCLE, SQUARE)");
        String appearance = String.valueOf(input.nextLine());
        System.out.println(newPet.setStringAppearance(appearance));
        System.out.println("What does " + name + " behave? (KIND, SHY, IMPULSIVE)");
        String personality = String.valueOf(input.nextLine());
        System.out.println(newPet.setStringPersonality(personality));
        petHome.addPets(newPet);
        System.out.println("There are your pets" + petHome.showPetNames());
    }

    public void makeNewPetTwo() {
        System.out.println("What is its name?");
        String name = String.valueOf(input.nextLine());
        System.out.println("How old is " + name + "?");
        int age = Integer.valueOf(input.nextLine());
        System.out.println("What is " + name + "'s identity ID?");
        String identity = String.valueOf(input.nextLine());
        System.out.println("Where did you find " + name + "?");
        String story = String.valueOf(input.nextLine());
        newPet2 = new Alien(name, age, identity, story);
        System.out.println("What does " + name + " look like? (CIRCLE, SQUARE)");
        String appearance = String.valueOf(input.nextLine());
        System.out.println(newPet2.setStringAppearance(appearance));
        System.out.println("What does " + name + " behave? (KIND, SHY, IMPULSIVE)");
        String personality = String.valueOf(input.nextLine());
        System.out.println(newPet2.setStringPersonality(personality));
        petHome.addPets(newPet2);
        System.out.println("There are your pets" + petHome.showPetNames());
    }

        // EFFECTS: saves the pet home to file
    private void savePetHome() {
        try {
            jsonWriter.open();
            jsonWriter.write(petHome);
            jsonWriter.close();
            System.out.println("Saved current pet home to " + JSON_STORE);
            printInstructions();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
            printInstructions();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadPetHome() {
        try {
            petHome = jsonReader.read();
            System.out.println("Loaded recent pet home from " + JSON_STORE);
            if (petHome.getNumPets() > 1) {
                newPet = petHome.getPets().get(1);
                if (petHome.getNumPets() > 2) {
                    newPet2 = petHome.getPets().get(2);
                }
            } else {
                newPet = null;
            }
            printInstructions();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            printInstructions();
        }
    }
    
    
}
