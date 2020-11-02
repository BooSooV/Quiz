package engine.study;

import org.springframework.boot.SpringApplication;

import java.sql.*;

public class StudyMySQL {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String userName = "root";
        String password = "1234";
        String connectionUrl = "jdbc:mysql://localhost:3306/learnJDBC?useUnicode=true&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
            Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS REGISTRATION (id INTEGER not NULL, first VARCHAR(255), last VARCHAR(255), age INTEGER,  PRIMARY KEY ( id ));");
//            statement.executeUpdate(("insert into registration set id =2, first ='Ivan', last ='Petrov', age =25"));
            ResultSet resultSet = statement.executeQuery("select * from registration");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("first"));
                System.out.println(resultSet.getString("last"));
                System.out.println(resultSet.getInt("age"));
                System.out.println("----------------------");
            }
        }
    }
}


