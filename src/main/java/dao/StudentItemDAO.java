package dao;

import java.util.List;

public interface StudentItemDAO {

    List<Integer> getStudentItemsIdsBy(int studentID);
    boolean add(int studentId, int itemId, boolean isUsed);
    boolean markItemAsUsed(int studentId, int itemId);
    boolean removeTeamItems();
}
