package model;

import java.util.Objects;

public class Team {

    private int id;
    private String teamName;

    public Team(String name) {
        this.teamName = name;
    }

    public Team(int id, String name) {
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
        Team team = (Team) o;
        return id == team.id &&
                Objects.equals(teamName, team.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamName);
    }
}
