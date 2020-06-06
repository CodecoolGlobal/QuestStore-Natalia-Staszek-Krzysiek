package data.statements;

public class ItemStatement {

    public String getAllItems() {
        return "SELECT * FROM items;";
    }

    public String getItemsByStudentId() {
        return "SELECT name,description,price,category FROM items JOIN students_items ON items.id = students_items.id_item WHERE students_items.id_student_details=?;";
    }

    public String getItemById() {
        return "SELECT * FROM items WHERE id = ?;";
    }

    public String getItemByName() {
        return "SELECT * FROM items WHERE name=?;";
    }

    public String getItemsByCategory() {
        return "SELECT * FROM items WHERE category=?;";
    }

    public String deleteItemStatement() {
        return "DELETE FROM items WHERE id=?;";
    }

    public String addItemStatement() {
        return "INSERT INTO items (name,description,price,category) VALUES (?,?,?,?);";
    }

    public String updateQueryStatement() {
        return "UPDATE items SET name=?,description=?,price=?,category=? WHERE id=?;";
    }
}
