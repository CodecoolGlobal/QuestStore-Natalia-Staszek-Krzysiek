package dao.SQL;

import model.Codecooler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDao implements JdbcDao<Codecooler> {
    ItemDao itemDao;

    @Override
    public Codecooler get(int id) {
        connect();
        Codecooler codecooler = getCodecoolerById(id);
        disconnect();
        return codecooler;
    }

    private Codecooler getCodecoolerById(int id) {
        try {
            String sql = "SELECT * FROM CODECOOLER WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return readCodecoolerFromResultSet(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private Codecooler readCodecoolerFromResultSet(ResultSet resultSet) throws SQLException {
        Codecooler codecooler = new Codecooler();
        int id = resultSet.getInt("id");
        codecooler.setId(id);
        codecooler.setName(resultSet.getString("name"));
        codecooler.setLogin(resultSet.getString("login"));
        codecooler.setEmail(resultSet.getString("email"));
        codecooler.setPassword(resultSet.getString("password"));
        codecooler.setPhoneNumber(resultSet.getString("phone_number"));
        codecooler.setRole(resultSet.getInt("id_role"));
        codecooler.setExperience(resultSet.getInt("experience"));
        codecooler.setLevel(resultSet.getInt("level"));
        codecooler.setWallet(resultSet.getInt("wallet"));
//        codecooler.setBoughtItems(itemDao.getAllById(id));
        return codecooler;
    }

    @Override
    public List<Codecooler> getAll() {
        return null;
    }

    @Override
    public boolean add(Codecooler codecooler) {
        return false;
    }

    @Override
    public boolean update(Codecooler codecooler) {
        return false;
    }

    @Override
    public boolean remove(Codecooler codecooler) {
        return false;
    }
}
