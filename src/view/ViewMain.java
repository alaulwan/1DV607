package view;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Member;
import yachtClub.Main;
 
public class ViewMain{
 
    private TableView<Member> table = new TableView<Member>();
    private final ObservableList<Member> data =
            FXCollections.observableArrayList(Main.yachtClub.getMemberList().getMembers());
    final HBox hb = new HBox();
    @SuppressWarnings("unchecked")
    public void View(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("The yacht club");
        stage.setWidth(450);
        stage.setHeight(550);
        table.setId("table-view");
        final Label label = new Label("Member's list");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
 
        @SuppressWarnings("rawtypes")
		TableColumn firstNameCol = new TableColumn("Member Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
            new PropertyValueFactory<Member, String>("name"));
        /*firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Member, String>>() {
                @Override
                public void handle(CellEditEvent<Member, String> t) {
                    ((Member) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setName(t.getNewValue());
                }
            }
        )*/;
 
 
        @SuppressWarnings("rawtypes")
		TableColumn lastNameCol = new TableColumn("MemberID");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
            new PropertyValueFactory<Member, String>("Id"));
        /*lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Member, String>>() {
                @Override
                public void handle(CellEditEvent<Member, String> t) {
                    ((Member) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setId(Integer.valueOf(t.getNewValue()));
                }
            }
        )*/;
 
        @SuppressWarnings("rawtypes")
		TableColumn emailCol = new TableColumn("Num boats");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
            new PropertyValueFactory<Member, String>("NumberOfBoats"));
        /*emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Member, String>>() {
                @Override
                public void handle(CellEditEvent<Member, String> t) {
                    ((Member) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setNumberOfBoats(t.getNewValue());
                }
            }
        )*/;
 
        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
 
        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("Member Name");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("ID");
        final TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Number of Boats");
 
        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new Member(
                        addFirstName.getText(),
                        addLastName.getText()/*,
                        addEmail.getText()*/));
                addFirstName.clear();
                addLastName.clear();
                addEmail.clear();
            }
        });
        final Button VerboseList = new Button("VerboseList");
        VerboseList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
               System.out.println("Not implemented yet");
            }
        });
        VerboseList.setId("btn");
        addButton.setId("btn");
        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton,VerboseList);
        hb.setSpacing(3);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);
        hb.setId("tab");
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        scene.getStylesheets().add("/view/style.css");
        stage.setScene(scene);
        stage.show();
    }
    }
