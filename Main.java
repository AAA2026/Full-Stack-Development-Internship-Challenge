import java.time.LocalDate;
//AAA
public class Main {
    public static void main(String[] args) {
        runAllTests();
    }

    public static void runAllTests() {
        base();
        expired();
        outofstock();
        emptycart();
        balanceleak();
        noshipping();
        total();
    }

public static void base() {
    System.out.println("base");
    runTest(
        new Customer("khalid", 500),
        new Object[][] {
            { new Cheese("Cheese", 100, 10, 400, LocalDate.of(2025, 8, 1)), 2 },
            { new MobileScratchCard("Scratch Card", 50, 5), 1 }
        }
    );
}

public static void expired() {
    System.out.println("expired");
    tryAddToCart(
        new Biscuits("Biscuits", 150, 3, 700, LocalDate.of(2023, 1, 1)),
        1
    );
}

public static void outofstock() {
    System.out.println("out of stock");
    tryAddToCart(new TV("TV", 2000, 1, 10000), 2);
}

public static void emptycart() {
    System.out.println("empty cart");
    tryCheckout(new Customer("AAA", 1000), new Cart());
}

public static void balanceleak() {
    System.out.println("balance leak");
    Customer customer = new Customer("ahmed", 800);
    Cart cart = new Cart();
    cart.add(new TV("TV", 900, 2, 10000), 1);
    tryCheckout(customer, cart);
}

public static void total() {
    System.out.println("total everything");
    runTest(
        new Customer("Omar", 1000),
        new Object[][] {
            { new Cheese("Cheese", 100, 10, 400, LocalDate.of(2025, 8, 1)), 2 },
            { new Biscuits("Biscuits", 150, 10, 700, LocalDate.of(2025, 8, 1)), 1 }
        }
    );
}

public static void noshipping() {
    System.out.println("no shipping");
    runTest(
        new Customer("mo", 500),
        new Object[][] {
            { new MobileScratchCard("Scratch Card", 50, 3), 2 }
        }
    );
}
private static void runTest(Customer customer, Object[][] items) {
    Cart cart = new Cart();
    for (Object[] pair : items) {
        Product product = (Product) pair[0];
        int quantity = (int) pair[1];
        cart.add(product, quantity);
    }
    Checkout.process(customer, cart);
    System.out.println();
}

private static void tryAddToCart(Product product, int quantity) {
    Cart cart = new Cart();
    try {
        cart.add(product, quantity);
    } catch (Exception e) {
        System.out.println("Expected error: " + e.getMessage());
    }
    System.out.println();
}

private static void tryCheckout(Customer customer, Cart cart) {
    try {
        Checkout.process(customer, cart);
    } catch (Exception e) {
        System.out.println("Expected error: " + e.getMessage());
    }
    System.out.println();
}
}