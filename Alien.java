package model.alien;

import org.json.JSONObject;
import persistence.Writable;

// Represents an alien having a name, living Status, appearance, accessory, age, identity,
// story, stomach capacity and stomach Status
public class Alien implements Writable {
    private String name; // Alien Name
    private String livingStatus; // the living Status of the Alien pet
    private Appearance appearance; // the looks of the alien pet
    private Accessory accessory; // the accessory of the alien pet
    private int age; // the age of the alien pet
    private String identity; // the identity number of the alien pet
    private Personality personality; // the personality or behavior of the alien pet
    private String story; // the background story of the alien pet
    private int stomach; // the stomach capacity of the alien pet
    private String stomachStatus; // the satiety of the alien pet
    
    /*
     * REQUIRES: name, identity, story have non-zero length, age has an integer value
     * EFFECTS: name of the alien is set to name; living Status's initial value is alive
     *          age is set to age, identity is set to given identity, story is set to story
     *          initial stomach status is set to hungry where the stomach capacity is 0
     *          and the initial accessory is set plain 
     */
    public Alien(String name, int age, String identity, String story) {
        this.name = name;
        this.livingStatus = "alive";
        this.age = age;
        this.identity = identity;
        this.story = story;
        this.stomachStatus = "hungry";
        this.stomach = 0;
        this.accessory = Accessory.PLAIN;
    }

    /*
     * REQUIRES: food >= 0 and dead pets are not feedable
     * MODIFIES: this
     * EFFECTS: stomachStatus is still hungry if food eaten is lower than 10
     *          stomachStatus is half full if food eaten is at least 10
     *          stomachStatus is full if food eaten is 20
     *          stomachStatus is stuffed to death when food eaten is more than 20
     *          and also living status would turn to dead
     */
    public String feedPet(int food) {
        String result = "";
        if (this.livingStatus.equalsIgnoreCase("dead")) {
            result = "You cannot feed a dead pet.";
        } else {
            this.stomach += food;
            if (this.stomach > 20) {
                this.stomachStatus = "stuffed to death";
                this.livingStatus = "dead";
                result = "Your pet " + this.name + " is stuffed to death.";
            } else if (this.stomach == 20) {
                this.stomachStatus = "full";
            } else if (this.stomach >= 10) {
                this.stomachStatus = "half-full";
            }
        }
        return result;
        
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLivingStatus(String lifeStatus) {
        this.livingStatus = lifeStatus;
    }

    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }

    // same function as setAppearance but allow String inputs
    public String setStringAppearance(String appearance) {
        String result = "";
        if (appearance.equalsIgnoreCase("snow")) {
            this.appearance = Appearance.SNOW;
        } else if (appearance.equalsIgnoreCase("pet type 1")) {
            this.appearance = Appearance.PETTYPEONE;
        } else {
            result = "\nInput typed is invalid.\nAppearance set to default: CIRCLE\n";
            this.appearance = Appearance.PETTYPETWO;
        }
        return result;
    }

    // same function as setPersonality but allow String inputs
    public String setStringPersonality(String personality) {
        String result = "";
        if (personality.equalsIgnoreCase("shy")) {
            this.personality = Personality.SHY;
        } else if (personality.equalsIgnoreCase("kind")) {
            this.personality = Personality.KIND;
        } else if (personality.equalsIgnoreCase("impulsive")) {
            this.personality = Personality.IMPULSIVE;
        } else {
            result = "\nInput typed is invalid.\nPersonality set to default: KIND\n";
            this.personality = Personality.KIND;
        }
        return result;
    }

    public String setAccessory(String accessory) {
        String result = "";
        if (accessory.equalsIgnoreCase("Black")) {
            this.accessory = Accessory.BLACK;
        } else if (accessory.equalsIgnoreCase("White")) {
            this.accessory = Accessory.WHITE;
        } else if (accessory.equalsIgnoreCase("Gold")) {
            this.accessory = Accessory.GOLD;
        } else if (accessory.equalsIgnoreCase("Silver")) {
            this.accessory = Accessory.SILVER;
        } else {
            result = "\nInput typed is invalid.\nAccessory set to default: PLAIN\n";
            this.accessory = Accessory.PLAIN;
        }
        return result;
    }

    public void setPersonality(Personality personality) {
        this.personality = personality;
    }

    public void setStomach(int capacity) {
        this.stomach = capacity;
    }

    public void setStomachStatus(String status) {
        this.stomachStatus = status;
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public String getName() {
        return name;
    }

    public String getLivingStatus() {
        return livingStatus;
    }
    
    public int getAge() {
        return age;
    }

    public String getIdentity() {
        return identity;
    }

    public Personality getPersonality() {
        return personality;
    }

    public String getStory() {
        return story;
    }

    public int getStomach() {
        return stomach;
    }

    public String getStomachStatus() {
        return stomachStatus;
    }

    public Accessory getAccessory() {
        return accessory;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("livingStatus", livingStatus);
        json.put("appearance", appearance);
        json.put("accessory", accessory);
        json.put("age", age);
        json.put("identity", identity);
        json.put("personality", personality);
        json.put("story", story);
        json.put("stomach", stomach);
        json.put("stomachStatus", stomachStatus);
        return json;
    }

}
