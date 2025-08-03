// 代码生成时间: 2025-08-04 06:47:08
package com.example.network;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import javax.management.JMException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.IOException;

/**
 * Main application class for network connection status checking.
 */
@QuarkusMain
public class NetworkStatusChecker implements QuarkusApplication {

    private static final int SOCKET_TIMEOUT = 3000; // Timeout in milliseconds
    private static final int PORT = 80; // Default HTTP port
    private static final String HOST = "www.google.com"; // Default host to check

    @Override
    public int run(String... args) throws IOException, JMException {
        boolean isConnected = checkNetworkStatus();
        if (isConnected) {
            System.out.println("Network connection is active.");
        } else {
            System.out.println("Network connection is down.");
        }
        return isConnected ? 0 : 1;
    }

    /**
     * Checks the network connection status by attempting to connect to a default host and port.
     *
     * @return true if the network connection is active, false otherwise.
     */
    private boolean checkNetworkStatus() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(HOST, PORT), SOCKET_TIMEOUT);
            return true;
        } catch (IOException e) {
            System.err.println("Error checking network connection: " + e.getMessage());
            return false;
        }
    }

    public static void main(String... args) {
        NetworkStatusChecker checker = new NetworkStatusChecker();
        checker.run(args);
    }
}
