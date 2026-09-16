package model;

import java.util.HashSet;
import java.util.Set;

public class Project {
    private String projectId;
    private String projectName;
    private String description;
    private Set<String> technologies;

    public Project(String projectId, String projectName, String description) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.description = description;
        this.technologies = new HashSet<>();
    }

    public String getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getTechnologies() {
        return technologies;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addTechnology(String technology) {
        technologies.add(technology);
    }

    @Override
    public String toString() {
        return projectName + " - " + technologies;
    }
}