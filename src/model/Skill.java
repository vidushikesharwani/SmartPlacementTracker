package model;

public class Skill {
    public enum ProficiencyLevel {
        BEGINNER,
        INTERMEDIATE,
        ADVANCED,
        EXPERT
    }

    private String skillName;
    private ProficiencyLevel proficiencyLevel;

    public Skill(String skillName, ProficiencyLevel proficiencyLevel) {
        this.skillName = skillName;
        this.proficiencyLevel = proficiencyLevel;
    }

    public String getSkillName() {
        return skillName;
    }

    public ProficiencyLevel getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setProficiencyLevel(ProficiencyLevel proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }
    @Override
public boolean equals(Object obj) {

    if (this == obj) {
        return true;
    }

    if (!(obj instanceof Skill)) {
        return false;
    }

    Skill other = (Skill) obj;

    return skillName.equalsIgnoreCase(other.skillName);
}

@Override
public int hashCode() {
    return skillName.toLowerCase().hashCode();
}

    @Override
    public String toString() {
        return skillName + " (" + proficiencyLevel + ")";
    }
}
