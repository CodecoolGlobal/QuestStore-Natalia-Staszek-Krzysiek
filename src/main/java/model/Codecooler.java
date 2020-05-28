package model;

import java.util.List;

public class Codecooler extends User{
    private int experience;
    private int level;
    private int wallet;
    private String teamName;
    private List<Item> boughtItems;
    private List<Quest> completedQuests;
    private Team team;

    public Codecooler(String name, String login, String email, String password, String phoneNumber,
                      int role, int experience, int level, int wallet, String teamName,
                      List<Item> boughtItems, List<Quest> completedQuests, Team team) {
        super(name, login, email, password, phoneNumber, role);
        this.experience = experience;
        this.level = level;
        this.wallet = wallet;
        this.teamName = teamName;
        this.boughtItems = boughtItems;
        this.completedQuests = completedQuests;
        this.team = team;
    }

    public Codecooler() {

    }

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