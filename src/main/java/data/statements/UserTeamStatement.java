package data.statements;

public class UserTeamStatement {

    public String insertConnectionStatement() {
        return "INSERT INTO user_teams (id_team,id_user) VALUES (?,?);";
    }

    public String deleteConnectionStatement() {
        return "DELETE FROM user_teams WHERE user_teams.id_team = ? AND user_teams.id_user = ?;";
    }

    public String deleteConnectionStatementByMentorID() {
        return "DELETE FROM user_teams WHERE user_teams.id_user = ?;";
    }
}
