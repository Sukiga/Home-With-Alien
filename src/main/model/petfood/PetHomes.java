package model.petfood;

import model.alien.Alien;

import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// represents a pet home having pets 
public class PetHomes implements Writable {

    private LinkedList<Alien> pets; // a list/ collection of pets

    /*
     * EFFECTS: pets is instantiated as a linked list
     *          pets is set as pets
     */
    public PetHomes() {
        LinkedList<Alien> pets = new LinkedList<>();
        this.pets = pets;
    }

    // MODIFIES: this
    // EFFECTS: add a pet to the list of pet
    public void addPets(Alien pet) {
        if (pet != null) {
            this.pets.add(pet);
        }
    }

    public LinkedList<Alien> getPets() {
        return pets;
    }

    // REQUIRES: pets.size() != 0
    // EFFECTS: return the names of the current pets if there are pets
    public String showPetNames() {
        if (this.pets.size() != 0) {
            StringBuilder result = new StringBuilder();
            for (Alien pet: this.pets) {
                result.append(pet.getName()).append("\n");
            } 
            return result.toString();
        } else {
            return "You have no pets.";
        }
    }

    // REQUIRES: this.pets.size() != 0
    // EFFECTS: returns string representations of pet information
    public String showPet(String name) {
        if (this.pets.size() != 0) {
            StringBuilder result = new StringBuilder();
            for (Alien pet : this.pets) {
                if (pet.getName().equalsIgnoreCase(name)) {
                    result.append("\nName: ").append(pet.getName()).append("\n");
                    result.append("Living Status: ").append(pet.getLivingStatus()).append("\n");
                    result.append("Appearance: ").append(pet.getAppearance()).append("\n");
                    result.append("Accessory: ").append(pet.getAccessory()).append("\n");
                    result.append("Age: ").append(pet.getAge()).append("\n");
                    result.append("Identity ID: ").append(pet.getIdentity()).append("\n");
                    result.append("Personality: ").append(pet.getPersonality()).append("\n");
                    result.append("Background Story: ").append(pet.getStory()).append("\n");
                    result.append("Stomach: ").append(pet.getStomachStatus());
                } else {
                    result.append("\n");
                }
            }
            return result.toString();
        } else {
            return "empty home";
        }
    }

    // REQUIRES: pets.size() != 0
    // EFFECTS: returns a string representation of accessorized pets
    public String accessorizePet(String name, String accessory) {
        String done = "You don't have a pet called " + name;
        for (Alien pet: pets) {
            if (name.equalsIgnoreCase(pet.getName())) {
                done = pet.setAccessory(accessory) + "\n" + name + " is wearing " + accessory + " now.";
            }
        }
        return done;
    }

    // REQUIRES: name has a non-zero length and foodAmount >= 0
    // EFFECTS: return a string repsentation of food fed to pets
    public String feedPet(String name, int foodAmount) {
        String done = "You don't have a pet called " + name;
        if (foodAmount < 0) {
            done = "You cannot feed negative cans of petFood.";
        } else {
            for (Alien pet: pets) {
                if (name.equalsIgnoreCase(pet.getName())) {
                    done = pet.feedPet(foodAmount) + "\nYou fed " + pet.getName() + " " + foodAmount 
                        + " cans of pet food.";
                }
            }
        }
        return done;
    }

    // EFFECTS: return the number of pets
    public int getNumPets() {
        if (this.pets.isEmpty()) {
            return 0;
        }
        return this.pets.size();
    }

    // EFFECTS: return the first pet
    public Alien getFirstPet() {
        return pets.peek();
    }

    // save pets
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("pets", petsToJson());
        return json;
    }

    // EFFECTS: returns things in this pet home as a JSON array
    private JSONArray petsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Alien p : pets) {
            if (p != null) {
                jsonArray.put(p.toJson());
            }
        }

        return jsonArray;
    }


}
