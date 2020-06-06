package data.statements;

public class TeamStatement {

    public String selectAllTeams() {
        return "SELECT * FROM WHERE group_name != 'ANYTHING BUT THIS!';" ;
    }

    public String selectTeamByName() {
        return "SELECT * FROM teams WHERE name = ?;" ;
    }

    public String selectGroupById() {
        return "SELECT * FROM teams WHERE id = ?;" ;
    }

    public String insertGroupStatement() {

        return "INSERT INTO teams (name) VALUES (?);" ;
    }

    public String deleteGroupStatement() {
        return "DELETE FROM teams WHERE id=?;";
    }

    public String selectGroupsNamesByMentorId() {
        return "SELECT * FROM teams JOIN users_teams ON teams.id = users_teams.id_team WHERE users_teams.id_user = ?;";
    }

    public String selectMentorAssignedToGroups() {
        return "SELECT * FROM users_teams;";
    }


}
