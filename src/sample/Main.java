package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../Views/TransactionInput.fxml"));
        primaryStage.setTitle("Finance tracker");
        primaryStage.setScene(new Scene(root, 600, 575));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        String filename = "C:\\Users\\Unda\\Documents\\Programming stuff\\Java\\AccentureBootcamp\\EndProject\\kontaparskats.csv";

        ArrayList transactionsList = new ArrayList(Transactions.Transactions.TransactionsController.readFile(filename));

        for (int i = 0; i < transactionsList.size(); i++) {
            System.out.println(transactionsList.get(i).toString());
        }
    }
}
