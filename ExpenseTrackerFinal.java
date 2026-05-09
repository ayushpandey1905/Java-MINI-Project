import java.util.ArrayList;
import java.util.Scanner;

// Class for Expense
class Expense {

    String category;
    double amount;
    String date;

    // Constructor
    Expense(String category, double amount, String date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    // Method to display expense
    void displayExpense() {
        System.out.println(category + "\t₹" + amount + "\t" + date);
    }
}

// Main Class
public class ExpenseTrackerSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ArrayList to store expenses
        ArrayList<Expense> expenses = new ArrayList<>();

        int choice;

        do {

            System.out.println("\n===== EXPENSE TRACKER SYSTEM =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Calculate Total Expense");
            System.out.println("4. Search Expense by Category");
            System.out.println("5. Delete Expense");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                // ADD EXPENSE
                case 1:

                    System.out.print("Enter Category: ");
                    String category = sc.nextLine();

                    System.out.print("Enter Amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Enter Date (dd-mm-yyyy): ");
                    String date = sc.nextLine();

                    Expense e = new Expense(category, amount, date);

                    expenses.add(e);

                    System.out.println("Expense Added Successfully!");
                    break;

                // VIEW EXPENSES
                case 2:

                    if (expenses.size() == 0) {

                        System.out.println("No Expenses Found!");

                    } else {

                        System.out.println("\nCategory\tAmount\tDate");
                        System.out.println("--------------------------------");

                        for (Expense ex : expenses) {
                            ex.displayExpense();
                        }
                    }

                    break;

                // TOTAL EXPENSE
                case 3:

                    double total = 0;

                    for (Expense ex : expenses) {
                        total = total + ex.amount;
                    }

                    System.out.println("Total Expense = ₹" + total);

                    break;

                // SEARCH CATEGORY
                case 4:

                    System.out.print("Enter Category to Search: ");
                    String search = sc.nextLine();

                    boolean found = false;

                    for (Expense ex : expenses) {

                        if (ex.category.equalsIgnoreCase(search)) {

                            ex.displayExpense();
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("Expense Not Found!");
                    }

                    break;

                // DELETE EXPENSE
                case 5:

                    System.out.print("Enter Expense Number to Delete: ");

                    int index = sc.nextInt();

                    if (index > 0 && index <= expenses.size()) {

                        expenses.remove(index - 1);

                        System.out.println("Expense Deleted Successfully!");

                    } else {

                        System.out.println("Invalid Expense Number!");
                    }

                    break;

                // EXIT
                case 6:

                    System.out.println("Thank You for Using Expense Tracker!");
                    break;

                default:

                    System.out.println("Invalid Choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}


