package data.statements;

public class StudentDetailsStatement {

    public String getStudentDataById() {
        return "SELECT * FROM students_details WHERE students_details.id_user=?";
    }

    public String createStudentData() {
        return "INSERT INTO students_details (id_team,level,wallet,experience) VALUES (?,?,?,?,?,?);";
    }

    public String updateStudentData() {
        return "UPDATE students_details SET id_team=?,level=?,wallet=?,experience=?;";
    }

    public String getStudentsInSameTeam() {
        return "SELECT * FROM students_details WHERE students_details.id_team=?;";
    }

    public String getAllStudents() {
        return "SELECT * FROM students_details;";
    }

}
