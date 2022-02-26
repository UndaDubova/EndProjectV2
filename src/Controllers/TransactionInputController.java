package Controllers;
import Repository.DatabaseManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.time.*;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class TransactionInputController implements Initializable {
    @FXML private Label filePathLabel;
    @FXML private DatePicker dateInput;
    @FXML private TextField purchaseInput;
    @FXML private TextField priceInput;
    @FXML private ChoiceBox currencyInput;
    String transactionInputFilePath;


    DatabaseManager databaseManager;
    Connection conn;

    public TransactionInputController(){}
    public void initialize(URL url, ResourceBundle resourceBundle) {
        databaseManager = new DatabaseManager();
        conn = databaseManager.getConnection();

        currencyInput.getItems().add("USD");
        currencyInput.getItems().add("GBP");
        currencyInput.getItems().add("EUR");

    }


    @FXML
    private void BrowseForFileClick (ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", "*.CSV"));
        File f = fc.showOpenDialog(null);
       if(f!= null){

           String transactionInputFilePath = f.getAbsolutePath();
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

        System.out.println(transactionDate + transactionPurchase + transactionPrice + transactionCurrency);
    }

    @FXML
    private void doneButtonPressed(ActionEvent event){
        ArrayList transactionsList = new ArrayList(Transactions.Transactions.TransactionsController.readFile(transactionInputFilePath));

        for(int i = 0; i < transactionsList.size(); i++) {
            System.out.println(transactionsList.get(i).toString());
        }
    }
}


