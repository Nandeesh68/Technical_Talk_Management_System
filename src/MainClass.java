import java.io.IOException;
import java.util.ArrayList;

import database.DatabaseController;
import database.LoginDatabaseController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.LoginInfo;
import resources.Resources;
import resources.TalkInfo;
import resources.TalksList;
import screens.LoginControler;


public class MainClass extends Application{

    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("Technical Talk Management System");

        DatabaseController dc = new DatabaseController();
        dc.getTalksList();
        LoginDatabaseController lc = new LoginDatabaseController();
        lc.getLoginList();

        Parent root= FXMLLoader.load(getClass().getResource("screens/LoginGUI.fxml"));

        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
