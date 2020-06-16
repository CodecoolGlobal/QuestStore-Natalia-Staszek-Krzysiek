package dao.SQL;

import dao.UserClassDAO;
import data.Database_Connection;
import data.statements.UserClassStatement;

import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.Collections;

public class SQLUserGroupDao extends Database_Connection implements UserClassDAO {

    private UserClassStatement userClassStatement = new UserClassStatement();

    @Override
    public boolean add(int groupID, int mentorID) {

        String sqlStatement = userClassStatement.insertConnectionStatement();
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(groupID, mentorID), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean delete(int groupID, int mentorID) {
        String sqlStatement = userClassStatement.deleteConnectionStatement();
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(groupID, mentorID), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean deleteBy(int mentorID) {
        String sqlStatement = userClassStatement.deleteConnectionStatementByMentorID();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(mentorID), sqlStatement);
        return update(statement);
    }
}
