package screens;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import resources.Resources;
import resources.TalkInfo;




public class DeleteController {

    @FXML
    private TableColumn<TalkInfo, String> academicYear;

    @FXML
    private TableColumn<TalkInfo, String> dateTime;

    @FXML
    private TableColumn<TalkInfo, String> duration;

    @FXML
    private TableColumn<TalkInfo, String> keypoints;

    @FXML
    private TableColumn<TalkInfo, String> personName;

    @FXML
    private TableColumn<TalkInfo, Integer> serialNo;

    @FXML
    private TableView<TalkInfo> tableView;

    @FXML
    private TableColumn<TalkInfo, String> topicTitle;

    @FXML
    private ComboBox<String> searchDrop;

    @FXML
    private TextField searchBox;

    @FXML
    private TableColumn<TalkInfo, Void> delete;

    @FXML
    private GridPane updateGrid;



    public void initialize() {

        ObservableList<String> options =
                FXCollections.observableArrayList( "Serial No.",
                        "Resource Person Name",
                        "Title"
                );
        searchDrop.setItems(options);

        serialNo.setCellValueFactory(new PropertyValueFactory<TalkInfo, Integer>("serialNo"));
        academicYear.setCellValueFactory(new PropertyValueFactory<TalkInfo, String>("academicYear"));
        dateTime.setCellValueFactory(new PropertyValueFactory<TalkInfo, String>("dateTime"));
        duration.setCellValueFactory(new PropertyValueFactory<TalkInfo, String>("duration"));
        personName.setCellValueFactory(new PropertyValueFactory<TalkInfo, String>("personName"));
        topicTitle.setCellValueFactory(new PropertyValueFactory<TalkInfo, String>("topicTitle"));
        keypoints.setCellValueFactory(new PropertyValueFactory<TalkInfo, String>("keyPoints"));


        personName.setCellFactory(param -> {
            return new TableCell<TalkInfo, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        this.setText(null);
                        this.setStyle("");
                        this.setGraphic(null);
                    } else {
                        Text text = new Text(item);
                        text.setFont(Font.font(15));
                        text.setWrappingWidth(param.getPrefWidth() - 35);
                        //the only change that i make
                        this.setMinHeight(60.0);
                        this.setPrefHeight(text.getLayoutBounds().getHeight()+10);
                        this.setGraphic(text);
                    }
                }
            };
        });
        topicTitle.setCellFactory(param -> {
            return new TableCell<TalkInfo, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        this.setText(null);
                        this.setStyle("");
                        this.setGraphic(null);
                    } else {
                        Text text = new Text(item);
                        text.setFont(Font.font(15));
                        text.setWrappingWidth(param.getPrefWidth() - 35);
                        //the only change that i make
                        this.setMinHeight(60.0);
                        this.setPrefHeight(text.getLayoutBounds().getHeight()+10);
                        this.setGraphic(text);
                    }
                }
            };
        });
        keypoints.setCellFactory(param -> {
            return new TableCell<TalkInfo, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        this.setText(null);
                        this.setStyle("");
                        this.setGraphic(null);
                    } else {
                        Text text = new Text(item);
                        text.setFont(Font.font(15));
                        text.setWrappingWidth(param.getPrefWidth() - 35);
                        //the only change that i make
                        this.setMinHeight(60.0);
                        this.setPrefHeight(text.getLayoutBounds().getHeight()+10);
                        this.setGraphic(text);
                    }
                }
            };
        });
        Callback<TableColumn<TalkInfo, Void>, TableCell<TalkInfo, Void>> cellFactory = new Callback<TableColumn<TalkInfo, Void>, TableCell<TalkInfo, Void>>() {
            @Override
            public TableCell<TalkInfo, Void> call(final TableColumn<TalkInfo, Void> param) {
                final TableCell<TalkInfo, Void> cell = new TableCell<TalkInfo, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setStyle("-fx-background-color:  #0e84f1; ");
                        btn.setTextFill(Color.WHITE);
                        btn.setOnAction((ActionEvent event) -> {
                            TalkInfo data = getTableView().getItems().get(getIndex());
                            ScheduleMeetingController scheduleMeetingController = new ScheduleMeetingController();
                            scheduleMeetingController.deleteMeetingDetails(data,updateGrid);

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        delete.setCellFactory(cellFactory);

        FilteredList<TalkInfo> talkInfos = new FilteredList<TalkInfo>(FXCollections.observableArrayList(Resources.talksDetails), p->true);
        tableView.setItems(talkInfos);

        searchBox.textProperty().addListener((obs,oldValue,newValue) -> {
            switch (searchDrop.getValue().toString()){
                case "Serial No.":
                    talkInfos.setPredicate(p -> p.getSerialNo().toString().contains(newValue.trim()));
                    break;

                case "Resource Person Name":
                    talkInfos.setPredicate(p -> p.getPersonName().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;

                case "Title":
                    talkInfos.setPredicate(p -> p.getTopicTitle().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;

            }
        });



    }



}
