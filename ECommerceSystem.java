import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Product {
    private int productId;
    private String name;
    private double price;

    public Product(int productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class ShoppingCart {
    private Map<Product, Integer> items = new HashMap<>();

    public void addItem(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public void removeItem(Product product) {
        items.remove(product);
    }

    public Map<Product, Integer> getItems() {
        return items;
    }
}

class Order {
    private int orderId;
    private User user;
    private Map<Product, Integer> orderedItems = new HashMap<>();

    public Order(int orderId, User user, Map<Product, Integer> orderedItems) {
        this.orderId = orderId;
        this.user = user;
        this.orderedItems.putAll(orderedItems);
    }

    public int getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public Map<Product, Integer> getOrderedItems() {
        return orderedItems;
    }
}

public class ECommerceSystem {
    private static int orderIdCounter = 1;
    private static List<User> users = new ArrayList<>();
    private static List<Product> products = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        initializeData();

        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;
        User currentUser = null;

        while (true) {
            if (!loggedIn) {
                System.out.println("E-Commerce System");
                System.out.println("1. Register");
                System.out.println("2. Log In");
                System.out.println("3. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter a username: ");
                        String regUsername = scanner.nextLine();
                        System.out.print("Enter a password: ");
                        String regPassword = scanner.nextLine();
                        User newUser = new User(regUsername, regPassword);
                        users.add(newUser);
                        System.out.println("Registration successful!");
                        break;
                    case 2:
                        System.out.print("Enter your username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter your password: ");
                        String password = scanner.nextLine();
                        currentUser = authenticateUser(username, password);
                        if (currentUser != null) {
                            System.out.println("Login successful!");
                            loggedIn = true;
                        } else {
                            System.out.println("Invalid username or password. Please try again.");
                        }
                        break;
                    case 3:
                        System.out.println("Goodbye!");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Welcome, " + currentUser.getUsername() + "!");
                System.out.println("E-Commerce Menu");
                System.out.println("1. View Products");
                System.out.println("2. Add to Cart");
                System.out.println("3. View Cart");
                System.out.println("4. Checkout");
                System.out.println("5. Log Out");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        displayProducts();
                        break;
                    case 2:
                        displayProducts();
                        System.out.print("Enter the product ID to add to your cart: ");
                        int productId = scanner.nextInt();
                        scanner.nextLine();
                        Product selectedProduct = findProductById(productId);
                        if (selectedProduct != null) {
                            System.out.print("Enter the quantity: ");
                            int quantity = scanner.nextInt();
                            scanner.nextLine();
                            currentUserCart(currentUser).addItem(selectedProduct, quantity);
                            System.out.println(quantity + " " + selectedProduct.getName() + "(s) added to your cart.");
                        } else {
                            System.out.println("Product not found.");
                        }
                        break;
                    case 3:
                        displayCart(currentUserCart(currentUser));
                        break;
                    case 4:
                        checkout(currentUser);
                        loggedIn = false;
                        currentUser = null;
                        break;
                    case 5:
                        System.out.println("Logging out...");
                        loggedIn = false;
                        currentUser = null;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    private static void initializeData() {
        products.add(new Product(1, "Laptop", 999.99));
        products.add(new Product(2, "Smartphone", 499.99));
        products.add(new Product(3, "Tablet", 299.99));
        products.add(new Product(4, "Headphones", 99.99));
    }

    private static User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private static void displayProducts() {
        System.out.println("Available Products:");
        for (Product product : products) {
            System.out.println(product.getProductId() + ". " + product.getName() + " - $" + product.getPrice());
        }
    }

    private static Product findProductById(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    private static ShoppingCart currentUserCart(User user) {
        for (Order order : orders) {
            if (order.getUser().equals(user)) {
                return new ShoppingCart(order.getOrderedItems());
            }
        }
        ShoppingCart newCart = new ShoppingCart();
        orders.add(new Order(orderIdCounter++, user, newCart.getItems()));
        return newCart;
    }

    private static void displayCart(ShoppingCart cart) {
        System.out.println("Your Shopping Cart:");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(product.getName() + " - $" + product.getPrice() + " x " + quantity);
        }
    }

    private static void checkout(User user) {
        ShoppingCart cart = currentUserCart(user);
        if (cart.getItems().isEmpty()) {
            System.out.println("Your cart is empty. Add items before checking out.");
            return;
        }

        double total = calculateTotal(cart);
        System.out.println("Total: $" + total);

        System.out.print("Confirm checkout (yes/no): ");
        Scanner scanner = new Scanner(System.in);
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Thank you for your order!");
            orders.removeIf(order -> order.getUser().equals(user));
        } else {
            System.out.println("Checkout canceled.");
        }
    }

    private static double calculateTotal(ShoppingCart cart) {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }
}
