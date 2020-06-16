package model;

import java.util.Objects;

public class Group {

    private int id;
    private String teamName;

    public Group(String name) {
        this.teamName = name;
    }

    public Group(int id, String name) {
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
        Group aGroup = (Group) o;
        return id == aGroup.id &&
                Objects.equals(teamName, aGroup.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamName);
    }
}
