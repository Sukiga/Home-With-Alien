package persistence;

import model.alien.Alien;
import model.alien.Appearance;
import model.alien.Personality;
import model.alien.Accessory;
import model.petfood.PetHomes;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
// This class is modeled from JsonReader in the provided sample JSONSERIALIZATION
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads pet home from file and returns them;
    // throws IOException if an error occurs reading data from file
    public PetHomes read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePetHome(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses pethome from JSON object and returns it
    private PetHomes parsePetHome(JSONObject jsonObject) {
        PetHomes ph = new PetHomes();
        addPets(ph,jsonObject);
        return ph;
    }

    // MODIFIES: ph
    // EFFECTS: parses pets from JSON object and adds them to pet home
    private void addPets(PetHomes ph, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("pets");
        for (Object json : jsonArray) {
            JSONObject nextPet = (JSONObject) json;
            addPet(ph, nextPet);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addPet(PetHomes ph, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String livingStatus = jsonObject.getString("livingStatus");
        Appearance appearance = Appearance.valueOf(jsonObject.getString("appearance"));
        Accessory accessory = Accessory.valueOf(jsonObject.getString("accessory"));
        int age = jsonObject.getInt("age");
        String identity = jsonObject.getString("identity");
        Personality personality = Personality.valueOf(jsonObject.getString("personality"));
        String story = jsonObject.getString("story");
        int stomach = jsonObject.getInt("stomach");
        String stomachStatus = jsonObject.getString("stomachStatus");
        Alien pet = new Alien(name, age, identity, story);
        pet.setLivingStatus(livingStatus);
        pet.setAppearance(appearance);
        pet.setAccessory(accessory.toString());
        pet.setPersonality(personality);
        pet.setStomach(stomach);
        pet.setStomachStatus(stomachStatus);
        ph.addPets(pet);
    }
}