package model;

import java.util.List;

public class StudentDetails {

    private User user;
    private int groupId;
    private String teamName;
    private int level;
    private int wallet;
    private int experience;
    private Group aGroup;
    private List<Item> boughtItems;
    private List<Quest> completedQuests;

    public StudentDetails(User user, int wallet, int experience,int groupId,String teamName) {
        this.user = user;
        this.wallet = wallet;
        this.experience = experience;
        this.groupId = groupId;
        this.teamName = teamName;
    }

    public StudentDetails(){
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Integer getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void addExperience(int experience) {
        this.experience += experience;
    }

    public Group getaGroup() {
        return aGroup;
    }

    public void setaGroup(Group aGroup) {
        this.aGroup = aGroup;
    }

    public List<Item> getBoughtItems() {
        return boughtItems;
    }

    public void setBoughtItems(List<Item> boughtItems) {
        this.boughtItems = boughtItems;
    }

    public List<Quest> getCompletedQuests() {
        return completedQuests;
    }

    public void setCompletedQuests(List<Quest> completedQuests) {
        this.completedQuests = completedQuests;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                ", team=" + aGroup +
                '}';
    }
}