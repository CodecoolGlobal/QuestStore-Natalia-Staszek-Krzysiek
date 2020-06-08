package dao.SQL;

import services.StatisticService;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

//        CreepDao creepDao = new CreepDao("fbznochzdwosyl", "95a8e2f8c07cf64b80500a788f87ec46f1d2eaf6b2b542034f25f4e0311b0e2f", "d3nuc8s3988iho");
//        MentorDao mentorDao = new MentorDao("fbznochzdwosyl", "95a8e2f8c07cf64b80500a788f87ec46f1d2eaf6b2b542034f25f4e0311b0e2f", "d3nuc8s3988iho");
//        CodecoolerDao codecoolerDao = new CodecoolerDao("fbznochzdwosyl", "95a8e2f8c07cf64b80500a788f87ec46f1d2eaf6b2b542034f25f4e0311b0e2f", "d3nuc8s3988iho", itemDao, questDao);
//
//        dao.connect();
//        mentorDao.extractor(mentorDao.resultSet(mentorDao.queryBuilder("users")));
//        creepDao.extractor(creepDao.resultSet(creepDao.queryBuilder("users")));
//        codecoolerDao.extractor(codecoolerDao.resultSet(codecoolerDao.queryBuilder("users")));
//        dao.disconnect();
//
//        Creep creeper = new Creep("Jozef", "jarek3","czarek@onet.pl", "135456","335/882/852", 2 );
//


//        creepDao.injector(creeper);
//        System.out.println(creepDao.isUserDataCorrect("Lepper", "123456"));

        SQLUserDao sqlUserDao = new SQLUserDao();
        System.out.println(sqlUserDao.getAll());

        System.out.println(new StatisticService().getAllQuests());



    }

}
