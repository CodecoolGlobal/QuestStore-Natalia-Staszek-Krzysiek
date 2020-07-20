/*
package dao.SQL;

import dao.UserClassDAO;

import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.Collections;

public class SQLUserGroupDao extends Database_Connection implements UserClassDAO {


    @Override
    public boolean add(int groupID, int mentorID) {

        String sqlStatement = "INSERT INTO users_classes (id_class,id_user) VALUES (?,?);";
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(groupID, mentorID), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean delete(int groupID, int mentorID) {
        String sqlStatement = "DELETE FROM users_classes WHERE id_class = ? AND id_user = ?;";
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(groupID, mentorID), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean deleteBy(int mentorID) {
        String sqlStatement = "DELETE FROM user_classes WHERE id_user = ?;";
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(mentorID), sqlStatement);
        return update(statement);
    }
}
*/
