// 代码生成时间: 2025-09-07 15:00:14
package com.quarkus.memory;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import javax.enterprise.context.ApplicationScoped;

/**
 * A bean for analyzing memory usage in a Quarkus application.
 */
@ApplicationScoped
public class MemoryUsageAnalysis {

    private final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    /**
     * Retrieves the current memory usage details.
     * 
     * @return A string representation of the current memory usage.
     */
    public String getCurrentMemoryUsage() {
        long heapUsedMB = memoryMXBean.getHeapMemoryUsage().getUsed() / (1024 * 1024);
        long heapMaxMB = memoryMXBean.getHeapMemoryUsage().getMax() / (1024 * 1024);
        long nonHeapUsedMB = memoryMXBean.getNonHeapMemoryUsage().getUsed() / (1024 * 1024);
        long nonHeapMaxMB = memoryMXBean.getNonHeapMemoryUsage().getMax() / (1024 * 1024);
        
        return "Heap Used: " + heapUsedMB + " MB
" +
               "Heap Max: " + heapMaxMB + " MB
" +
               "Non-Heap Used: " + nonHeapUsedMB + " MB
" +
               "Non-Heap Max: " + nonHeapMaxMB + " MB";
    }

    /**
     * Triggers a garbage collection and returns the memory usage post-GC.
     * 
     * @return A string representation of the memory usage after garbage collection.
     */
    public String garbageCollectAndCheckMemory() {
        System.gc();
        return getCurrentMemoryUsage();
    }
}
