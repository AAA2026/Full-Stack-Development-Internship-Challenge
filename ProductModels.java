import java.time.LocalDate;
//AAA
abstract class Product {
    protected String name;
    protected double price;
    protected int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public abstract boolean expired();
    public abstract boolean shipneed();
    public boolean available(int requestedQty) {
        return !expired() && quantity >= requestedQty;
    }
    public String getname() { return name; }
    public double getprice() { return price; }
    public int getqu() { return quantity; }
    public void reducequ(int qty) { quantity -= qty; }

    @Override
    public String toString() {
        return getname();
    }
}

interface beingshipped {
    String getname();
    double getWeight();
}
abstract class shippable extends Product implements beingshipped {
    protected double weight;
    public shippable(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }
    @Override
    public boolean shipneed() {
        return true;
    }
    @Override
    public double getWeight() {
        return weight;
    }
    @Override
    public String toString() {
        return getname();
    }
}
abstract class PerishableProduct extends shippable {
    protected LocalDate expiryDate;

    public PerishableProduct(String name, double price, int quantity, double weight, LocalDate expiryDate) {
        super(name, price, quantity, weight);
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean expired() {
        return LocalDate.now().isAfter(expiryDate);
    }
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return getname() + " (Expires: " + expiryDate + ")";
    }
}

class Cheese extends PerishableProduct {
    public Cheese(String name, double price, int quantity, double weight, LocalDate expiryDate) {
        super(name, price, quantity, weight, expiryDate);
    }
}

class Biscuits extends PerishableProduct {
    public Biscuits(String name, double price, int quantity, double weight, LocalDate expiryDate) {
        super(name, price, quantity, weight, expiryDate);
    }
}

class TV extends shippable {
    public TV(String name, double price, int quantity, double weight) {
        super(name, price, quantity, weight);
    }
    @Override
    public boolean expired() {
        return false;
    }
}
class MobileScratchCard extends Product {
    public MobileScratchCard(String name, double price, int quantity) {
        super(name, price, quantity);
    }
    @Override
    public boolean expired() {
        return false;
    }
    @Override
    public boolean shipneed() {
        return false;
    }
    @Override
    public String toString() {
        return getname();
    }
}
