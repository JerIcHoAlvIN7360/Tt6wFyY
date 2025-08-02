// 代码生成时间: 2025-08-02 08:13:11
package com.example.database;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.MigrationState;
import org.jboss.logging.Logger;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@QuarkusMain
@Command(name = "database-migration-tool", mixinStandardHelpOptions = true, versionProvider = DatabaseMigrationTool.class)
public class DatabaseMigrationTool implements QuarkusApplication {

    private static final Logger LOGGER = Logger.getLogger(DatabaseMigrationTool.class);

    @Option(names = "--migrate", description = "Perform the database migration.")
    private boolean migrate = false;

    @Option(names = "--validate", description = "Validate the database migration.")
    private boolean validate = false;

    @Option(names = { "--url" }, description = "The JDBC URL of the database.")
    private String url = "jdbc:h2:mem:testdb";

    @Option(names = { "--user" }, description = "The database user.")
    private String user = "sa";

    @Option(names = { "--password" }, description = "The database password.")
    private String password = "";

    @Option(names = { "--locations" }, description = "The locations to scan recursively for migrations.")
    private String locations = "filesystem:src/main/resources/db/migration";

    @Override
    public int run(String... args) throws Exception {
        return new CommandLine(this).execute(args);
    }

    public static void main(String[] args) {
        Quarkus.run(DatabaseMigrationTool.class, args);
    }

    @Override
    public void run() throws Exception {
        if (migrate) {
            performMigration();
        } else if (validate) {
            validateMigration();
        }
    }

    private void performMigration() {
        try (Flyway flyway = Flyway
                .configure()
                .dataSource(url, user, password)
                .locations(locations)
                .load()) {
            flyway.migrate();
            LOGGER.info("Database migration completed successfully.");
        } catch (Exception e) {
            LOGGER.error("Database migration failed.", e);
            throw new RuntimeException("Migration failed", e);
        }
    }

    private void validateMigration() {
        try (Flyway flyway = Flyway
                .configure()
                .dataSource(url, user, password)
                .locations(locations)
                .load()) {
            List<MigrationInfo> info = flyway.info().all();
            for (MigrationInfo migrationInfo : info) {
                if (migrationInfo.getState() == MigrationState.UNAPPLIED) {
                    LOGGER.warn("Unapplied migration found: " + migrationInfo.getVersion());
                }
            }
            LOGGER.info("Database migration validation completed successfully.");
        } catch (Exception e) {
            LOGGER.error("Database migration validation failed.", e);
            throw new RuntimeException("Validation failed", e);
        }
    }

    public String[] versionProvider() {
        return new String[]{
            "Database Migration Tool", "1.0"
        };
    }
}
