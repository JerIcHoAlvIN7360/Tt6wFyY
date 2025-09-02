// 代码生成时间: 2025-09-03 04:56:38
package com.example.databasemigration;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.flywaydb.core.Flyway;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * The DatabaseMigrationTool class is responsible for handling database migrations.
 */
@QuarkusMain
@ApplicationScoped
public class DatabaseMigrationTool {

    private DataSource dataSource;

    /**
     * Initializes the data source.
     *
     * @param dataSource The data source to be initialized.
     */
    public void init(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Performs the database migration.
     */
    @PostConstruct
    public void migrateDatabase() {
        try (Connection connection = dataSource.getConnection()) {
            Flyway flyway = Flyway.configure()
                    .dataSource(connection)
                    .load();

            flyway.migrate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    /**
     * The main method that starts the migration process.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Quarkus.run(DatabaseMigrationTool.class, args);
    }

    /**
     * Observer method that starts the migration process when the application starts.
     *
     * @param quarkusApplication Startup event.
     */
    public void onStart(@Observes StartupEvent quarkusApplication) {
        new DatabaseMigrationTool().migrateDatabase();
    }
}
