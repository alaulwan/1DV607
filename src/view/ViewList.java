package view;


import java.io.File;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import model.Member;
import model.MemberFilter;
import model.YachtClubDAO;
import yachtClub.Main;

public class ViewList {
	   BorderPane bp = new BorderPane();
	   AddMemberPopup ading = new AddMemberPopup();
	   ObservableList<Member> data = FXCollections.observableArrayList(Main.yachtClub.getMemberList());
	   private  TableView<Member> table = new TableView<Member>(data);
	   TableColumn<Member, String> name = new TableColumn<Member, String>("Member Name");
       TableColumn<Member, String> pn = new TableColumn<Member, String>("Personal Number");
       TableColumn<Member, Integer> id = new TableColumn<Member, Integer>("Member id");
       TableColumn<Member, Integer> boats = new TableColumn<Member, Integer>("Number Of Boats");
       TextField input = new TextField();
      
       
	 public  BorderPane BorderPane() {
    table.setId("table-view");
    table.setRowFactory(tableView -> { final TableRow<Member> row = new TableRow<>(); 
   getmenu(row);
    return row; });
    buildTop();
    buildLeft();
    buildRight();
    buildtabelview();
    bp.setId("bp");
    initialize();
	return bp;
}
	private void initialize() {
	    	   name.setCellValueFactory( new PropertyValueFactory<Member, String>("name"));
	    	   pn.setCellValueFactory(new PropertyValueFactory<Member, String>("personalNumber"));
	    	   boats.setCellValueFactory(new PropertyValueFactory<Member, Integer>("NumberOfBoats"));
	    	   id.setCellValueFactory(new PropertyValueFactory<Member, Integer>("Id"));
	       }
	 
	 private void buildLeft() {

	        BorderPane leftLayout = new BorderPane();

	        // Create a faux border-right effect using a Label.
	        Label divider = new Label();
	        divider.setId("divider1       divider.setPrefWidth(1)");
	        divider.setMinHeight(Screen.getPrimary().getBounds().getHeight());
	        leftLayout.setRight(divider);

	        //Place all demonstration buttons in a Vercial Box.
	        VBox buttonBox = new VBox();

	        //Set Alignment of Buttons in VBox Container.
	        buttonBox.setAlignment(Pos.TOP_CENTER);

	        //Give VBox a CSS ID;
	        buttonBox.setId("buttonMenuContainer");        //Create some vertical spacing b/n buttons
	        buttonBox.setSpacing(10);

	        //Add Demonstration Buttons
	        Button AddMember = new Button("Add member");
            Button Verbose = new Button("Verbose List");
	        //Set All Buttons to the same size.
	        AddMember.setMaxWidth(Double.MAX_VALUE);
	        //Create Button 2
	        Button LogOut = new Button();
	        LogOut.setText("LogOut");
	        LogOut.setMaxWidth(Double.MAX_VALUE);
	        Button Save = new Button("Save");
	        Save.setMaxWidth(Double.MAX_VALUE);
	        AddMember.setId("btnLogin");
	        LogOut.setId("btnLogin");
	        Save.setId("btnLogin");
	        Verbose.setId("btnLogin");
	        Verbose.setOnAction(new EventHandler<ActionEvent>()
	        {
	            public void handle(ActionEvent e)
	            {
	                id.setVisible(true);
	                
	            }
	        });   
	        AddMember.setOnAction(new EventHandler<ActionEvent>()
	        {
	            public void handle(ActionEvent e)
	            {	
	            	int oldSizeMemberList = Main.yachtClub.getMemberList().size();
	                ading.createPopup(null);
	                if (oldSizeMemberList < Main.yachtClub.getMemberList().size()) {
	                	data.add(Main.yachtClub.getMemberList().get(oldSizeMemberList));
	                	//data.removeAll(data);
	                	//data.addAll(Main.yachtClub.getMemberList().getMembers());
	                }
	                table.refresh();
	            }
	        });   
	        Button compact = new Button("Compact  ");
	        compact.setId("btnLogin");
	        compact.setOnAction(new EventHandler<ActionEvent>()
	        {
	            public void handle(ActionEvent e)
	            {
	                id.setVisible(false);
	            }
	        });  
	        
	        Save.setOnAction(new EventHandler<ActionEvent>()
	        {
	            public void handle(ActionEvent e)
	            {
	                YachtClubDAO ycDAO = new YachtClubDAO();
	                ycDAO.jaxbObjectToXML(yachtClub.Main.yachtClub);
	            }
	        });
	        
	        buttonBox.getChildren().addAll(AddMember, Verbose,compact,Save,LogOut);
       
	        //Add VBox to leftLayout.
	        leftLayout.setCenter(buttonBox);

	        //Place into Application.
	        bp.setLeft(leftLayout);

	 	}
	   
	   private void buildTop() {
	        BorderPane topLayout = new BorderPane();
	        Label title= new Label("Yacht Club");	        
	        title.setId("text");
	        topLayout.setCenter(title);
	        title.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
	        bp.setTop(topLayout);
	   }
	   
	   private void buildRight() {
	        TextField input = new TextField();	        
	        input.setPromptText("Search");
	        CheckBox filter = new CheckBox("Enable filter");
	        filter.setId("btnLogin");
	        CheckBox nameSearch = new CheckBox("Name");
	        CheckBox monthSearch = new CheckBox("Birth Data(Month)");
	        ChoiceBox<Integer> MonthSearch = new ChoiceBox<Integer>();
	        MonthSearch.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
	        MonthSearch.setValue(1);
	        Button search = new Button("update");
	        search.setId("btnLogin");
	        search.setOnAction(new EventHandler<ActionEvent>()
	        {
	            public void handle(ActionEvent e)
	            {
	            	data.removeAll(data);
         		    data.addAll( FXCollections.observableArrayList(Main.yachtClub.getMemberList()));
	            	MemberFilter filter = new MemberFilter(data, nameSearch.isSelected(), input.getText(), monthSearch.isSelected(), MonthSearch.getValue());
	            	filter.getFilteredMemberList();
	                
	            }
	        }); 
	        VBox rightLayout = new VBox(10);
	        rightLayout.getChildren().addAll(filter, nameSearch, input, monthSearch, MonthSearch, search);
	        for (int i=1; i<rightLayout.getChildren().size();i++)
    			rightLayout.getChildren().get(i).setDisable(!filter.isSelected());
	        
	        filter.selectedProperty().addListener(new ChangeListener<Boolean>() {
	            public void changed(ObservableValue<? extends Boolean> ov,
	                Boolean old_val, Boolean new_val) {
	            		for (int i=1; i<rightLayout.getChildren().size();i++)
	            			rightLayout.getChildren().get(i).setDisable(!filter.isSelected());
	            	   if (!filter.isSelected()) {
	            		   data.removeAll(data);
	            		   data.addAll( FXCollections.observableArrayList(Main.yachtClub.getMemberList()));
	            	   }
	            }
	        });
	        
	        bp.setRight(rightLayout);
	   }
	  
	@SuppressWarnings("unchecked")
	private void buildtabelview()
	   {
		   BorderPane center = new BorderPane();
	        name.setMinWidth(100);
	        pn.setMinWidth(100);
	        boats.setMinWidth(100);
	        id.setMinWidth(100);
	        table.getColumns().addAll(name,id, pn, boats);
	        table.setMaxWidth(500);
	        table.setEditable(true);
	        id.setVisible(false);
	        final VBox vbox = new VBox();
	        vbox.setSpacing(5);
	        vbox.setPadding(new Insets(10, 0, 0, 10));
	        vbox.getChildren().add(table);
	        center.setCenter(vbox);
	        bp.setCenter(vbox);
	   }
	  
	   private void getmenu(TableRow<Member> row) {
		   final ContextMenu contextMenu = new ContextMenu();  
           final MenuItem removeMenuItem = new MenuItem("Remove Member"); 
           final MenuItem EditMenuItem = new MenuItem("Edit Member");
           final MenuItem ViewMember = new MenuItem("View Member");
          
           removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {  
               @Override  
               public void handle(ActionEvent event) {  
            	   Member member = (Member)table.getSelectionModel().getSelectedItem();
	            	data.remove(member);
	            	Main.yachtClub.removeMember(member);
	            	table.refresh();
                   //table.getItems().remove(row.getItem());  
               }  
           });
           EditMenuItem.setOnAction(new EventHandler<ActionEvent>() {  
               @Override  
               public void handle(ActionEvent event) { 
            	   Member m = (Member)table.getSelectionModel().getSelectedItem();
	            	if (m!=null) {
	            		ading.createPopup((Member)table.getSelectionModel().getSelectedItem());
		                table.refresh();
	            	}
               }  
           });
           ViewMember.setOnAction(new EventHandler<ActionEvent>() {  
               @Override  
               public void handle(ActionEvent event) { 
            	   Member m = (Member)table.getSelectionModel().getSelectedItem();
	            	if (m!=null) {
	            		ading.createPopup((Member)table.getSelectionModel().getSelectedItem());
		                table.refresh();
	            	}
            	   /*final Stage dialog = new Stage();
                   dialog.initModality(Modality.APPLICATION_MODAL);
                   dialog.setTitle("Member name");
                   VBox dialogVbox = new VBox(20);
                   dialogVbox.setId("root");
                   dialogVbox.getChildren().add(new Text("here we will show member ifno"));
                   Scene dialogScene = new Scene(dialogVbox, 300, 200);
                   dialog.setScene(dialogScene);
                   dialog.show(); */ 
               }  
           });
           setImageIcon(ViewMember,"src/images/view.png");
           setImageIcon(removeMenuItem,"src/images/delete.png");
           setImageIcon(EditMenuItem,"src/images/edit.jpg");
           contextMenu.getItems().addAll(ViewMember,removeMenuItem,EditMenuItem);
           row.contextMenuProperty().bind(  
                   Bindings.when(row.emptyProperty())  
                   .then((ContextMenu)null)  
                   .otherwise(contextMenu)  
           );  
		}
	private void setImageIcon(MenuItem removeMenuItem,String path) {
		 File file = new File(path);
         Image image = new Image(file.toURI().toString());
         ImageView imageView=new ImageView();
         imageView.setImage(image);
         imageView.setFitWidth(10);
         imageView.setFitHeight(10);
         removeMenuItem.setGraphic(imageView);
	}
}
