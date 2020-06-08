package model;

import java.util.List;

public class StudentDetails extends User{
    private int groupId;
    private String teamName;
    private int level;
    private int wallet;
    private int experience;
    private Class aClass;
    private List<Item> boughtItems;
    private List<Quest> completedQuests;

    public StudentDetails(int id, String name, String login, String email, String password, String phoneNumber, int role, int groupId, String teamName, int level, int wallet, int experience, Class aClass, List<Item> boughtItems, List<Quest> completedQuests) {
        super(id, name, login, email, password, phoneNumber, role);
        this.groupId = groupId;
        this.teamName = teamName;
        this.level = level;
        this.wallet = wallet;
        this.experience = experience;
        this.aClass = aClass;
        this.boughtItems = boughtItems;
        this.completedQuests = completedQuests;
    }

    public StudentDetails(int id, int groupId, String teamName, int level, int wallet, int experience) {
        super(id);
        this.groupId = groupId;
        this.teamName = teamName;
        this.level = level;
        this.wallet = wallet;
        this.experience = experience;
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

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
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

    @Override
    public String toString() {
        return "Codecooler{" +
                "experience=" + experience +
                ", level=" + level +
                ", wallet=" + wallet +
                ", teamName='" + teamName + '\'' +
                ", boughtItems=" + boughtItems +
                ", completedQuests=" + completedQuests +
                ", team=" + aClass +
                '}';
    }
}