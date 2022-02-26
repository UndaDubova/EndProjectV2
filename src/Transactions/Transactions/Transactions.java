package Transactions.Transactions;

public class Transactions {
    private int Id;
    private String date;
    private String currency;
    private Double sum;
    private String name;
    private Category category;
    private TransactionType type;

    public Transactions() {
    }

    public Transactions(int Id, String date, String currency, Double sum, String name, Category category, TransactionType type) {
        this.Id = Id;
        this.date = date;
        this.currency = currency;
        this.sum = sum;
        this.name = name;
        this.category = category;
        this.type = type;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return
                "Id: " + Id +
                ", date: '" + date +
                ", currency: '" + currency +
                ", sum: " + sum +
                ", name: " + name  +
                ", category: " + category +
                '}';
    }
}
