public class Expense {

    private String day;
    private String category;
    private double amount;
    private String description;

    public Expense(String day, String category, double amount, String description) {

        this.day = day;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    // Getter method for day
    public String getDay() {
        return day;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    // Getter method for description
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {

        return "Day: " + day +
                ", Category: " + category +
                ", Amount: $" + amount +
                ", Description: " + description;
    }
}
