//AAA
public class Customer {
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name= name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
    public void pay(double amount) {
        if (balance < amount) {
            throw new IllegalStateException("Not enough balance.");
        }
        balance -= amount;
    }

    public void printBalance() {
        System.out.println("balance:" + balance);
    }
}
