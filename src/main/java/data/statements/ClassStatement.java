package data.statements;

public class ClassStatement {

    public String selectAllClasses() {
        return "SELECT * FROM classes WHERE name != 'ANYTHING BUT THIS!';" ;
    }

    public String selectTeamByName() {
        return "SELECT * FROM classes WHERE name = ?;" ;
    }

    public String selectGroupById() {
        return "SELECT * FROM classes WHERE id = ?;" ;
    }

    public String insertGroupStatement() {

        return "INSERT INTO classes (name) VALUES (?);" ;
    }

    public String deleteGroupStatement() {
        return "DELETE FROM classes WHERE id=?;";
    }

    public String selectGroupsNamesByMentorId() {
        return "SELECT * FROM classes JOIN users_classes ON classes.id = users_classes.id_class WHERE users_classes.id_user = ?;";
    }

    public String selectMentorAssignedToGroups() {
        return "SELECT * FROM users_classes;";
    }


}
