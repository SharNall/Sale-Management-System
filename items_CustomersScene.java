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
import javafx.util.converter.IntegerStringConverter;

public class items_CustomersScene {
    BorderPane bp=new BorderPane();

	private TextField ItemIDTextField;
	 private TextField CustomersIDTextField;
	 private TextField quantiyTextField;
	 ObservableList<items_Customers> items_CustomersList = FXCollections.observableArrayList();
	    DataBaseConnection db = new DataBaseConnection();
	        Scene addCustomerItems , CustomerItemspage ;
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
	                String sql = "select * from items_customers";
	                Statement stmt = con.createStatement();
	                ResultSet rs = stmt.executeQuery(sql);
	                while (rs.next()){

	                	items_CustomersList.add(new items_Customers(rs.getInt(1) ,rs.getInt(2),rs.getInt(3)));
	                }
	                con.close();
	                System.out.println("asdf");
	            }
	            catch (Exception m ){

	            }
	        }
			public items_CustomersScene() {
				 ReadWorker();
			     Stage addstage = new Stage();

				 // Create UI elements
			        Label ItemIDLabel = new Label("ItemID:");
			        Label CustomersIDPriceLabel = new Label("CustomersID:");
			        Label QuantityLabel = new Label("Quantity:");

			        ItemIDTextField = new TextField();
			        CustomersIDTextField = new TextField();
			        quantiyTextField = new TextField();

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
			                String sql = "insert into items_customers (ItemsID,CustomersID,qunatity) values ('"+ItemIDTextField.getText()+"','"+CustomersIDTextField.getText()+"','"+quantiyTextField.getText()+"');\n";
			                String csql="Update items set StoredQuantity=StoredQuantity -"+quantiyTextField.getText()+" where ItemsID="+ItemIDTextField.getText()+";";
			                System.out.println(csql);
			                System.out.println(sql);
			                items_CustomersList.add(new items_Customers(Integer.parseInt(ItemIDTextField.getText()),Integer.parseInt(CustomersIDTextField.getText()),Integer.parseInt(quantiyTextField.getText())));
			                Statement stmt = con.createStatement();
			                stmt.executeUpdate(sql);
			                stmt.executeUpdate(csql);
			                con.close();
			                addstage.close();
			            }
			            catch (Exception m ){

			            }
			            items_CustomersList.clear();
			            ReadWorker();
			        });
			        // Set up the layout
			        GridPane gridPane = new GridPane();
			        gridPane.setPadding(new Insets(20, 20, 20, 20));
			        gridPane.setVgap(10);
			        gridPane.setHgap(10);

			        gridPane.add(ItemIDLabel, 0, 0);
			        gridPane.add(ItemIDTextField, 1, 0);
			        gridPane.add(CustomersIDPriceLabel, 0, 1);
			        gridPane.add(CustomersIDTextField, 1, 1);
			        gridPane.add(QuantityLabel, 0, 2);
			        gridPane.add(quantiyTextField, 1, 2);
			        gridPane.add(addButton, 0, 3);
			        gridPane.add(backButton, 1, 3);
			        
			//==============================================================================================================
			        TableView<items_Customers> tableView = new TableView<>();
			        tableView.setItems(items_CustomersList);
			        tableView.setEditable(true);
			        TableColumn<items_Customers, Integer> ItemIDColumn = new TableColumn<>("ItemsID");
			        ItemIDColumn.setCellValueFactory(new PropertyValueFactory<>("ItemsID"));
			        ItemIDColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			        ItemIDColumn.setResizable(false);
			        ItemIDColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<items_Customers, Integer>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<items_Customers, Integer> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setItemsID(arg0.getNewValue());
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getCustomersID();
			                double name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getItemsID();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Orders set ItemsID ='" + name + "'  WHERE CustomersID='" + id ;
			                    
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			        
			        TableColumn<items_Customers, Integer> OrdersIDColumn = new TableColumn<>("CustomersID");
			        OrdersIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomersID"));
			        OrdersIDColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			        OrdersIDColumn.setResizable(false);
			        OrdersIDColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<items_Customers, Integer>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<items_Customers, Integer> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setCustomersID(arg0.getNewValue());
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getItemsID();
			                double name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getCustomersID();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Orders set CustomersID ='" + name + "'  WHERE ItemsID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });

			        TableColumn<items_Customers, Integer> QuantityColumn = new TableColumn<>("Quantity");
			        QuantityColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
			        QuantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			        QuantityColumn.setResizable(false);
			        QuantityColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<items_Customers, Integer>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<items_Customers, Integer> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setQuantity(arg0.getNewValue());;
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getCustomersID();
			                double name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getQuantity();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Orders set Quantity ='" + name + "'  WHERE CustomersID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			      
			        tableView.getColumns().addAll(ItemIDColumn, OrdersIDColumn, QuantityColumn);

			        Button deleteWorker = new Button("Delete row");
			        deleteWorker.setOnAction(e->{
			            items_Customers wo = tableView.getSelectionModel().getSelectedItem();

			            try {

			            	int id = wo.getCustomersID();
			                Connection con = db.getConnection().connectDB();
			                String sql = "DELETE FROM items_customers WHERE CustomersID='" + id + "'";
			                Statement stmt = con.createStatement();
			                stmt.executeUpdate(sql);
			                con.close();

			            } catch (Exception e2) {
			                e2.getMessage();
			            }
			            items_CustomersList.remove(wo);
			            tableView.refresh();
			            
			        });
			        Button addWorkerBtn = new Button("Add row");
			        
			        backButton.setOnAction(e->{
			        	addstage.close();
			        });
			        addWorkerBtn.setOnAction(e->{
			            addstage.setScene(addCustomerItems);
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
			        CustomerItemspage = new Scene(bp, 500, 500);
			        CustomerItemspage.getStylesheets().add(getClass().getResource("a.css").toExternalForm());


			        // Set up the scene
			        addCustomerItems = new Scene(gridPane, 300, 200);
			        addCustomerItems.getStylesheets().add(getClass().getResource("a.css").toExternalForm());

			}
	        
}
