package screens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import resources.Resources;
import resources.UserInfo;

import java.io.IOException;

public class LoginControler
{
    @FXML
    private Button login;
    @FXML
    private TextField userName;
    @FXML
    private TextField password;





    public void verifyUser(ActionEvent ae) {

        boolean valid = false;
        for (UserInfo user: Resources.loginDetails) {

            if(user.getUserName().equalsIgnoreCase(userName.getText()) && user.getPassword().equals(password.getText())){
                valid = true;
            }
        }

        if(valid){
            try {
                GridPane root= FXMLLoader.load(getClass().getResource("TalksGUI.fxml"));
                GridPane root1= FXMLLoader.load(getClass().getResource("allMeetings.fxml"));

                root.add(root1,1,1);
                login.getScene().setRoot(root);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Details");
            alert.setContentText("Please enter the correct details");
            alert.show();
        }
    }


    public void clearTextField(){
        userName.clear();
        password.clear();
    }



}
