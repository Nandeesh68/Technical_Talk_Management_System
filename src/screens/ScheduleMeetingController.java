package screens;


import database.DatabaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.converter.LocalDateStringConverter;
import resources.Resources;
import resources.TalkInfo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ScheduleMeetingController{

    @FXML
    private GridPane gridPane1;


    @FXML
    private TextField acYear;


    @FXML
    private DatePicker meetDate;

    @FXML
    private TextField meetTime;

    @FXML
    private TextArea points;

    @FXML
    private TextField resPersonName;

    @FXML
    private ComboBox<String> timeDrop;

    @FXML
    private TextField title;

    @FXML
    private TextField hour;

    @FXML
    private TextField minute;

    @FXML
    private ComboBox<String> timestat;


    DatabaseController dc = new DatabaseController();
    public void intializeDropdown(){
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "hours",
                        "minutes"
                );
        timeDrop.setItems(options);
    }

    public void intializeDropdown1(){
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "AM",
                        "PM"
                );
        timestat.setItems(options);
    }

    public void schedular(){
      
        try {
            GridPane root1 = FXMLLoader.load(getClass().getResource("scheduleMeeting.fxml"));
            Node node = gridPane1.getChildren().get(4);
              GridPane pane = (GridPane) node;
              gridPane1.getChildren().remove(pane);

              gridPane1.add(root1,1,1);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void updateMeeting(){

        try {
            GridPane root1 = FXMLLoader.load(getClass().getResource("searchMeeting.fxml"));
            Node node = gridPane1.getChildren().get(4);
            GridPane pane = (GridPane) node;
            gridPane1.getChildren().remove(pane);

            gridPane1.add(root1,1,1);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void displayTable(){
        try {
            GridPane root1 = FXMLLoader.load(getClass().getResource("allMeetings.fxml"));
            Node node = gridPane1.getChildren().get(4);
            GridPane pane = (GridPane) node;
            gridPane1.getChildren().remove(pane);
            
            gridPane1.add(root1,1,1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void dispalyDeleteTab(){
        try {
            GridPane root1 = FXMLLoader.load(getClass().getResource("deleteMeeting.fxml"));
            Node node = gridPane1.getChildren().get(4);
            GridPane pane = (GridPane) node;
            gridPane1.getChildren().remove(pane);

            gridPane1.add(root1,1,1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void logout(){

        try{
            GridPane root1 = FXMLLoader.load(getClass().getResource("LoginGUI.fxml"));
            Scene sc = gridPane1.getScene();
            sc.setRoot(root1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createMeeting(ActionEvent ae){

        LocalDate isoDate = meetDate.getValue();
        if(acYear.getText().isEmpty() || isoDate == null || meetTime.getText().isEmpty() || hour.getText().isEmpty() || minute.getText().isEmpty() || timeDrop.getSelectionModel().isEmpty() || resPersonName.getText().isEmpty() || title.getText().isEmpty()){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Details");
            alert.setContentText("Please enter the correct details");
            alert.show();
        }
        else {
            TalkInfo num = Resources.talksDetails.get(Resources.talksDetails.size()-1);
            TalkInfo temp = new TalkInfo((num.getSerialNo()+1),acYear.getText(),meetDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"  "+hour.getText()+":"+minute.getText()+" "+timestat.getValue(),meetTime.getText()+" "+timeDrop.getValue(),resPersonName.getText(),title.getText(),points.getText());

            Resources.talksDetails.add(temp);
            dc.insertTalk(temp.getSerialNo(),temp.getAcademicYear(),temp.getDateTime(),temp.getDuration(),temp.getPersonName(),temp.getTopicTitle(),temp.getKeyPoints());
            acYear.clear();
            meetDate.getEditor().clear();
            hour.clear();
            minute.clear();
            timestat.setValue("AM");
            timeDrop.setValue("--Select--");
            meetTime.clear();
            resPersonName.clear();
            title.clear();
            points.clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Meeting Scheduled");
            alert.setContentText("Meeting scheduled Successfully!!");
            alert.show();

        }



    }
    public void clearFields(){
        acYear.clear();
        meetDate.getEditor().clear();
        hour.clear();
        minute.clear();
        timestat.setValue("AM");
        timeDrop.setValue("--Select");
        meetTime.clear();
        resPersonName.clear();
        title.clear();
        points.clear();
    }

    public void deleteMeetingDetails(TalkInfo data,GridPane deletePane){

        int i=1,flag =0;
        TalkInfo buffer = new TalkInfo();
        for(TalkInfo info: Resources.talksDetails){

            if(data.getSerialNo().equals(info.getSerialNo())){
                buffer = info;
                continue;
            }
            else{
                info.setSerialNo(i);
            }
              i++;

        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Meeting deleted");
        alert.setContentText("Meeting deleted Successfully!!");
        alert.show();

        Resources.talksDetails.remove(buffer);
        dc.deleteTalk(buffer.getSerialNo());

        GridPane pane1 = (GridPane) deletePane.getScene().getRoot();

        GridPane root3 = null;
        try {
            root3 = FXMLLoader.load(getClass().getResource("allMeetings.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node node2 = pane1.getChildren().get(4);
        GridPane pane2 = (GridPane) node2;
        pane1.getChildren().remove(pane2);

        pane1.add(root3,1,1);
    }
    public void updateMeetingDetails(TalkInfo data,GridPane updatePane){

        String temp1[] = data.getDuration().split(" ");
        try{
            GridPane root = (GridPane) updatePane.getScene().getRoot();
            GridPane root1 = FXMLLoader.load(getClass().getResource("scheduleMeeting.fxml"));
            Node node = root.getChildren().get(4);
            GridPane pane = (GridPane) node;
            root.getChildren().remove(pane);


            GridPane timebox = (GridPane) root1.getChildren().get(13);
            GridPane boxNodes = (GridPane) root1.getChildren().get(12);
            GridPane buttonsGrid = (GridPane) root1.getChildren().get(11);
            for (Node boxNode : boxNodes.getChildren()){
                if(boxNode instanceof TextField) {
                    TextField tempField = (TextField) boxNode;
                    tempField.setText(temp1[0]);
                }

                if(boxNode instanceof ComboBox){
                    ComboBox tempBox = (ComboBox) boxNode;
                    tempBox.setValue(temp1[1]);
                }
            }
            String timeStr=data.getDateTime();
            String[] tmp1=timeStr.split("  ");
            String[] tmp2=tmp1[1].split(":");
            String[] tmp3=tmp2[1].split(" ");
            for (Node boxNode : timebox.getChildren()){
                if(boxNode instanceof DatePicker){
                    DatePicker tempDate = (DatePicker) boxNode;
                    tempDate.setValue(new LocalDateStringConverter().fromString(tmp1[0]));

                }
                if(boxNode instanceof TextField) {
                    TextField tempField = (TextField) boxNode;
                    if(tempField.getId().equals("hour"))
                        tempField.setText(tmp2[0]);
                    if(tempField.getId().equals("minute"))
                        tempField.setText(tmp3[0]);
                }

                if(boxNode instanceof ComboBox){
                    ComboBox tempBox = (ComboBox) boxNode;
                    tempBox.setValue(tmp3[1]);
                }
            }
            boolean flag = true;
            for (Node tempNode : root1.getChildren()) {
                
                if(tempNode instanceof Label && flag){
                    Label  lbl1 = (Label) tempNode;
                    if(lbl1.getId().equals("lblMeet")){
                        lbl1.setText("Update Meeting");
                        flag = false;
                    }

                }

                if(tempNode instanceof TextField){
                    TextField tempField = (TextField) tempNode;
                    if(tempField.getId().equals("acYear"))
                        tempField.setText(data.getAcademicYear());
                    if(tempField.getId().equals("resPersonName"))
                        tempField.setText(data.getPersonName());
                    if(tempField.getId().equals("title"))
                        tempField.setText(data.getTopicTitle());
                }

                if(tempNode instanceof  TextArea){
                    TextArea tempArea = (TextArea) tempNode;
                    tempArea.setText(data.getKeyPoints());
                }
            }
            for (Node boxNode : buttonsGrid.getChildren()){
                  Button btn = (Button) boxNode;

                if(btn.getId().equals("btnSubmit")){
                    btn.setOnAction(e -> {

                        for(TalkInfo info: Resources.talksDetails){
                            if(info.getSerialNo().equals(data.getSerialNo())){

                                String buffer[] = new String[2];
                                TalkInfo tempInfo = new TalkInfo();
                                String buffer1[] = new String[5];
                                for (Node boxNode1 : boxNodes.getChildren()){
                                    if(boxNode1 instanceof TextField) {
                                        TextField tempField = (TextField) boxNode1;
                                        buffer[0] = tempField.getText();

                                    }

                                    if(boxNode1 instanceof ComboBox){
                                        ComboBox tempBox = (ComboBox) boxNode1;
                                        buffer[1] = tempBox.getValue().toString();
                                        tempBox.setPromptText("--Select--");
                                    }
                                }

                                for (Node timeBoxNode : timebox.getChildren()){
                                    if(timeBoxNode instanceof DatePicker){
                                        DatePicker tempDate = (DatePicker) timeBoxNode;
                                        buffer1[0]=tempDate.getEditor().getText().trim();

                                    }
                                    if(timeBoxNode instanceof TextField) {
                                        TextField tempField = (TextField) timeBoxNode;
                                        if(tempField.getId().equals("hour"))
                                            buffer1[1] = tempField.getText().trim();
                                        if(tempField.getId().equals("minute"))
                                            buffer1[2] = tempField.getText().trim();
                                    }

                                    if(timeBoxNode instanceof ComboBox){
                                        ComboBox tempBox = (ComboBox) timeBoxNode;
                                        buffer1[3] = tempBox.getValue().toString();
                                    }
                                }
                                for (Node tempNode : root1.getChildren()) {
                                    if(tempNode instanceof TextField){
                                        TextField tempField = (TextField) tempNode;
                                        if(tempField.getId().equals("acYear"))
                                            tempInfo.setAcademicYear(tempField.getText());
                                        if(tempField.getId().equals("resPersonName"))
                                            tempInfo.setPersonName(tempField.getText());
                                        if(tempField.getId().equals("title")) {
                                            tempInfo.setTopicTitle(tempField.getText());

                                        }
                                    }
                                    if(tempNode instanceof  TextArea){
                                        TextArea tempArea = (TextArea) tempNode;
                                        tempInfo.setKeyPoints(tempArea.getText());
                                    }
                                }
                                tempInfo.setDateTime(buffer1[0]+"  "+buffer1[1]+":"+buffer1[2]+" "+buffer1[3]);
                                tempInfo.setDuration(buffer[0]+" "+buffer[1]);
                                if(tempInfo.getAcademicYear().isEmpty() || tempInfo.getDateTime().isEmpty() || buffer[0].isEmpty() || buffer[1].isEmpty()  ||tempInfo.getPersonName().isEmpty() || tempInfo.getTopicTitle().isEmpty()){

                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Invalid Details");
                                    alert.setContentText("Please enter the correct details");
                                    alert.show();
                                }
                                else{

                                    dc.updateTalk(data.getSerialNo(),tempInfo.getAcademicYear(),tempInfo.getDateTime(),tempInfo.getDuration(),tempInfo.getPersonName(),tempInfo.getTopicTitle(),tempInfo.getKeyPoints());
                                    info.setAcademicYear(tempInfo.getAcademicYear());
                                    info.setDateTime(tempInfo.getDateTime());
                                    info.setDuration(tempInfo.getDuration());
                                    info.setPersonName(tempInfo.getPersonName());
                                    info.setTopicTitle(tempInfo.getTopicTitle());
                                    info.setKeyPoints(tempInfo.getKeyPoints());
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Meeting updated");
                                    alert.setContentText("Meeting updated Successfully!!");
                                    alert.show();
                                    try {

                                        GridPane root2 = (GridPane) btn.getScene().getRoot();
                                        GridPane root3 = FXMLLoader.load(getClass().getResource("allMeetings.fxml"));
                                        Node node2 = root2.getChildren().get(4);
                                        GridPane pane1 = (GridPane) node2;
                                        root2.getChildren().remove(pane1);

                                        root2.add(root3,1,1);

                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }

                                }

                            }
                        }
                    });
                }
            }



            root.add(root1,1,1);
        }catch (Exception e){
            e.printStackTrace();
        }



    }

}


