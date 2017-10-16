package view;

import java.io.File;
import java.net.MalformedURLException;

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
import javafx.stage.Stage;
import model.Member;
import model.MemberFilter;
import model.YachtClubDAO;
import yachtClub.Program;

public class ViewList {
	BorderPane borderPane = new BorderPane();
	MemberHandler m_handler = new MemberHandler();
	ObservableList<Member> data = FXCollections.observableArrayList(Program.yachtClub.getMemberList());
	private TableView<Member> table = new TableView<Member>(data);
	TableColumn<Member, String> c_name = new TableColumn<Member, String>("Member Name");
	TableColumn<Member, String> c_personalNumber = new TableColumn<Member, String>("Personal Number");
	TableColumn<Member, Integer> c_id = new TableColumn<Member, Integer>("Member id");
	TableColumn<Member, Integer> c_numberOfBoats = new TableColumn<Member, Integer>("Number Of Boats");
	TextField input = new TextField();

	public BorderPane BorderPane() {
		table.setId("table-view");
		table.setRowFactory(tableView -> {
			final TableRow<Member> row = new TableRow<>();
			getmenu(row);
			return row;
		});
		buildTop();
		buildLeft();
		buildRight();
		buildtabelview();
		borderPane.setId("bp");
		initialize();
		return borderPane;
	}

	private void initialize() {
		c_name.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
		c_personalNumber.setCellValueFactory(new PropertyValueFactory<Member, String>("personalNumber"));
		c_numberOfBoats.setCellValueFactory(new PropertyValueFactory<Member, Integer>("NumberOfBoats"));
		c_id.setCellValueFactory(new PropertyValueFactory<Member, Integer>("Id"));
	}

	private void buildLeft() {

		BorderPane leftLayout = new BorderPane();

		// Create a faux border-right effect using a Label.
		Label divider = new Label();
		divider.setId("divider1       divider.setPrefWidth(1)");
		divider.setMinHeight(Screen.getPrimary().getBounds().getHeight());
		leftLayout.setRight(divider);

		// Place all demonstration buttons in a Vercial Box.
		VBox buttonBox = new VBox();

		// Set Alignment of Buttons in VBox Container.
		buttonBox.setAlignment(Pos.TOP_CENTER);

		// Give VBox a CSS ID;
		buttonBox.setId("buttonMenuContainer"); // Create some vertical spacing b/n buttons
		buttonBox.setSpacing(10);

		// Add Demonstration Buttons
		Button AddMember = new Button("Add member");
		Button Verbose = new Button("Verbose List");
		// Set All Buttons to the same size.
		AddMember.setMaxWidth(Double.MAX_VALUE);
		// Create Button 2
		Button LogOut = new Button();
		LogOut.setText("LogOut");
		LogOut.setMaxWidth(Double.MAX_VALUE);
		Button Save = new Button("Save");
		Save.setMaxWidth(Double.MAX_VALUE);
		AddMember.setId("btnLogin");
		LogOut.setId("btnLogin");
		Save.setId("btnLogin");
		Verbose.setId("btnLogin");
		Verbose.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				c_id.setVisible(true);

			}
		});
		AddMember.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				addMember();
			}
		});
		Button compact = new Button("Compact  ");
		compact.setId("btnLogin");
		compact.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				c_id.setVisible(false);
			}
		});

		Save.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				save();
			}
		});

		LogOut.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				logOut();
			}
		});

		buttonBox.getChildren().addAll(AddMember, Verbose, compact, Save, LogOut);

		// Add VBox to leftLayout.
		leftLayout.setCenter(buttonBox);

		// Place into Application.
		borderPane.setLeft(leftLayout);

	}

	private void buildTop() {
		BorderPane topLayout = new BorderPane();
		Label title = new Label("Yacht Club");
		title.setId("text");
		topLayout.setCenter(title);
		title.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
		borderPane.setTop(topLayout);
	}

	private void buildRight() {
		TextField input = new TextField();
		input.setPromptText("Search");
		CheckBox filter = new CheckBox("Enable filter");
		filter.setId("btnLogin");
		CheckBox nameSearch = new CheckBox("Name");
		CheckBox monthSearch = new CheckBox("Birth Data(Month)");
		ChoiceBox<Integer> MonthSearch = new ChoiceBox<Integer>();
		MonthSearch.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
		MonthSearch.setValue(1);
		Button search = new Button("Search");
		search.setId("btnLogin");
		search.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				MemberFilter filter = new MemberFilter(data, nameSearch.isSelected(), input.getText(),
						monthSearch.isSelected(), MonthSearch.getValue());
				search(filter);

			}

		});
		VBox rightLayout = new VBox(10);
		rightLayout.getChildren().addAll(filter, nameSearch, input, monthSearch, MonthSearch, search);
		for (int i = 1; i < rightLayout.getChildren().size(); i++)
			rightLayout.getChildren().get(i).setDisable(!filter.isSelected());

		filter.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
				for (int i = 1; i < rightLayout.getChildren().size(); i++)
					rightLayout.getChildren().get(i).setDisable(!filter.isSelected());
				if (!filter.isSelected()) {
					data.removeAll(data);
					data.addAll(FXCollections.observableArrayList(Program.yachtClub.getMemberList()));
				}
			}
		});

		borderPane.setRight(rightLayout);
	}

	@SuppressWarnings("unchecked")
	private void buildtabelview() {
		BorderPane center = new BorderPane();
		c_name.setMinWidth(100);
		c_personalNumber.setMinWidth(100);
		c_numberOfBoats.setMinWidth(100);
		c_id.setMinWidth(100);
		table.getColumns().addAll(c_name, c_id, c_personalNumber, c_numberOfBoats);
		table.setMaxWidth(500);
		table.setEditable(true);
		c_id.setVisible(false);
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().add(table);
		center.setCenter(vbox);
		borderPane.setCenter(vbox);
	}

	// right click menu for the member-table (UI)
	private void getmenu(TableRow<Member> row) {
		final ContextMenu contextMenu = new ContextMenu();
		final MenuItem removeMenuItem = new MenuItem("Remove Member");
		final MenuItem EditMenuItem = new MenuItem("Edit Member");
		final MenuItem ViewMember = new MenuItem("View Member");

		// Action when user select "Remove Member" from the contextMenu
		removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Member member = (Member) table.getSelectionModel().getSelectedItem();
				removeMember(member);
			}
		});

		// Action when user select "Edit Member" from the contextMenu
		EditMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Member member = (Member) table.getSelectionModel().getSelectedItem();
				editMember(member);
			}
		});

		// Action when user select "View Member" from the contextMenu
		ViewMember.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Member member = (Member) table.getSelectionModel().getSelectedItem();
				viewMember(member);
			}
		});
		setImageIcon(ViewMember, "src/images/view.png");
		setImageIcon(removeMenuItem, "src/images/delete.png");
		setImageIcon(EditMenuItem, "src/images/edit.jpg");
		contextMenu.getItems().addAll(ViewMember, removeMenuItem, EditMenuItem);
		row.contextMenuProperty()
				.bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(contextMenu));
	}

	private void setImageIcon(MenuItem removeMenuItem, String path) {
		File file = new File(path);
		Image image = new Image(file.toURI().toString());
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setFitWidth(10);
		imageView.setFitHeight(10);
		removeMenuItem.setGraphic(imageView);
	}

	// Save button action
	private void save() {
		YachtClubDAO ycDAO = new YachtClubDAO();
		ycDAO.jaxbObjectToXML(yachtClub.Program.yachtClub);

	}

	// log-out button action
	private void logOut() {
		Stage stage = (Stage) borderPane.getScene().getWindow();
		Login login = new Login();
		try {
			login.start(stage);
			Program.yachtClub.setLogedInUser(null);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// search button action
	private void search(MemberFilter filter) {
		data.removeAll(data);
		data.addAll(FXCollections.observableArrayList(Program.yachtClub.getMemberList()));
		filter.getFilteredMemberList();
	}

	// add-member button action
	private void addMember() {
		int oldSizeMemberList = Program.yachtClub.getMemberList().size();
		boolean hasPermission = (Program.yachtClub.getLogedInUser() instanceof model.Secretary);
		m_handler.createPopup(null, hasPermission);
		if (oldSizeMemberList < Program.yachtClub.getMemberList().size()) {
			data.add(Program.yachtClub.getMemberList().get(oldSizeMemberList));
			// data.removeAll(data);
			// data.addAll(Main.yachtClub.getMemberList().getMembers());
		}
		table.refresh();
	}

	// remove-member action
	private void removeMember(Member member) {
		data.remove(member);
		Program.yachtClub.removeMember(member);
		table.refresh();
		// table.getItems().remove(row.getItem());

	}

	// edit-member action
	private void editMember(Member member) {
		if (member != null) {
			boolean hasPermission = Program.yachtClub.getLogedInUser() == member
					|| Program.yachtClub.getLogedInUser() instanceof model.Secretary;
			m_handler.createPopup((Member) table.getSelectionModel().getSelectedItem(), hasPermission);
			table.refresh();
		}
	}

	// view-member action
	private void viewMember(Member member) {
		if (member != null) {
			boolean hasPermission = Program.yachtClub.getLogedInUser() == member
					|| Program.yachtClub.getLogedInUser() instanceof model.Secretary;
			m_handler.createPopup(member, hasPermission);
			table.refresh();
		}
	}
}
