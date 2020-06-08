package data.statements;

public class StudentItemStatement {

    public String addStudentItemConnection() {
        return "INSERT INTO students_items (id_item,id_student_details,is_used) VALUES (?,?,?);";
    }

    public String getStudentsItems() {
        return "SELECT id_item FROM students_items WHERE id_student_details=?;";
    }

    public String markItemAsUsed() {
        return "UPDATE students_items SET is_used = true WHERE id_student_details = ? AND id_item = ?;";
    }

    public String deleteTeamItemsStatement() {
        return "DELETE FROM students_items WHERE students_items.id_item IN SELECT id FROM items JOIN students_items ON items.id = students_items.id_item WHERE items.id_category = ?;";
    }

}
