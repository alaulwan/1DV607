package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Boat;
import model.Boat.Type;
import model.Member;
import yachtClub.Main;

public class AddMemberPopup {
	
	@SuppressWarnings("unchecked")
	public void createPopup(Member member) {
		BorderPane root=new BorderPane();
		Stage stage=new Stage();
		stage.setTitle("Add Member");
		Button done = new Button("Done!");
		done.setId("btnLogin");
		
		Button addBoat = new Button("Add Boat");
		addBoat.setId("btnLogin");
		Button removeBoat = new Button("Remove Boat");
		removeBoat.setId("btnLogin");
		removeBoat.setDisable(true);
		Button editBoat = new Button("Update Boat");
		editBoat.setId("btnLogin");
		editBoat.setDisable(true);
		
		TextField name = new TextField();
		Text nam = new Text(" Name:");
		TextField PersonalNumber = new TextField();
		Text pn = new Text(" PersonalNumber:\nYYMMDD-*");
		TextField userName = new TextField();
		Text UserName = new Text(" User Name:");
		PasswordField password = new PasswordField();
		Text Password = new Text(" Password:");
		Text bl = new Text(" Boat Length:");
		Text bt = new Text(" Boat Type:");
		TextField BoatLength = new TextField();
		 ChoiceBox<Type> cb = new ChoiceBox<Type>();
		 cb.getItems().addAll(Type.kayak, Type.Motorsailer, Type.Sailboat, Type.Other);
		 
		 ObservableList<Boat> boatsData = FXCollections.observableArrayList();
		   TableView<Boat> table = new TableView<Boat>(boatsData);
		   TableColumn<Boat, Type> boatType = new TableColumn<Boat, Type>("Type");
		   boatType.setCellValueFactory( new PropertyValueFactory<Boat, Type>("Type"));
	       TableColumn<Boat, String> bpn = new TableColumn<Boat, String>("Personal Number");
	       TableColumn<Boat, Integer> boatId = new TableColumn<Boat, Integer>("id");
	       boatId.setCellValueFactory( new PropertyValueFactory<Boat, Integer>("Id"));
	       TableColumn<Boat, Double> boatLength = new TableColumn<Boat, Double>("Length");
	       boatLength.setCellValueFactory( new PropertyValueFactory<Boat, Double>("Length"));
	        table.getColumns().addAll(boatId,boatType, boatLength, bpn );
	        table.setPrefWidth(225);
	        table.setMaxWidth(400);
	        boatType.setPrefWidth(75);
	        bpn.setPrefWidth(75);
	        boatLength.setPrefWidth(75);
	        boatId.setPrefWidth(75);
	        bpn.setVisible(false);
	        final VBox vbox = new VBox();
	        vbox.setSpacing(5);
	        vbox.setPadding(new Insets(10, 0, 0, 10));
	        vbox.getChildren().add(table);
		VBox container = new VBox(5);
		container.getChildren().addAll(nam,name,pn,PersonalNumber,UserName,userName, Password, password,bl,BoatLength,bt,cb,addBoat,removeBoat,editBoat,done,table);
		HBox buttom = new HBox(5);
		buttom.getChildren().addAll();
		root.setBottom(buttom);
		VBox center = new VBox();
		center.getChildren().add(table);
		root.setCenter(center);
		VBox left = new VBox(5);
		left.getChildren().addAll(nam,name,pn,PersonalNumber,UserName,userName, Password, password,bl,BoatLength,bt,cb,addBoat,editBoat, removeBoat,done);
		root.setLeft(left);
		table.setId("table-view");
		Scene scene = new Scene(root,500,500);
		stage.setScene(scene);
		root.setId("bp");
		 scene.getStylesheets().add(getClass().getClassLoader().
				 getResource("login.css").toExternalForm());
	     
		 cb.setValue(Type.Other);
		 if (member!=null) {
			 boatsData.addAll(FXCollections.observableArrayList(member.getBoatList()));
			 name.setText(member.getName());
			 PersonalNumber.setText(member.getPersonalNumber());
			 userName.setText(member.getUserName());
			 password.setText(member.getPassword());
			 stage.setTitle("View/Edit Member");
		 }
		 
		 if (Main.yachtClub.logedInUser ==null || (Main.yachtClub.logedInUser != member && !(Main.yachtClub.logedInUser instanceof model.Secretary)) ) {
			 root.setDisable(true);
			 /*addBoat.setVisible(false);
			 removeBoat.setVisible(false);
			 editBoat.setVisible(false);
			 done.setVisible(false);*/
			 
		 }
		 done.setOnAction(new EventHandler<ActionEvent>()
	        {
	            public void handle(ActionEvent e)
	            {	
	            	InputVerifier inputVerifier = new view.InputVerifier();
	            	if (inputVerifier.isCorrectMember(name.getText(), PersonalNumber.getText(), userName.getText(), password.getText(), member)) {
	            		Member newMember = new Member (name.getText(), PersonalNumber.getText(),userName.getText(), password.getText());
		            	
		            	newMember.setBoatList(boatsData);
		            	if (member!=null) {
		            		member.copyOf(newMember);
		            		for (Boat b : member.getBoatList())
			            		b.setOwnerId(member.getId());
		            	}
		            	else {
		            		for (Boat b : newMember.getBoatList())
			            		b.setOwnerId(newMember.getId());
		            		Main.yachtClub.addMember(newMember);
		            	}
		                stage.close();
	            	}
	            	
	            	
	            }
	        });
		 addBoat.setOnAction(new EventHandler<ActionEvent>()
	        {
	            public void handle(ActionEvent e)
	       
	            {  
	            	InputVerifier inputVerifier = new view.InputVerifier();
            		if (inputVerifier.isCorrectBoat(BoatLength.getText())) {
            			Boat boat = new Boat((Type)cb.getValue(),Double.valueOf(BoatLength.getText()),0);
	 	            	 boatsData.add(boat);
            			
            		}
	            	/*try {//Integer.parseInt(PersonalNumber.getText());
	            	    	 
	            	         //name.clear();PersonalNumber.clear();BoatLength.clear();
	            	     }
	            	
	            	catch (NumberFormatException e1) {
	            	     //Not an integer
	            		Alert alert = new Alert(AlertType.ERROR, "Stop outsmarting dude! ");
	            		alert.showAndWait();
	            	}*/
	            	
	            }
	        });
		 removeBoat.setOnAction(new EventHandler<ActionEvent>()
	        {
	            public void handle(ActionEvent e)
	            {
	            	Boat boat = (Boat)  table.getSelectionModel().getSelectedItem();
	            	boatsData.remove(boat);
	            	//Main.yachtClub.getMemberList().getMember(member.getId()).getBoats().getBoats().remove(boat);
	            	
	            }
	        });
		 editBoat.setOnAction(new EventHandler<ActionEvent>()
	        {
	            public void handle(ActionEvent e)
	            {	
	            	InputVerifier inputVerifier = new view.InputVerifier();
            		if (inputVerifier.isCorrectBoat(BoatLength.getText())) {
            			Boat boat = (Boat)  table.getSelectionModel().getSelectedItem();
    	            	boat.setType(cb.getValue());
    	            	boat.setLength(Double.valueOf(BoatLength.getText()));
    	            	table.refresh();
            		}
	            	
	            	
	            }
	        });
		 table.setOnMouseClicked(event1 -> {
			 if (table.getSelectionModel().getSelectedItem()!=null) {
				 editBoat.setDisable(false);
				 removeBoat.setDisable(false);
				 BoatLength.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getLength()));
				 cb.setValue(table.getSelectionModel().getSelectedItem().getType());
			 }
		 });
		stage.showAndWait();
	    
	}
	
}
