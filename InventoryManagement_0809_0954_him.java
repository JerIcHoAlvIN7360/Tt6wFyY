// 代码生成时间: 2025-08-09 09:54:49
import io.quarkus.runtime.Quarkus;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.ConcurrentHashMap;

@Path('/inventory')
public class InventoryManagement {
    // A concurrent map to simulate the inventory
    private final ConcurrentHashMap<String, Integer> inventory;

    public InventoryManagement() {
        this.inventory = new ConcurrentHashMap<>();
    }

    // Add an item to the inventory
    @POST
    @Path('/add/{itemId}/{quantity}')
    @Produces(MediaType.TEXT_PLAIN)
    public String addItem(@PathParam("itemId") String itemId, @PathParam("quantity") int quantity) {
        if (quantity < 0) {
            return "Error: Quantity cannot be negative.";
        }

        inventory.put(itemId, inventory.getOrDefault(itemId, 0) + quantity);
        return "Item added successfully.";
    }

    // Remove an item from the inventory
    @POST
    @Path('/remove/{itemId}/{quantity}')
    @Produces(MediaType.TEXT_PLAIN)
    public String removeItem(@PathParam("itemId") String itemId, @PathParam("quantity") int quantity) {
        if (quantity < 0) {
            return "Error: Quantity cannot be negative.";
        }

        Integer currentQuantity = inventory.get(itemId);
        if (currentQuantity == null || currentQuantity < quantity) {
            return "Error: Not enough stock to remove.";
        }

        inventory.put(itemId, currentQuantity - quantity);
        return "Item removed successfully.";
    }

    // Get inventory item details
    @GET
    @Path('/{itemId}')
    @Produces(MediaType.APPLICATION_JSON)
    public String getItem(@PathParam("itemId") String itemId) {
        Integer quantity = inventory.get(itemId);
        if (quantity == null) {
            return "{\"error\": \"Item not found.\"}";
        }

        return "{\"itemId\": \" + itemId + \