package Controllers;
import Repository.DatabaseManager;
import Transactions.Transactions.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;


public class TransactionInputController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    //ID of the next element to be added
    //int nextTransactionID;

// For the transaction input view
    @FXML private Label filePathLabel;
    @FXML private DatePicker dateInput;
    @FXML private TextField purchaseInput;
    @FXML private TextField priceInput;
    @FXML private ChoiceBox currencyInput;
    String transactionInputFilePath;

// For the transaction categories view
    @FXML private TableColumn<Transactions,String>dateColumn;
    @FXML private TableColumn<Transactions,String>currencyColumn;
    @FXML private TableColumn<Transactions,Double>priceColumn;
    @FXML private TableColumn<Transactions,String>purchaseColumn;
    @FXML private TableColumn<Transactions,String>categoryColumn;
    @FXML private ComboBox categoryChooser;


    private TableView tableView = new TableView();
    ObservableList<TransactionsForTable>transactionsForTableList;


    DatabaseManager databaseManager;
    Connection conn;

    public TransactionInputController(){}

    public void initialize(URL url, ResourceBundle resourceBundle) {
        //nextTransactionID = 0;
        //write code to overwrite this with the highest transaction ID in the database later
        databaseManager = new DatabaseManager();
        conn = databaseManager.getConnection();

        //for the currency input choicebox in transaction input window
        currencyInput.getItems().add("USD");
        currencyInput.getItems().add("GBP");
        currencyInput.getItems().add("EUR");
        // for the tableview

        TableColumn dateColumn = new TableColumn("Datele");
        TableColumn currencyColumn = new TableColumn("Currency");
        TableColumn priceColumn = new TableColumn("Price");
        TableColumn purchaseColumn = new TableColumn("Purchase");
        this.tableView.getColumns().addAll(new Object[]{dateColumn, currencyColumn, priceColumn, purchaseColumn});

        //For the observableList in transaction category window (date, currency, sum, name)
        transactionsForTableList = FXCollections.observableArrayList(

        new TransactionsForTable("22.03.23", "GBP", 34d, "Socks"),
        new TransactionsForTable("22.03.23", "EUR", 34d, "Socks"),
        new TransactionsForTable("22.03.23", "GBP", 31d, "Socks"),
        new TransactionsForTable("22.03.23", "GBP", 34d, "ducks"),
        new TransactionsForTable("22.03.23", "USD", 3d, "Jam")
        );

        dateColumn.setCellValueFactory(new PropertyValueFactory("firstName"));
        currencyColumn.setCellValueFactory(new PropertyValueFactory("lastName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory("email"));
        purchaseColumn.setCellValueFactory(new PropertyValueFactory("remark"));
        this.tableView.setItems(this.transactionsForTableList);


    }


    @FXML
    private void BrowseForFileClick (ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", "*.CSV"));
        File f = fc.showOpenDialog(null);
       if(f!= null){

           transactionInputFilePath = f.getAbsolutePath();
           System.out.println(transactionInputFilePath);
           filePathLabel.setText(transactionInputFilePath);
       }

    }

    @FXML
    private void AddButtonPressed(ActionEvent event){
        LocalDate transactionDate = dateInput.getValue();
        String transactionPurchase = purchaseInput.getText();
        double transactionPrice = Double.parseDouble(priceInput.getText());
        String transactionCurrency = currencyInput.getValue().toString();

        //int transactionID = nextTransactionID;
        //nextTransactionID++;
        //System.out.println(nextTransactionID);


        System.out.println(transactionDate + transactionPurchase + transactionPrice + transactionCurrency);
        //loadScene(event, "Views/TransactionCategories.fxml", 900, 475);
    }

    @FXML
    private void doneButtonPressed(ActionEvent event) {
        System.out.println(transactionInputFilePath);
        ArrayList transactionsList = new ArrayList(TransactionsController.readFile(transactionInputFilePath));
        loadScene(event, "Views/TransactionCategories.fxml", 900, 475);
        System.out.println("Here2");
        for (int i = 0; i < transactionsList.size(); i++) {
            //System.out.println(transactionsList.get(i).toString());
//Now I need it to not print that list out here but to get that list in the scene 2
        }
    }

    @FXML
    private void doneButton2Pressed(ActionEvent event){
        loadScene(event, "Views/TransactionCategories.fxml", 900, 475);
        }

    //Handle switching between fxml file scenes
    private void loadScene(ActionEvent event, String location, int width, int height){
        try {
            System.out.println(location);
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(location));      //absolute reference for file path of scene
            loader.setController(this);     //since we're using the same controller, need to pass in a reference to itself "this" controller
            //this gives us access to all of the class variables from both scenes, like arrayLists
            scene = new Scene((Pane) loader.load(), width, height);                                       //set width and height of scene
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception){
            System.out.println(exception);
        }
    }
}





