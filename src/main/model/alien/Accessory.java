package model.alien;

// represents the types of accessory the player have
// including BLACK, WHITE, GOLD, SILVER, PLAIN
public enum Accessory {

    BLACK("BLACK","src/main/ui/Pics/BigSnowBlack.png", "src/main/ui/Pics/BigPetType1Black.png", 
    "src/main/ui/Pics/BigPetType2Black.png"),
    WHITE("WHITE","src/main/ui/Pics/BigSnowWhite.png", "src/main/ui/Pics/BigPetType1White.png", 
    "src/main/ui/Pics/BigPetType2White.png"),
    GOLD("GOLD","src/main/ui/Pics/BigSnowGold.png", "src/main/ui/Pics/BigPetType1Gold.png", 
    "src/main/ui/Pics/BigPetType2Gold.png"),
    SILVER("SILVER","src/main/ui/Pics/BigSnowSilver.png", "src/main/ui/Pics/BigPetType1Silver.png", 
    "src/main/ui/Pics/BigPetType2Silver.png"),
    PLAIN("PLAIN","src/main/ui/Pics/BigSnowPlain.png", "src/main/ui/Pics/BigPetType1Plain.png", 
    "src/main/ui/Pics/BigPetType2Plain.png");

    private final String type;
    private final String snowTypeImagePath;
    private final String petTypeOneImagePath;
    private final String petTypeTwoImagePath;

    Accessory(String type, String snowTypeImagePath, String petTypeOneImagePath, String petTypeTwoImagePath) {
        this.type = type;
        this.snowTypeImagePath = snowTypeImagePath;
        this.petTypeOneImagePath = petTypeOneImagePath;
        this.petTypeTwoImagePath = petTypeTwoImagePath;
    }

    public String getType() {
        return type;
    }

    public String getSnowTypeImagePath() {
        return snowTypeImagePath;
    }

    public String getPetTypeOneImagePath() {
        return petTypeOneImagePath;
    }

    public String getPetTypeTwoImagePath() {
        return petTypeTwoImagePath;
    }
}
