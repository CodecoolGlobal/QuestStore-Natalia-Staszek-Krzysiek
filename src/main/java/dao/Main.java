package dao;

import model.Codecooler;
import model.Creep;
import model.Mentor;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        CreepDao creepDao = new CreepDao("fbznochzdwosyl", "95a8e2f8c07cf64b80500a788f87ec46f1d2eaf6b2b542034f25f4e0311b0e2f", "d3nuc8s3988iho");
        MentorDao mentorDao = new MentorDao("fbznochzdwosyl", "95a8e2f8c07cf64b80500a788f87ec46f1d2eaf6b2b542034f25f4e0311b0e2f", "d3nuc8s3988iho");
        CodecoolerDao codecoolerDao = new CodecoolerDao("fbznochzdwosyl", "95a8e2f8c07cf64b80500a788f87ec46f1d2eaf6b2b542034f25f4e0311b0e2f", "d3nuc8s3988iho");

//        dao.connect();
//        mentorDao.extractor(mentorDao.resultSet(mentorDao.queryBuilder("users")));
//        creepDao.extractor(creepDao.resultSet(creepDao.queryBuilder("users")));
//        codecoolerDao.extractor(codecoolerDao.resultSet(codecoolerDao.queryBuilder("users")));
//        dao.disconnect();

        Creep creeper = new Creep("Jarek", "jarek3","jarek@onet.pl", "135456","335/882/852", 2 );



<<<<<<< HEAD
        dao.connect();
        dao.userExtractor(dao.resultSet(dao.queryBuilder("users")));
        dao.disconnect();
=======
        creepDao.injector(creeper);
>>>>>>> d280d076577dbd96326d13cbb450827ee2c2c4b3

    }

}
