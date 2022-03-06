package Transactions.Transactions;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;

public class TransactionsForTable {
    private int Id;
    private SimpleStringProperty date;
    private SimpleStringProperty currency;
    private Double sum;
    private SimpleStringProperty purchase;
    private ComboBox category;

    public TransactionsForTable() {}

    public TransactionsForTable(String date, String currency, Double sum, String purchase) {
        this.Id = 1;
        this.date = new SimpleStringProperty(date);
        this.currency = new SimpleStringProperty(currency);
        this.sum = sum;
        this.purchase = new SimpleStringProperty(purchase);
        this.category = new ComboBox();
    }



    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public SimpleStringProperty getDate() {
        return date;
    }

    public void setDate(SimpleStringProperty date) {
        this.date = date;
    }

    public SimpleStringProperty getCurrency() {
        return currency;
    }

    public void setCurrency(SimpleStringProperty currency) {
        this.currency = currency;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public SimpleStringProperty getPurchase() {
        return purchase;
    }

    public void setName(SimpleStringProperty purchase) {
        this.purchase = purchase;
    }

    public ComboBox getCategory() {
        return category;
    }

    public void setCategory(ComboBox category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return
                "Id: " + Id +
                        ", date: '" + date +
                        ", currency: '" + currency +
                        ", sum: " + sum +
                        ", purchase: " + purchase  +
                        ", category: " + category +
                        '}';
    }
}
