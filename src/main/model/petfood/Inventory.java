package model.petfood;

// represents the inventory having petfood
public class Inventory {

    private int petFood; // petFood amount in the inventory

    // petFood set to 50  
    public Inventory() {
        this.petFood = 50; 
    }

    public int getPetFood() {
        return this.petFood;
    }

    // REQUIRES: petFood - amount >= 0
    // MODIFIES: this
    // Effects: petFood decreases by 1
    public void getFood(int amount) {
        petFood -= amount;
    }
    
}
