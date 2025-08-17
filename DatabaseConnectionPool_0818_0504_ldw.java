// 代码生成时间: 2025-08-18 05:04:20
import io.quarkus.runtime.StartupEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@ApplicationScoped
public class DatabaseConnectionPool {

    @Inject
    @ConfigProperty(name = "db.datasource.url")
    String datasourceUrl;

    @Inject
    @ConfigProperty(name = "db.datasource.username")
    String datasourceUsername;

    @Inject
    @ConfigProperty(name = "db.datasource.password")
    String datasourcePassword;

    @Inject
    DataSource dataSource;

    public void onStart(@Observes StartupEvent ev) {
        try {
            // Try to establish a connection to verify the pool is working
            Connection connection = dataSource.getConnection();
            connection.close();
            System.out.println("Database connection pool is ready.");
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
       }
    }

    @Readiness
    public HealthCheckResponse checkDBConnection() {
        try {
            // Check if we can get a connection from the pool
            Connection connection = dataSource.getConnection();           
            connection.close();
            return HealthCheckResponse.up("Database connection is ready");
        } catch (SQLException e) {
            return HealthCheckResponse.down("Failed to get connection from pool: " + e.getMessage());
        }
    }
}
