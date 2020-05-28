package model;

import java.util.List;

public class Codecooler extends User{


    public Codecooler(int id, String name, String login, String email, String password, String phoneNumber, String role) {
        super(id, name, login, email, password, phoneNumber, role);
    }

    private int experience;
    private int level;
    private int wallet;
    private String teamName;
    private List<Item> boughtItems;
    private List<Quest> completedQuests;
    private Team team;

    public int getExperience() {
        return experience;
    }

    public int getLevel() {
        return level;
    }

    public int getWallet() {
        return wallet;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Item> getBoughtItems() {
        return boughtItems;
    }

    public List<Quest> getCompletedQuests() {
        return completedQuests;
    }

    public void setBoughtItems(List<Item> boughtItems) {
        this.boughtItems = boughtItems;
    }

    public void setCompletedQuests(List<Quest> completedQuests) {
        this.completedQuests = completedQuests;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Codecooler{" +
                "experience=" + experience +
                ", level=" + level +
                ", wallet=" + wallet +
                ", teamName='" + teamName + '\'' +
                ", boughtItems=" + boughtItems +
                ", completedQuests=" + completedQuests +
                ", team=" + team +
                '}';
    }
}
