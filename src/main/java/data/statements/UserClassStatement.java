package data.statements;

public class UserClassStatement {

    public String insertConnectionStatement() {
        return "INSERT INTO users_classes (id_class,id_user) VALUES (?,?);";
    }

    public String deleteConnectionStatement() {
        return "DELETE FROM users_classes WHERE id_class = ? AND id_user = ?;";
    }

    public String deleteConnectionStatementByMentorID() {
        return "DELETE FROM user_classes WHERE id_user = ?;";
    }
}
