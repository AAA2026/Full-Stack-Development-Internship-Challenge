import java.util.*;
//AAA
class ShippingService {
    public static void ship(List<beingshipped> items) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        Map<String, Integer> counts = new LinkedHashMap<>();

        for (beingshipped item : items) {
            counts.put(item.getname(), counts.getOrDefault(item.getname(), 0) + 1);
            totalWeight += item.getWeight();
        }

        for (var entry : counts.entrySet()) {
            System.out.println(entry.getValue() + "x " + entry.getKey());
        }

        System.out.printf("Total package weight %.1fkg\n", totalWeight / 1000);
    }
}

public class Checkout {
    public static void process(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalArgumentException("empty cart.");
        }

        double subtotal = cart.calculateSubtotal();
        double shippingFee = cart.calculateShippingFee();
        double total = subtotal + shippingFee;

        if (customer.getBalance() < total) {
            throw new IllegalStateException("Not enough balance.");
        }

        for (CartItem item : cart.getItems()) {
            item.product.reducequ(item.quantity);
        }

        List<beingshipped> shippables = cart.getShippableItems();
        if (!shippables.isEmpty()) {
            ShippingService.ship(shippables);
        }

        customer.pay(total);

        System.out.println("-- receipt --");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %s\t%.0f\n", item.quantity, item.product.getname(), item.product.getprice() * item.quantity);
        }
        System.out.println("^^^^^^^^^^^^^^^^^^");
        System.out.printf("Subtotal \t%.0f\n", subtotal);
        System.out.printf("Shipping \t%.0f\n", shippingFee);
        System.out.printf("Amount\t \t%.0f\n", total);
        customer.printBalance();
    }
}
