import java.io.*;
import java.util.*;

public class Main {

    static ExpenseManager manager = new ExpenseManager();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "expenses.txt";

    public static void main(String[] args) {
        loadFromFile();
        
        while (true) {
            System.out.println("\n===== EXPENSE TRACKER =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. View By Category");
            System.out.println("4. View Total");
            System.out.println("5. Delete Expense");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();
            sc.nextLine(); 

            switch (ch) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewAll();
                    break;
                case 3:
                    viewByCategory();
                    break;
                case 4:
                    System.out.println("Total Spent: " + manager.getTotal());
                    break;
                case 5:
                    deleteExpense();
                    break;
                case 6:
                    saveToFile();
                    System.out.println("Saved and exited.");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void addExpense() {
        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter category: ");
        String category = sc.nextLine();

        System.out.print("Enter a short note: ");
        String note = sc.nextLine();

        manager.addExpense(new Expense(amount, category, note));
        System.out.println("Expense added!");
    }

    static void viewAll() {
        ArrayList<Expense> list = manager.getAllExpenses();
        if (list.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }

        System.out.println("\n--- All Expenses ---");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + ". " + list.get(i));
        }
    }

    static void viewByCategory() {
        System.out.print("Enter category: ");
        String cat = sc.nextLine();

        ArrayList<Expense> list = manager.getByCategory(cat);

        if (list.isEmpty()) {
            System.out.println("No expenses found in this category.");
            return;
        }

        System.out.println("\n--- " + cat + " Expenses ---");
        for (Expense e : list) {
            System.out.println(e);
        }
    }

    static void deleteExpense() {
        viewAll();
        System.out.print("Enter index to delete: ");
        int index = sc.nextInt();

        if (manager.deleteExpense(index)) {
            System.out.println("Expense deleted.");
        } else {
            System.out.println("Invalid index!");
        }
    }

    static void saveToFile() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME));
            for (Expense e : manager.getAllExpenses()) {
                pw.println(e.getAmount() + "," + e.getCategory() + "," + e.getNote());
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }

    static void loadFromFile() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return;

            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(",");
                double amount = Double.parseDouble(parts[0]);
                String category = parts[1];
                String note = parts[2];
                manager.addExpense(new Expense(amount, category, note));
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
    }
}
