package model.alien;

// represents the types of appearance pets have, including Snow type, pet type one, and pet type two
public enum Appearance {

    SNOW("SNOW","src/main/ui/Pics/BigSnowPlain.png", "src/main/ui/Pics/SmallSnowPlain.png"),
    PETTYPEONE("GREEN SNOW","src/main/ui/Pics/BigPetType1Plain.png", "src/main/ui/Pics/SmallPetType1Plain.png"),
    PETTYPETWO("FURIOUS","src/main/ui/Pics/BigPetType2Plain.png", "src/main/ui/Pics/SmallPetType2Plain.png");

    private final String type;
    private final String bigImagePath;
    private final String smallImagePath;

    Appearance(String type, String bigImagePath, String smallImagePath) {
        this.type = type;
        this.smallImagePath = smallImagePath;
        this.bigImagePath = bigImagePath;
    }

    public String getType() {
        return type;
    }

    public String getBigImagePath() {
        return bigImagePath;
    }

    public String getSmallImagePath() {
        return smallImagePath;
    }
}
