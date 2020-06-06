package data.statements;

public class ItemStatement {

    public String getAllItems() {
        return "SELECT * FROM items;";
    }

    public String getItemsByStudentId() {
        return "SELECT name,description,price,category FROM items JOIN students_items ON items.id = students_items.id_item WHERE students_items.id_student_details=?;";
    }

    public String getItemById() {
        return "SELECT * FROM items WHERE items.id = ?;";
    }

    public String getItemByName() {
        return "SELECT * FROM items WHERE items.name=?;";
    }

    public String getItemsByCategory() {
        return "SELECT * FROM items WHERE items.category=?;";
    }

    public String deleteItemStatement() {
        return "DELETE FROM items WHERE items.id=?;";
    }

    public String addItemStatement() {
        return "INSERT INTO (name,description,price,category) VALUES (?,?,?,?);";
    }

    public String updateQueryStatement() {
        return "UPDATE items SET name=?,description=?,price=?,category=? WHERE items.id=?;";
    }
}
