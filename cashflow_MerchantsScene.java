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
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class cashflow_MerchantsScene {
    BorderPane bp=new BorderPane();
	private TextField DayIDTextField;
	 private TextField MerchantsIDTextField;
	 private TextField baidDebtTextField;
	 Button back=new Button("Back");
	 ObservableList<cashflow_Merchants> cashflow_MerchantsList = FXCollections.observableArrayList();
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
	                String sql = "select * from cashflow_Merchants";
	                Statement stmt = con.createStatement();
	                ResultSet rs = stmt.executeQuery(sql);
	                while (rs.next()){

	                	cashflow_MerchantsList.add(new cashflow_Merchants(rs.getInt(1) ,rs.getInt(2),rs.getFloat(3)));
	                	System.out.println("lll");
	                }
	                con.close();
	                System.out.println("asdf");
	            }
	            catch (Exception m ){

	            }
	        }
			public cashflow_MerchantsScene() {
				 ReadWorker();
			     Stage addstage = new Stage();

				 // Create UI elements
			        Label DayIDLabel = new Label("DayID:");
			        Label MerchantsIDLabel = new Label("MerchantsID:");
			        Label baidDebtLabel = new Label("paidDebts:");

			        DayIDTextField = new TextField();
			        MerchantsIDTextField = new TextField();
			        baidDebtTextField = new TextField();

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
			                String sql = "insert into cashflow_merchants (DayID,MerchantsID,paid_debts) values ('"+DayIDTextField.getText()+"','"+MerchantsIDTextField.getText()+"','"+baidDebtTextField.getText()+"');\n";
			                String csql="Update Merchants set total_debts=total_debts -"+baidDebtTextField.getText()+" where MerchantsID="+MerchantsIDTextField.getText()+";";
			                System.out.println(csql);
			                System.out.println(sql);
			                cashflow_MerchantsList.add(new cashflow_Merchants(Integer.parseInt(DayIDTextField.getText()),Integer.parseInt(MerchantsIDTextField.getText()),Float.parseFloat(baidDebtTextField.getText())));
			                Statement stmt = con.createStatement();
			                stmt.executeUpdate(sql);
			                stmt.executeUpdate(csql);
			                con.close();
			                addstage.close();
			            }
			            catch (Exception m ){

			            }
			            cashflow_MerchantsList.clear();
			            ReadWorker();
			        });
			        // Set up the layout
			        GridPane gridPane = new GridPane();
			        gridPane.setPadding(new Insets(20, 20, 20, 20));
			        gridPane.setVgap(10);
			        gridPane.setHgap(10);

			        gridPane.add(DayIDLabel, 0, 0);
			        gridPane.add(DayIDTextField, 1, 0);
			        gridPane.add(MerchantsIDLabel, 0, 1);
			        gridPane.add(MerchantsIDTextField, 1, 1);
			        gridPane.add(baidDebtLabel, 0, 2);
			        gridPane.add(baidDebtTextField, 1, 2);
			        gridPane.add(addButton, 0, 3);
			        gridPane.add(backButton, 1, 3);
			        
			//==============================================================================================================
			        TableView<cashflow_Merchants> tableView = new TableView<>();
			        tableView.setItems(cashflow_MerchantsList);
			        tableView.setEditable(true);
			        TableColumn<cashflow_Merchants, Integer> ItemIDColumn = new TableColumn<>("DayID");
			        ItemIDColumn.setCellValueFactory(new PropertyValueFactory<>("DayID"));
			        ItemIDColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			        ItemIDColumn.setResizable(false);
			        ItemIDColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<cashflow_Merchants, Integer>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<cashflow_Merchants, Integer> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setDayID(arg0.getNewValue());;
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getMerchantsID();
			                double name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDayID();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Orders set DayID( ='" + name + "'  WHERE MerchantsID='" + id ;
			                    
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			        
			        TableColumn<cashflow_Merchants, Integer> OrdersIDColumn = new TableColumn<>("MerchantsID");
			        OrdersIDColumn.setCellValueFactory(new PropertyValueFactory<>("MerchantsID"));
			        OrdersIDColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			        OrdersIDColumn.setResizable(false);
			        OrdersIDColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<cashflow_Merchants, Integer>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<cashflow_Merchants, Integer> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setMerchantsID(arg0.getNewValue());;
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDayID();
			                double name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getMerchantsID();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Orders set MerchantsID ='" + name + "'  WHERE DayID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });

			        TableColumn<cashflow_Merchants, Float> QuantityColumn = new TableColumn<>("paidDebts");
			        QuantityColumn.setCellValueFactory(new PropertyValueFactory<>("paidDebts"));
			        QuantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
			        QuantityColumn.setResizable(false);
			        QuantityColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<cashflow_Merchants, Float>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<cashflow_Merchants, Float> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setPaidDebts(arg0.getNewValue());
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDayID();
			                float name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPaidDebts();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Orders set baidDebt ='" + name + "'  WHERE DayID='" + id + "'";
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
			            cashflow_Merchants wo = tableView.getSelectionModel().getSelectedItem();

			            try {

			            	int id = wo.getDayID();
			                Connection con = db.getConnection().connectDB();
			                String sql = "DELETE FROM cashflow_Merchants WHERE DayID='" + id + "'";
			                Statement stmt = con.createStatement();
			                stmt.executeUpdate(sql);
			                con.close();

			            } catch (Exception e2) {
			                e2.getMessage();
			            }
			            cashflow_MerchantsList.remove(wo);
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
