package view;

import java.util.stream.IntStream;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import model.Member;
import yachtClub.Main;

public class ViewList {
	   BorderPane bp = new BorderPane();
	   AddMemberPopup ading = new AddMemberPopup();
	   ObservableList<Member> data = FXCollections.observableArrayList(Main.yachtClub.getMemberList().getMembers());
	   private TableView table = new TableView(data);
	   TableColumn<Member, String> name = new TableColumn<Member, String>("Member Name");
       TableColumn<Member, String> pn = new TableColumn<Member, String>("Personal Number");
       TableColumn<Member, Integer> id = new TableColumn<Member, Integer>("Member id");
       TableColumn<Member, Integer> boats = new TableColumn<Member, Integer>("Number Of Boats");
       
      
       
	 @SuppressWarnings("unchecked")
	public  BorderPane BorderPane() {
    table.setId("table-view");
    table.setRowFactory(tableView -> { final TableRow<Member> row = new TableRow<>(); row.hoverProperty().addListener((observable) -> { final Member
    	person = row.getItem(); if (row.isHover() && person != null) 
    	{ final Tooltip tooltip = new Tooltip();
    	tooltip.setText(
    		    "\nHere will be member info\n"  
    		);
    		row.setTooltip(tooltip);}
    	 }); return row; });
    buildTop();
    buildLeft();
    buildtabelview();
    BuildmemberDetails();
    bp.setId("bp");
    initialize();
	return bp;
}

	private void initialize() {
	       	   System.out.print("alaa");
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
	        AddMember.setId("btnLogin");
	        LogOut.setId("btnLogin");
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
	                ading.createPopup();
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
	        buttonBox.getChildren().addAll(AddMember, Verbose,compact,LogOut);
       
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
	        Label search = new Label("Search");
	        title.setId("text");
	        topLayout.setCenter(title);
	        VBox SearchBox = new VBox();
	        SearchBox.getChildren().addAll(search,input);
	        topLayout.setRight(SearchBox);
	        title.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
	       
	        bp.setTop(topLayout);
	   }
	   private void buildtabelview()
	   {
		   BorderPane center = new BorderPane();
	        name.setMinWidth(80);
	        pn.setMinWidth(80);
	        boats.setMinWidth(80);
	        id.setMinWidth(80);
	        table.getColumns().addAll(name,id, pn, boats);
	        table.setMaxWidth(400);
	        id.setVisible(false);
	        final VBox vbox = new VBox();
	        vbox.setSpacing(5);
	        vbox.setPadding(new Insets(10, 0, 0, 10));
	        vbox.getChildren().add(table);
	        center.setCenter(vbox);
	        bp.setCenter(vbox);
	   }
	   public void BuildmemberDetails(){
		   VBox vbox = new VBox();
		    vbox.setPadding(new Insets(10));
		    vbox.setSpacing(8);

		    Text title = new Text("Member");
		    Text t = new Text("here when we double click the cell");
		    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		    Button remove = new Button("Remove");
		    remove.setId("btnLogin");
		    Button edit = new Button("Edit");
		    edit.setId("btnLogin");
		    vbox.getChildren().addAll(title,remove,edit,t);
		   bp.setRight(vbox);
		   
	   }
}
