package Transactions.Transactions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TransactionsController {

    // Metode "readFile" nolasa CSV failu, atdod atpakaļ arraylisti ar transakcijām.

    public static ArrayList readFile(String filename) {
        ArrayList transactions = new ArrayList();
        String line = "";
        String splitBy = ";";
        try {

            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                int k = 0;
                int Id = 0;
                while ((line = br.readLine()) != null) { //šī daļa (29-32) ļauj izlaist pirmo rindu CSV failā
                    if (k == 0) {
                        k++;
                        continue;
                    }
                    String[] transactionData = line.split(splitBy);
                    //System.out.println("Date of payment: " + transactionData[1] + ", Currency: " + transactionData[2] + ", Sum: " + transactionData[3] + " Name: " + transactionData[4]);

                    String date = transactionData[1];
                    String currency = transactionData[2];
                    double sum = Double.parseDouble(transactionData[3]);
                    String name = transactionData[4];
                    Category category = Category.UNSORTED;
                    Id++;
                    Transactions transaction = new Transactions(Id, date, currency, sum, name, category);
                    transactions.add(transaction);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }
    public static ArrayList readInput(String dateInput, String currencyInput, double priceInput, String placeInput) {
        ArrayList currentTransactions = new ArrayList();
                    String date = dateInput;
                    String currency = currencyInput;
                    double price = priceInput;
                    String place = placeInput ;
                    Category category = Category.UNSORTED;
                    //if -  vs savings - depending on C or D in csv
                     int Id = 1;
                    Transactions transaction = new Transactions(Id, date, currency, price, place, category);
                    currentTransactions.add(transaction);
            return currentTransactions;
                }

    }
   /* public static void changeCategory(int id,Category category){
        for(int i = 0; i < transactions.size(); i++) {
            System.out.println(transactions.get(i).toString());
        }
    }*/

//Earnings Spendings
//metode priekš kategoriju salikšanas
    // metodes priekš skatīšās -
    // javafx - graphs and reports
