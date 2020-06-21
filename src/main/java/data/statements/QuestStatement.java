package data.statements;

public class QuestStatement {

    public String selectTaskByName() {
        return "SELECT * FROM quests WHERE name = ?;";
    }

    public String selectTaskById() {
        return "SELECT * FROM quests WHERE id = ?;";
    }

    public String insertTaskStatement() {
        return "INSERT INTO quests (id_mentor,name,points,description,category) VALUES (?,?,?,?,?);";
    }

    public String updateTaskStatement() {
        return "UPDATE quests SET points=?,description=?,category=? WHERE name=?;";
    }

    public String selectAllTasks() {
        return "SELECT * FROM quests;";
    }

    public String selectTasksByStudentId() {
        return "SELECT name,points,description,category FROM quests JOIN quests ON quests.id=students_quests.id_quest WHERE students_quests.id_user;";
    }

    public String deleteTaskStatement() {
        return "DELETE FROM quests WHERE quests.id=?;";
    }
}
