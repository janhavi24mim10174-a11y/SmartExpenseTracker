import java.util.ArrayList;

public class ExpenseManager {

    private ArrayList<Expense> expenses = new ArrayList<>();

    public void addExpense(Expense e) {
        expenses.add(e);
    }

    public ArrayList<Expense> getAllExpenses() {
        return expenses;
    }

    public ArrayList<Expense> getByCategory(String category) {
        ArrayList<Expense> list = new ArrayList<>();
        for (Expense e : expenses) {
            if (e.getCategory().equalsIgnoreCase(category)) {
                list.add(e);
            }
        }
        return list;
    }

    public double getTotal() {
        double sum = 0;
        for (Expense e : expenses) {
            sum += e.getAmount();
        }
        return sum;
    }

    public boolean deleteExpense(int index) {
        if (index < 0 || index >= expenses.size()) {
            return false;
        }
        expenses.remove(index);
        return true;
    }
}
