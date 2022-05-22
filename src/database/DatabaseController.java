package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import resources.LoginInfo;
import resources.Resources;
import resources.TalkInfo;
import resources.UserInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class DatabaseController {

    public void insertTalk(Integer serialNo, String academicYear, String dateTime, String duration, String personName, String topicTitle, String keyPoints) {
        String query = "insert into talks values("+serialNo+",'"+academicYear+"','"+dateTime+"','"+duration+"','"+personName+"','"+topicTitle+"','"+keyPoints+"')";
        executeQuery(query);
    }
    public void updateTalk(Integer serialNo, String academicYear, String dateTime, String duration, String personName, String topicTitle, String keyPoints) {
        String query = "UPDATE talks SET academicYear='"+academicYear+"',dateTime='"+dateTime+"',duration='"+duration+"',personName='"+personName+"',topicTitle='"+topicTitle+"',keyPoints='"+keyPoints+"' WHERE serialNo="+serialNo+"";
        executeQuery(query);
    }

    public void deleteTalk(Integer serialNo) {
        String query = "DELETE FROM talks WHERE serialNo="+serialNo+"";
        executeQuery(query);
    }

    public void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {

        String url="jdbc:mysql://localhost:3306/technical_talks";
        String name="root";
        String passwd="mysql";
        Connection conn=null;
        try {
            //
            conn = DriverManager.getConnection(url,name,passwd);
            if(conn != null){
                System.out.println("Connected successfully");
            }
            return conn;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void getTalksList(){
        Connection connection = getConnection();
        String query = "SELECT * FROM talks ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            TalkInfo talk;
            while(rs.next()) {
                talk = new TalkInfo(rs.getInt("serialNo"),rs.getString("academicYear"),rs.getString("dateTime"),rs.getString("duration"),rs.getString("personName"),rs.getString("topicTitle"),rs.getString("keypoints"));
                Resources.talksDetails.add(talk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
