import java.util.*;
//AAA
class CartItem {
    public Product product;
    public int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) {
        if (!product.available(quantity)) {
            throw new IllegalArgumentException("product is unavailable or expired:" + product.getname());
        }
        items.add(new CartItem(product, quantity));
    }
    public List<CartItem> getItems() {
        return items;
    }
    public boolean isEmpty() {
        return items.isEmpty();
    }
    public double calculateSubtotal() {
        return items.stream()
                .mapToDouble(i -> i.product.getprice() * i.quantity)
                .sum();
    }
    public double calculateShippingFee() {
        return getShippableItems().isEmpty() ? 0 : 30.0;
    }
    public List<beingshipped> getShippableItems() {
        List<beingshipped> shippables = new ArrayList<>();
        for (CartItem item : items) {
            if (item.product.shipneed()) {
                for (int i = 0; i < item.quantity; i++) {
                    shippables.add((beingshipped) item.product);
                }
            }
        }
        return shippables;
    }

}
