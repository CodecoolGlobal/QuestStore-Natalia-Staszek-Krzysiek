package data.statements;

public class StudentQuestStatement {

    public String insertConnectionStatement() {
        return "INSERT INTO students_quests (id_user,id_quest) VALUES (?,?);";
    }
}
