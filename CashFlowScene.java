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

public class CashFlowScene {
    BorderPane bp=new BorderPane();

	 private TextField DateTextField;
	    private TextField debtOnUSTextField;
	    private TextField paidDebtsTextField;
	    private TextField totalSalariesTextField;
	    private TextField totalCashTextField;
	    private TextField totalIncomeTextField;
	    ObservableList<CashFlow> CashFlowList = FXCollections.observableArrayList();
	    DataBaseConnection db = new DataBaseConnection();
	        Scene addcashflow , cashflowpage ;
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
	                String sql = "select * from cashflow";
	                Statement stmt = con.createStatement();
	                ResultSet rs = stmt.executeQuery(sql);
	                while (rs.next()){
	                	
	                	System.out.println(rs.getInt(1));
	                	System.out.println(rs.getString(4));
	                	CashFlowList.add(new CashFlow(rs.getInt(1),rs.getString(2),rs.getInt(3) ,rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7)));
	                }
	                con.close();
	                System.out.println("asdf");
	            }
	            catch (Exception m ){

	            }
	        }
			public CashFlowScene() {
				 ReadWorker();
			     Stage addstage = new Stage();

				 // Create UI elements
			        Label DateLabel = new Label("Date:");
			        Label debtOnUSLabel = new Label("debtOnUS:");
			        Label paidDebtsLabel = new Label("paidDebts:");
			        Label totalSalariesLabel = new Label("totalSalaries:");
			        Label totalCashLabel = new Label("totalCash:");
			        Label totalIncomeLabel = new Label("totalIncome:");

			        DateTextField=new TextField();
			        debtOnUSTextField=new TextField();
			        paidDebtsTextField=new TextField();
			        totalSalariesTextField=new TextField();
			        totalCashTextField=new TextField();
			        totalIncomeTextField=new TextField();
			        Button addButton = new Button("Add row");
			        Button backButton = new Button("Back");
			        // Set button actions
			        addButton.setOnAction(e->{
			        	System.out.println("s");
			            Connection con = null;
			            try {
			                con = db.getConnection().connectDB();
			            } catch (ClassNotFoundException ex) {
			                throw new RuntimeException(ex);
			            } catch (SQLException ex) {
			                throw new RuntimeException(ex);
			            }
			            try {
			            
			                String sql = "insert into cashflow (Date,debtOnUS,paidDebts,totalSalaries,totalCash,totalIncome) values ('"+DateTextField.getText()+"','"+debtOnUSTextField.getText()+"','"+paidDebtsTextField.getText()+"','"+totalSalariesTextField.getText()+"','"+totalCashTextField.getText()+"','"+totalIncomeTextField.getText()+"')";
			              
			                CashFlowList.add(new CashFlow(DateTextField.getText(),Integer.parseInt(debtOnUSTextField.getText()),Integer.parseInt(paidDebtsTextField.getText()),Integer.parseInt(totalSalariesTextField.getText()),Integer.parseInt(totalCashTextField.getText()),Integer.parseInt(totalIncomeTextField.getText())));
			                Statement stmt = con.createStatement();
			                stmt.executeUpdate(sql);
			                con.close();
			                addstage.close();
			            }
			            catch (Exception m ){

			            }
			            CashFlowList.clear();
			            ReadWorker();
			        });
			        // Set up the layout
			        GridPane gridPane = new GridPane();
			        gridPane.setPadding(new Insets(20, 20, 20, 20));
			        gridPane.setVgap(10);
			        gridPane.setHgap(10);

			        gridPane.add(DateLabel, 0, 0);
			        gridPane.add(DateTextField, 1, 0);
			        gridPane.add(debtOnUSLabel, 0, 1);
			        gridPane.add(debtOnUSTextField, 1, 1);
			        gridPane.add(paidDebtsLabel, 0, 2);
			        gridPane.add(paidDebtsTextField, 1, 2);
			        gridPane.add(totalSalariesLabel, 0, 3);
			        gridPane.add(totalSalariesTextField, 1, 3);
			        gridPane.add(totalCashLabel, 0, 4);
			        gridPane.add(totalCashTextField, 1, 4);
			        gridPane.add(totalIncomeLabel, 0, 5);
			        gridPane.add(totalIncomeTextField, 1, 5);
			        gridPane.add(addButton, 0, 6);
			        gridPane.add(backButton, 1, 6);
			        
			//==============================================================================================================
			        TableView<CashFlow> tableView = new TableView<>();
			        tableView.setItems(CashFlowList);
			        
			        tableView.setEditable(true);
			        // Create TableColumn for name
			        TableColumn<CashFlow, String> dateColumn = new TableColumn<>("date");
			        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
			        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			        dateColumn.setResizable(false);
			        dateColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CashFlow, String>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<CashFlow, String> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setDate(arg0.getNewValue());;
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDayID();
			                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDate();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE CashFlow set date ='" + name + "'  WHERE DayID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			        
			     // Create TableColumn for name
			        TableColumn<CashFlow, Integer> debtOnUSColumn = new TableColumn<>("debtOnUS");
			        debtOnUSColumn.setCellValueFactory(new PropertyValueFactory<>("debtOnUS"));
			        debtOnUSColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			        debtOnUSColumn.setResizable(false);
			        debtOnUSColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CashFlow, Integer>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<CashFlow, Integer> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setDebtOnUS(arg0.getNewValue());;
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDayID();
			                Integer name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDebtOnUS();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE CashFlow set debtOnUS ='" + name + "'  WHERE DayID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			     // Create TableColumn for name
			        TableColumn<CashFlow, Integer> paidDebtsColumn = new TableColumn<>("paidDebts");
			        paidDebtsColumn.setCellValueFactory(new PropertyValueFactory<>("paidDebts"));
			        paidDebtsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			        paidDebtsColumn.setResizable(false);
			        paidDebtsColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CashFlow, Integer>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<CashFlow, Integer> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setPaidDebts(arg0.getNewValue());
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDayID();
			                Integer name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPaidDebts();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE CashFlow set paidDebts ='" + name + "'  WHERE DayID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			     // Create TableColumn for name
			        TableColumn<CashFlow, Integer> totalSalariesColumn = new TableColumn<>("totalSalaries");
			        totalSalariesColumn.setCellValueFactory(new PropertyValueFactory<>("totalSalaries"));
			        totalSalariesColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			        totalSalariesColumn.setResizable(false);
			        totalSalariesColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CashFlow, Integer>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<CashFlow, Integer> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setTotalSalaries(arg0.getNewValue());
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDayID();
			                Integer name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getTotalSalaries();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE CashFlow set totalSalaries ='" + name + "'  WHERE DayID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			     // Create TableColumn for name
			        TableColumn<CashFlow, Integer> totalCashColumn = new TableColumn<>("totalCash");
			        totalCashColumn.setCellValueFactory(new PropertyValueFactory<>("totalCash"));
			        totalCashColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			        totalCashColumn.setResizable(false);
			        totalCashColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CashFlow, Integer>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<CashFlow, Integer> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setTotalCash(arg0.getNewValue());
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDayID();
			                Integer name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getTotalCash();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE CashFlow set totalCash ='" + name + "'  WHERE DayID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			     // Create TableColumn for name
			        TableColumn<CashFlow, Integer> totalIncomeColumn = new TableColumn<>("totalIncome");
			        totalIncomeColumn.setCellValueFactory(new PropertyValueFactory<>("totalIncome"));
			        totalIncomeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			        totalIncomeColumn.setResizable(false);
			        totalIncomeColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CashFlow, Integer>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<CashFlow, Integer> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setTotalIncome(arg0.getNewValue());
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDayID();
			                Integer name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getTotalIncome();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE CashFlow set totalIncome ='" + name + "'  WHERE DayID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			     
			        tableView.getColumns().addAll(dateColumn, debtOnUSColumn,paidDebtsColumn,totalSalariesColumn,totalCashColumn,totalIncomeColumn);

			        Button deleteWorker = new Button("Delete row");
			        deleteWorker.setOnAction(e->{
			            CashFlow wo = tableView.getSelectionModel().getSelectedItem();

			            try {

			            	int id = wo.getDayID();
			                Connection con = db.getConnection().connectDB();
			                String sql = "DELETE FROM cashflow WHERE DayID='" + id + "'";
			                Statement stmt = con.createStatement();
			                stmt.executeUpdate(sql);
			                con.close();

			            } catch (Exception e2) {
			                e2.getMessage();
			            }
			            CashFlowList.remove(wo);
			            tableView.refresh();
			            
			        });
			        Button addWorkerBtn = new Button("Add row");
			        
			        backButton.setOnAction(e->{
			        	addstage.close();
			        });
			        addWorkerBtn.setOnAction(e->{
			            addstage.setScene(addcashflow);
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
			        cashflowpage = new Scene(bp, 500, 500);
			        cashflowpage.getStylesheets().add(getClass().getResource("a.css").toExternalForm());


			        // Set up the scene
			        addcashflow = new Scene(gridPane, 300, 200);
			        addcashflow.getStylesheets().add(getClass().getResource("a.css").toExternalForm());

			}
	        
}
