package model;

import java.util.ArrayList;
import java.util.List;

public class Mentor extends User{
    private final List<Quest> mentorQuests = new ArrayList<>();

    public Mentor(String name, String login, String email, String password, String phoneNumber, int role) {
        super(name, login, email, password, phoneNumber, role);
    }

    public List<Quest> getMentorQuests(List<Quest> allQuest) {
        for (Quest quest : allQuest) {
            if (quest.getIdMentor() == this.getId()) {
                mentorQuests.add(quest);
            }
        }
        return mentorQuests;
    }
}
