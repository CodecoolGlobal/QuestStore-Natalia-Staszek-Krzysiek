package model;

import java.util.Objects;

public class Class {

    private int id;
    private String teamName;

    public Class(String name) {
        this.teamName = name;
    }

    public Class(int id, String name) {
        this(name);
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return id == aClass.id &&
                Objects.equals(teamName, aClass.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamName);
    }
}
