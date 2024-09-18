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
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class OrdersScene {
    BorderPane bp=new BorderPane();
	private TextField dateTextField;
	 private TextField quantiyTextField;
	 private TextField totalPriceTextField;
	    private TextField merchantTextField;
	    ObservableList<Orders> OrdersList = FXCollections.observableArrayList();
	    DataBaseConnection db = new DataBaseConnection();
	        Scene addOrders , Orderspage ;
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
	                String sql = "select * from Orders";
	                Statement stmt = con.createStatement();
	                ResultSet rs = stmt.executeQuery(sql);
	                while (rs.next()){

	                	OrdersList.add(new Orders(rs.getInt(1) ,rs.getString(2),rs.getFloat(3),rs.getInt(4),rs.getInt(5)));
	                }
	                con.close();
	                System.out.println("asdf");
	            }
	            catch (Exception m ){

	            }
	        }
			public OrdersScene() {
				 ReadWorker();
			     Stage addstage = new Stage();

				 // Create UI elements
			        Label DateLabel = new Label("Date:");
			        Label TotalPriceLabel = new Label("Total Price:");
			        Label QuantityLabel = new Label("Quantity:");
			        Label MerchantsIdLabel = new Label("MerchantsId:");

			        dateTextField = new TextField();
			        totalPriceTextField = new TextField();
			        quantiyTextField = new TextField();
			        merchantTextField = new TextField();

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
			                String sql = "insert into Orders (Date,totalprice,Quantity,MerchantsID) values ('"+dateTextField.getText()+"','"+Float.parseFloat(totalPriceTextField.getText())+"','"+Integer.parseInt(quantiyTextField.getText())+"','"+Integer.parseInt(merchantTextField.getText())+"');\n";
			                String csql="Update merchants set total_debts=total_debts +"+totalPriceTextField.getText()+" where MerchantsID="+merchantTextField.getText()+";";
			                System.out.println(sql);
			                OrdersList.add(new Orders(dateTextField.getText(),Float.parseFloat(totalPriceTextField.getText()),Integer.parseInt(quantiyTextField.getText()),Integer.parseInt(merchantTextField.getText())));
			                Statement stmt = con.createStatement();
			                stmt.executeUpdate(sql);
			                stmt.executeUpdate(csql);
			                con.close();
			                addstage.close();
			            }
			            catch (Exception m ){

			            }
			            OrdersList.clear();
			            ReadWorker();
			        });
			        // Set up the layout
			        GridPane gridPane = new GridPane();
			        gridPane.setPadding(new Insets(20, 20, 20, 20));
			        gridPane.setVgap(10);
			        gridPane.setHgap(10);

			        gridPane.add(DateLabel, 0, 0);
			        gridPane.add(dateTextField, 1, 0);
			        gridPane.add(TotalPriceLabel, 0, 1);
			        gridPane.add(totalPriceTextField, 1, 1);
			        gridPane.add(QuantityLabel, 0, 2);
			        gridPane.add(quantiyTextField, 1, 2);
			        gridPane.add(MerchantsIdLabel, 0, 3);
			        gridPane.add(merchantTextField, 1, 3);
			        gridPane.add(addButton, 0, 4);
			        gridPane.add(backButton, 1, 4);
			        
			//==============================================================================================================
			        TableView<Orders> tableView = new TableView<>();
			        tableView.setItems(OrdersList);
			        tableView.setEditable(true);
			        // Create TableColumn for name
			        TableColumn<Orders, String> DateColumn = new TableColumn<>("Date");
			        DateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
			        DateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			        DateColumn.setResizable(false);
			        DateColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Orders, String>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<Orders, String> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setDate(arg0.getNewValue());;
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getOrdersID();
			                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDate();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Orders set Date ='" + name + "'  WHERE OrdersID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			        
			        // Create TableColumn for phone
			        TableColumn<Orders, Float> TotalpriceColumn = new TableColumn<>("totalPrice");
			        TotalpriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
			        TotalpriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
			        TotalpriceColumn.setResizable(false);
			        TotalpriceColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Orders, Float>>() {
			            @Override
			            public void handle(TableColumn.CellEditEvent<Orders, Float> event) {
			                event.getTableView().getItems().get(event.getTablePosition().getRow()).setTotalPrice(event.getNewValue());;
			                int id = event.getTableView().getItems().get(event.getTablePosition().getRow()).getOrdersID();
			                float phone = event.getTableView().getItems().get(event.getTablePosition().getRow()).getTotalPrice();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Orders SET totalprice = '" + phone + "' WHERE OrdersID = '" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e) {
			                    e.printStackTrace();
			                }
			            }
			        });

			        TableColumn<Orders, Integer> QuantityColumn = new TableColumn<>("Quantity");
			        QuantityColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
			        QuantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			        QuantityColumn.setResizable(false);
			        QuantityColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Orders, Integer>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<Orders, Integer> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setQuantity(arg0.getNewValue());;
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getOrdersID();
			                double name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getQuantity();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Orders set Quantity ='" + name + "'  WHERE OrdersID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			        TableColumn<Orders, Integer> MerchantsColumn = new TableColumn<>("MerchantsID");
			        MerchantsColumn.setCellValueFactory(new PropertyValueFactory<>("MerchantsID"));
			        MerchantsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			        MerchantsColumn.setResizable(false);
			        MerchantsColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Orders, Integer>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<Orders, Integer> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setMerchantsID(arg0.getNewValue());;
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getOrdersID();
			                double name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getMerchantsID();
			                try {
			                    Connection con = db.getConnection().connectDB();
			          
			                    String sql = "UPDATE Orders set MerchantsID ='" + name + "'  WHERE OrdersID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			        tableView.getColumns().addAll(DateColumn, TotalpriceColumn, QuantityColumn,MerchantsColumn);

			        Button deleteWorker = new Button("Delete row");
			        deleteWorker.setOnAction(e->{
			            Orders wo = tableView.getSelectionModel().getSelectedItem();

			            try {

			            	int id = wo.getOrdersID();
			                Connection con = db.getConnection().connectDB();
			                String sql = "DELETE FROM Orders WHERE OrdersID='" + id + "'";
			                Statement stmt = con.createStatement();
			                stmt.executeUpdate(sql);
			                con.close();

			            } catch (Exception e2) {
			                e2.getMessage();
			            }
			            OrdersList.remove(wo);
			            tableView.refresh();
			            
			        });
			        Button addWorkerBtn = new Button("Add row");
			        
			        backButton.setOnAction(e->{
			        	addstage.close();
			        });
			        addWorkerBtn.setOnAction(e->{
			            addstage.setScene(addOrders);
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
			        Orderspage = new Scene(bp, 500, 500);
			        Orderspage.getStylesheets().add(getClass().getResource("a.css").toExternalForm());


			        // Set up the scene
			        addOrders = new Scene(gridPane, 300, 200);
			        addOrders.getStylesheets().add(getClass().getResource("a.css").toExternalForm());

			}
	        
}
