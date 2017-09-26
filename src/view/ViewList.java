package view;


import java.io.File;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Member;
import model.YachtClubDAO;
import yachtClub.Main;

public class ViewList {
	   BorderPane bp = new BorderPane();
	   AddMemberPopup ading = new AddMemberPopup();
	   ObservableList<Member> data = FXCollections.observableArrayList(Main.yachtClub.getMemberList().getMembers());
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
	                ading.createPopup(null);
	                if (data.size()!=Main.yachtClub.getMemberList().getMembers().size()) {
	                	data.add(Main.yachtClub.getMemberList().getMembers().get(data.size()));
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
	                ycDAO.jaxbObjectToXML();
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
	        TextField input = new TextField();	        
	        input.setPromptText("Search");
	        Button search = new Button("Search");
	        search.setId("btnLogin");
	        title.setId("text");
	        topLayout.setCenter(title);
	        VBox SearchBox = new VBox();
	        SearchBox.getChildren().addAll(search,input);
	        topLayout.setRight(SearchBox);
	        title.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
	       
	        bp.setTop(topLayout);
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
			// TODO Auto-generated method stub
		   final ContextMenu contextMenu = new ContextMenu();  
           final MenuItem removeMenuItem = new MenuItem("Remove Member"); 
           final MenuItem EditMenuItem = new MenuItem("Edit Member");
           final MenuItem ViewMember = new MenuItem("View Member");
          
           removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {  
               @Override  
               public void handle(ActionEvent event) {  
                   table.getItems().remove(row.getItem());  
               }  
           });
           ViewMember.setOnAction(new EventHandler<ActionEvent>() {  
               @Override  
               public void handle(ActionEvent event) {  
            	   final Stage dialog = new Stage();
                   dialog.initModality(Modality.APPLICATION_MODAL);
                   dialog.setTitle("Member name");
                   VBox dialogVbox = new VBox(20);
                   dialogVbox.setId("root");
                   dialogVbox.getChildren().add(new Text("here we will show member ifno"));
                   Scene dialogScene = new Scene(dialogVbox, 300, 200);
                   dialog.setScene(dialogScene);
                   dialog.show();  
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
		// TODO Auto-generated method stub
		 File file = new File(path);
         Image image = new Image(file.toURI().toString());
         ImageView imageView=new ImageView();
         imageView.setImage(image);
         imageView.setFitWidth(10);
         imageView.setFitHeight(10);
         removeMenuItem.setGraphic(imageView);
	}
}
