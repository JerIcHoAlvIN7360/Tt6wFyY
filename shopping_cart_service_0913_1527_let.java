// 代码生成时间: 2025-09-13 15:27:36
import io.quarkus.runtime.annotations.RegisterForReflection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/cart")
@RegisterForReflection
public class ShoppingCartService {

    // Map to simulate a database for product storage and cart management
    private Map<Integer, Product> products = new HashMap<>();
    private Map<Integer, ShoppingCart> usersCart = new HashMap<>();
    private int productIdGenerator = 1;
    private int userIdGenerator = 1;

    // Dummy product class
    public static class Product {
        private int id;
        private String name;
        private double price;

        public Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        // Getters and setters
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }

    // Dummy shopping cart class
    public static class ShoppingCart {
        private List<Product> items;
        private int userId;

        public ShoppingCart(int userId) {
            this.userId = userId;
            this.items = new ArrayList<>();
        }

        public void addItem(Product product) {
            items.add(product);
        }

        public List<Product> getItems() {
            return items;
        }
    }

    // Method to add a product to the cart
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProductToCart(Product product, int userId) {
        if (product == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Product cannot be null").build();
        }

        if (!usersCart.containsKey(userId)) {
            usersCart.put(userId, new ShoppingCart(userId));
        }

        usersCart.get(userId).addItem(product);
        return Response.ok("Product added to cart").build();
    }

    // Method to create a new product
    @POST
    @Path("/product")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) {
        if (product == null || product.getName() == null || product.getName().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Product details are incomplete").build();
        }

        product.setId(productIdGenerator++);
        products.put(product.getId(), product);
        return Response.ok("Product created").build();
    }

    // Method to retrieve a user's cart
    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserCart(@PathParam("userId") int userId) {
        if (!usersCart.containsKey(userId)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User cart not found").build();
        }

        return Response.ok(usersCart.get(userId).getItems()).build();
    }

    // Main method for testing
    public static void main(String[] args) {
        ShoppingCartService service = new ShoppingCartService();

        // Create products
        Product apple = new Product(0, "Apple", 0.50);
        Product banana = new Product(0, "Banana", 0.30);
        service.createProduct(apple);
        service.createProduct(banana);

        // Add products to cart
        service.addProductToCart(apple, 1);
        service.addProductToCart(banana, 1);

        // Get cart
        Response response = service.getUserCart(1);
        System.out.println(response.getEntity());
    }
}
