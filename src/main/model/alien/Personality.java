package model.alien;

// represents the types of personality an alien pet could have
// including kind, shy, and impulsive
public enum Personality {
    KIND("kind"),
    SHY("shy"), 
    IMPULSIVE("impulsive");

    private final String personality;

    Personality(String personality) {
        this.personality = personality;
    }

    public String getPersonality() {
        return personality;
    }

    
}


