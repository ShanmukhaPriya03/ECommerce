import java.util.*;

public class SimpleECommerceApp {

    static class Product {
        int id;
        String name;
        int price;

        Product(int id, String name, int price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }
    }

    static class CartItem {
        Product product;
        int quantity;

        CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }
    }

    static List<Product> productList = new ArrayList<>();
    static List<CartItem> cart = new ArrayList<>();

    public static void main(String[] args) {
        initProducts();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nWelcome to Simple E-Commerce App");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: displayProducts(); break;
                case 2: addToCart(scanner); break;
                case 3: viewCart(); break;
                case 4: checkout(); break;
                case 5: System.out.println("Thanks for shopping!"); return;
                default: System.out.println("Invalid option");
            }
        }
    }

    static void initProducts() {
        productList.add(new Product(1, "Book", 100));
        productList.add(new Product(2, "Pen", 10));
        productList.add(new Product(3, "Notebook", 50));
    }

    static void displayProducts() {
        System.out.println("\nAvailable Products:");
        for (Product p : productList) {
            System.out.printf("%d: %s - ₹%d\n", p.id, p.name, p.price);
        }
    }

    static void addToCart(Scanner scanner) {
        displayProducts();
        System.out.print("Enter product id to add: ");
        int id = scanner.nextInt();
        System.out.print("Enter quantity: ");
        int qty = scanner.nextInt();

        Product selected = null;
        for (Product p : productList) {
            if (p.id == id) {
                selected = p;
                break;
            }
        }

        if (selected == null) {
            System.out.println("Invalid product id.");
            return;
        }

        for (CartItem item : cart) {
            if (item.product.id == id) {
                item.quantity += qty;
                System.out.println("Added to existing cart item.");
                return;
            }
        }

        cart.add(new CartItem(selected, qty));
        System.out.println("Added to cart.");
    }

    static void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println("\nYour Cart:");
        int total = 0;
        for (CartItem item : cart) {
            int subtotal = item.product.price * item.quantity;
            total += subtotal;
            System.out.printf("%s - ₹%d x %d = ₹%d\n",
                    item.product.name, item.product.price, item.quantity, subtotal);
        }
        System.out.println("Total: ₹" + total);
    }

    static void checkout() {
        viewCart();
        if (!cart.isEmpty()) {
            System.out.println("Checkout successful!");
            cart.clear();
        }
    }
}