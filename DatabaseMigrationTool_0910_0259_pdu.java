// 代码生成时间: 2025-09-10 02:59:53
package com.example.migrationtool;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

/**
 * DatabaseMigrationTool is a Java program that utilizes Quarkus and Liquibase to perform database migrations.
 * This tool provides a way to update the database schema through predefined Liquibase changelogs.
 */
@QuarkusMain
public class DatabaseMigrationTool implements QuarkusApplication {

    // DataSource that is configured in application.properties
    private DataSource dataSource;

    @Override
    public int run(String... args) throws LiquibaseException, SQLException {
        System.out.println("Starting database migration...");

        // Initialize Liquibase
        Liquibase liquibase = createLiquibase();

        // Perform the migration
        liquibase.update("latest");

        System.out.println("Database migration completed successfully.");
        return 0;
    }

    /**
     * Creates and configures a Liquibase instance.
     *
     * @return A configured Liquibase instance.
     * @throws LiquibaseException If there is an error configuring Liquibase.
     */
    private Liquibase createLiquibase() throws LiquibaseException {
        // Obtain a database connection from the DataSource
        try (Connection connection = dataSource.getConnection()) {
            ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor();
            return new Liquibase(
                "migration.changelog.xml", // Changelog file name
                resourceAccessor,
                new JdbcConnection(connection)
            );
        } catch (SQLException e) {
            throw new LiquibaseException("Failed to obtain database connection.", e);
        }
    }

    /**
     * Stops the database migration tool.
     */
    @Override
    public void stop() {
        // Perform any necessary cleanup here
    }

    /**
     * Main entry point for the QUARKUS application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Quarkus.run(DatabaseMigrationTool.class, args);
    }
}
