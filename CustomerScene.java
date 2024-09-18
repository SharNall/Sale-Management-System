package com.example.sale_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CustomerScene {
    BorderPane bp=new BorderPane();
	 private TextField nameTextField;
	    private TextField phoneTextField;
	    private TextField AddressTextField;
	    ObservableList<Customers> customerList = FXCollections.observableArrayList();
	    DataBaseConnection db = new DataBaseConnection();
	        Scene addCustomers , Customerspage ;
	        public void ReadWorker(){
	            Connection con = null;
	            try {
	                con = db.getConnection().connectDB();
	            } catch (ClassNotFoundException ex) {
	                throw new RuntimeException(ex);
	            } catch (SQLException ex) {
	                throw new RuntimeException(ex);
	            }
	            try {
	                String sql = "select * from Customers";
	                Statement stmt = con.createStatement();
	                ResultSet rs = stmt.executeQuery(sql);
	                while (rs.next()){

	                	customerList.add(new Customers(rs.getInt(1) ,rs.getString(2),rs.getString(3),rs.getString(4)));
	                }
	                con.close();
	                System.out.println("asdf");
	            }
	            catch (Exception m ){

	            }
	        }
			public CustomerScene() {
				 ReadWorker();
			     Stage addstage = new Stage();

				 // Create UI elements
			        Label nameLabel = new Label("Name:");
			        Label phoneLabel = new Label("Phone:");
			        Label AddresstypeLabel = new Label("Address:");

			        nameTextField = new TextField();
			        phoneTextField = new TextField();
			        AddressTextField = new TextField();

			        Button addButton = new Button("Add row");
			        Button backButton = new Button("Back");
			        // Set button actions
			        addButton.setOnAction(e->{
			            Connection con = null;
			            try {
			                con = db.getConnection().connectDB();
			            } catch (ClassNotFoundException ex) {
			                throw new RuntimeException(ex);
			            } catch (SQLException ex) {
			                throw new RuntimeException(ex);
			            }
			            try {
			                String sql = "insert into Customers (name,phone,Address) values ('"+nameTextField.getText()+"','"+phoneTextField.getText()+"','"+AddressTextField.getText()+"')";
			                customerList.add(new Customers(nameTextField.getText(),phoneTextField.getText(),AddressTextField.getText()));
			                Statement stmt = con.createStatement();
			                stmt.executeUpdate(sql);
			                con.close();
			                addstage.close();
			            }
			            catch (Exception m ){

			            }
			            customerList.clear();
			            ReadWorker();
			        });
			        // Set up the layout
			        GridPane gridPane = new GridPane();
			        gridPane.setPadding(new Insets(20, 20, 20, 20));
			        gridPane.setVgap(10);
			        gridPane.setHgap(10);

			        gridPane.add(nameLabel, 0, 0);
			        gridPane.add(nameTextField, 1, 0);
			        gridPane.add(phoneLabel, 0, 1);
			        gridPane.add(phoneTextField, 1, 1);
			        gridPane.add(AddresstypeLabel, 0, 2);
			        gridPane.add(AddressTextField, 1, 2);
			        gridPane.add(addButton, 0, 3);
			        gridPane.add(backButton, 1, 3);
			        
			//==============================================================================================================
			        TableView<Customers> tableView = new TableView<>();
			        tableView.setItems(customerList);
			        tableView.setEditable(true);
			        // Create TableColumn for name
			        TableColumn<Customers, String> nameColumn = new TableColumn<>("Name");
			        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			        nameColumn.setResizable(false);
			        nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Customers, String>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<Customers, String> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setName(arg0.getNewValue());
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getCustomersID();
			                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getName();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Customers set name ='" + name + "'  WHERE customersID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			        
			        // Create TableColumn for phone
			        TableColumn<Customers, String> phoneColumn = new TableColumn<>("Phone");
			        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
			        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			        phoneColumn.setResizable(false);
			        phoneColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Customers, String>>() {
			            @Override
			            public void handle(TableColumn.CellEditEvent<Customers, String> event) {
			                event.getTableView().getItems().get(event.getTablePosition().getRow()).setPhone(event.getNewValue());
			                int id = event.getTableView().getItems().get(event.getTablePosition().getRow()).getCustomersID();
			                String phone = event.getTableView().getItems().get(event.getTablePosition().getRow()).getPhone();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Customers SET phone = '" + phone + "' WHERE customerID = '" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e) {
			                    e.printStackTrace();
			                }
			            }
			        });

			        TableColumn<Customers, String> adressColumn = new TableColumn<>("address");
			        adressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
			        adressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			        adressColumn.setResizable(false);

			        adressColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Customers, String>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<Customers, String> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setAddress(arg0.getNewValue());;
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getCustomersID();
			                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getAddress();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Customers set address ='" + name + "'  WHERE customersID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			        
			        tableView.getColumns().addAll(nameColumn, phoneColumn, adressColumn);

			        Button deleteWorker = new Button("Delete row");
			        deleteWorker.setOnAction(e->{
			            Customers wo = tableView.getSelectionModel().getSelectedItem();

			            try {

			            	int id = wo.getCustomersID();
			                Connection con = db.getConnection().connectDB();
			                String sql = "DELETE FROM customers WHERE customersID='" + id + "'";
			                Statement stmt = con.createStatement();
			                stmt.executeUpdate(sql);
			                con.close();

			            } catch (Exception e2) {
			                e2.getMessage();
			            }
			            customerList.remove(wo);
			            tableView.refresh();
			            
			        });
			        Button addWorkerBtn = new Button("Add row");
			        
			        backButton.setOnAction(e->{
			        	addstage.close();
			        });
			        addWorkerBtn.setOnAction(e->{
			            addstage.setScene(addCustomers);
			            addstage.show();


			        }
			        );
			        
			        bp.setCenter(tableView);
			        HBox hb=new HBox();
			        hb.setSpacing(10);
			        hb.setPadding(new Insets(20,20,20,20));
			        bp.setBottom(hb);
			        hb.getChildren().addAll(addWorkerBtn,deleteWorker);
			        hb.setAlignment(Pos.BOTTOM_CENTER);
			        

			        // Set up the scene
			        Customerspage = new Scene(bp, 500, 500);
			        Customerspage.getStylesheets().add(getClass().getResource("a.css").toExternalForm());


			        // Set up the scene
			        addCustomers = new Scene(gridPane, 300, 200);
			        addCustomers.getStylesheets().add(getClass().getResource("a.css").toExternalForm());

			}
	        
}
