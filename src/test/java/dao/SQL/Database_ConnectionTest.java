//package dao.SQL;
//
//import dao.UserDAO;
//import model.User;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.internal.util.reflection.Fields;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import static org.mockito.Matchers.anyInt;
//import static org.mockito.Matchers.anyString;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//
//class Database_ConnectionTest {
//
//    @Mock
//    DataSource mockDataSource;
//    @Mock
//    Connection mockConn;
//    @Mock
//    PreparedStatement mockPreparedStmnt;
//    @Mock
//    ResultSet mockResultSet;
//    int userId = 100;
//
//    public Database_ConnectionTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() throws Exception {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @BeforeEach
//    void setUp() throws SQLException {
//        when(mockDataSource.getConnection()).thenReturn(mockConn);
//        when(mockDataSource.getConnection(anyString(), anyString())).thenReturn(mockConn);
//        doNothing().when(mockConn).commit();
//        when(mockConn.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStmnt);
//        doNothing().when(mockPreparedStmnt).setString(anyInt(), anyString());
//        when(mockPreparedStmnt.execute()).thenReturn(Boolean.TRUE);
//        when(mockPreparedStmnt.getGeneratedKeys()).thenReturn(mockResultSet);
//        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    public void testCreateWithNoExceptions() throws SQLException {
//
//        UserDAO instance = new UserDAO(mockDataSource);
//        instance.create(new User());
//
//        //verify and assert
//        verify(mockConn, times(1)).prepareStatement(anyString(), anyInt());
//        verify(mockPreparedStmnt, times(6)).setString(anyInt(), anyString());
//        verify(mockPreparedStmnt, times(1)).execute();
//        verify(mockConn, times(1)).commit();
//        verify(mockResultSet, times(2)).next();
//        verify(mockResultSet, times(1)).getInt(Fields.GENERATED_KEYS);
//    }
//
//}