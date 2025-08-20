// 代码生成时间: 2025-08-21 06:14:12
package com.example.performancemonitor;

import io.quarkus.runtime.annotations.RegisterForReflection;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.util.Optional;

public class SystemPerformanceMonitor {

    // OperatingSystemMXBean is used to get system-level information
    private final OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
    private final RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();

    public SystemPerformanceMonitor() {
        // Constructor
    }

    /**
     * Retrieves the current system CPU load.
     *
     * @return CPU load as a double value between 0.0 and 1.0
     */
    public double getCpuLoad() {
        return osBean.getSystemCpuLoad();
    }

    /**
     * Retrieves the current system memory usage.
     *
     * @return Memory usage as a percentage value
     */
    public double getMemoryUsage() {
        long totalMemory = runtimeMxBean.getMemoryMXBean().getHeapMemoryUsage().getMax();
        long usedMemory = runtimeMxBean.getMemoryMXBean().getHeapMemoryUsage().getUsed();
        return (double) usedMemory / totalMemory * 100;
    }

    /**
     * Retrieves information about the system uptime.
     *
     * @return System uptime in seconds.
     */
    public long getSystemUptime() {
        return runtimeMxBean.getUptime() / 1000; // Uptime is in milliseconds, convert to seconds.
    }

    /**
     * Main method to demonstrate the usage of the SystemPerformanceMonitor.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SystemPerformanceMonitor monitor = new SystemPerformanceMonitor();
        try {
            double cpuLoad = monitor.getCpuLoad();
            double memoryUsage = monitor.getMemoryUsage();
            long systemUptime = monitor.getSystemUptime();

            System.out.println("CPU Load: " + cpuLoad);
            System.out.println("Memory Usage: " + memoryUsage + "%");
            System.out.println("System Uptime: " + systemUptime + " seconds");
        } catch (Exception e) {
            System.err.println("Error monitoring system performance: " + e.getMessage());
        }
    }
}
