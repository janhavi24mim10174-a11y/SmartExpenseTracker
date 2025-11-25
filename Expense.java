public class Expense {
    private double amount;
    private String category;
    private String note;

    public Expense(double amount, String category, String note) {
        this.amount = amount;
        this.category = category;
        this.note = note;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return amount + " | " + category + " | " + note;
    }
}
