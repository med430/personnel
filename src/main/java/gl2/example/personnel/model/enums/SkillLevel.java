package gl2.example.personnel.model.enums;

public enum SkillLevel {
    BASIC("Débutant"),
    INTERMEDIATE("intermédiaire"),
    ADVANCED("avancé"),
    EXPERT("Expert");
    private final String displayName;

    SkillLevel(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
