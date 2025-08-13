// 代码生成时间: 2025-08-14 03:06:35
import io.quarkus.runtime.StartupEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/permissions")
@ApplicationScoped
public class UserPermissionService {

    @PersistenceContext
    EntityManager entityManager;

    // POST endpoint to create a new permission
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Permission createPermission(Permission permission) {
        try {
            entityManager.persist(permission);
            entityManager.flush();
            return permission;
        } catch (Exception e) {
            // Handle the error
            throw new RuntimeException("Failed to create permission", e);
        }
    }

    // GET endpoint to retrieve a permission by id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Permission getPermissionById(@PathParam("id") Long id) {
        Optional<Permission> permission = entityManager.find(Permission.class, id);
        if (permission.isPresent()) {
            return permission.get();
        } else {
            // Handle not found exception
            throw new RuntimeException("Permission not found");
        }
    }

    // GET endpoint to retrieve all permissions
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Permission> getAllPermissions() {
        return entityManager.createQuery("SELECT p FROM Permission p", Permission.class).getResultList();
    }

    // Represents a User Permission
    public static class Permission {
        private Long id;
        private String name;
        private List<String> roles;

        // Getters and setters
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public List<String> getRoles() {
            return roles;
        }
        public void setRoles(List<String> roles) {
            this.roles = roles;
        }
    }
}
