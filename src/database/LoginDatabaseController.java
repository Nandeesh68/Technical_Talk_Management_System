package database;

import resources.Resources;
import resources.UserInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDatabaseController {


    public Connection getConnection() {

        String url = "jdbc:mysql://localhost:3306/login_credentials";
        String name = "root";
        String passwd = "mysql";
        Connection conn = null;
        try {
            //
            conn = DriverManager.getConnection(url, name, passwd);
            if (conn != null) {
                System.out.println("Connected successfully");
            }
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getLoginList() {
        Connection connection = getConnection();
        String query = "SELECT * FROM login ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            UserInfo user;
            while (rs.next()) {
                user = new UserInfo(rs.getString("userName"), rs.getString("password"));
                Resources.loginDetails.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
