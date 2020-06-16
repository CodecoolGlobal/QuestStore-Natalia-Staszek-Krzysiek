package data.statements;

public class UserStatement {

    public String selectAllUsers() {
        return "SELECT * FROM users";
    }

    public String selectUserById() {
        return "SELECT * FROM users WHERE id = ?";
    }

    public String insertUserStatement() {
        return "INSERT INTO users (name,login,email,password,phone_number,id_role) VALUES (?,?,?,?,?,?);";
    }

    public String updateUserStatement() {
        return "UPDATE users SET name=?,login=?,email=?,password=?,phone_number=?,id_role=? WHERE id=?;";
    }

    public String deleteUserStatement() {
        return "DELETE FROM users WHERE id = ?;";
    }

    public String selectUserByLoginAndPassword() {
        return "SELECT * FROM users WHERE login = ? AND password = ?;";
    }

    public String selectUserByLoginAndRole() {
        return "SELECT * FROM users WHERE login = ? AND id_role = ?;";
    }

    public String selectUserByLogin() {
        return "SELECT * FROM users WHERE login = ?;";
    }

    public String selectAllUsersByRole() {
        return "SELECT * FROM users WHERE id_role = ?;";
    }

    public String selectUserByEmail() {
        return "SELECT * FROM users WHERE email = ?;";
    }

    public String selectUserByPhoneNumber() {
        return "SELECT * FROM users WHERE phone_number = ?;";
    }

    public String selectAllStudentsByGroupId() {
        return "SELECT name,login,email,password,phone_number,id_role FROM users JOIN students_details ON users.id = students_details.id_user WHERE students_details.id_class=?;";
    }
}