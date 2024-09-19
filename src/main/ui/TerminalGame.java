package ui;

import model.alien.Alien;
import model.petfood.Inventory;
import model.petfood.PetHomes;

import static model.alien.Personality.*;

import static model.alien.Appearance.*;


public class TerminalGame {

    public static final String PETHOME_NAME = "Pet home";
    public static final String INVENTORY_NAME = "Inventory";
    public static final String ALIEN_SNOW_NAME = "Snow";
    public static final String ALIEN_FIREBALLS_NAME = "Fireballs";
    
    public TerminalGame() {
        PetHomes petHome = new PetHomes();
        Inventory inventory = new Inventory();
        Alien snow = new Alien(ALIEN_SNOW_NAME, 10, "12345", "Snow was born in winter.");
        snow.setAppearance(SNOW);
        snow.setPersonality(IMPULSIVE);
        petHome.addPets(snow);
        // Alien fireBalls = new Alien(ALIEN_FIREBALLS_NAME, 12, "54321", "Fireballs was born in lava.");
        // fireBalls.setAppearance(SQUARE);
        // fireBalls.setPersonality(KIND);
        // petHome.addPets(fireBalls);
        System.out.println("\nWelcome to Home with Aliens!");
        System.out.println("As the Valedictorian of UBC 2027, you're selected to represent Canada to live on Mars!");
        System.out.println("You are now exploring your home on Mars.");
        System.out.println("Explore Your Home!\n");
        GameInfo info = new GameInfo(petHome, inventory);
        info.handleUserInput();
    }  

    

}
