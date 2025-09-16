// 代码生成时间: 2025-09-17 06:41:47
package com.example.monitor;

import io.quarkus.runtime.annotations.RegisterForReflection;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.time.LocalDateTime;

/**
 * 系统性能监控工具
 */
public class SystemMonitorService {

    // 获取内存使用信息
    public MemoryUsage getMemoryUsage() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        return memoryMXBean.getHeapMemoryUsage();
    }

    // 获取运行时信息
    public RuntimeMXBean getRuntimeMXBean() {
        return ManagementFactory.getRuntimeMXBean();
    }

    // 获取线程信息
    public ThreadInfo[] getThreadInfo() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        return threadMXBean.dumpAllThreads(true, true);
    }

    // 打印系统信息
    public void printSystemInfo() {
        RuntimeMXBean runtimeMXBean = getRuntimeMXBean();
        MemoryUsage memoryUsage = getMemoryUsage();
        ThreadInfo[] threadInfo = getThreadInfo();

        System.out.println("Current time: " + LocalDateTime.now());
        System.out.println("VM Name: " + runtimeMXBean.getVmName());
        System.out.println("VM Version: " + runtimeMXBean.getVmVersion());
        System.out.println("VM Vendor: " + runtimeMXBean.getVmVendor());
        System.out.println("Input arguments: " + runtimeMXBean.getInputArguments());
        System.out.println("Uptime: " + runtimeMXBean.getUptime() + " ms");

        System.out.println("Heap Memory Usage: " + memoryUsage.toString());

        System.out.println("Thread Info: ");
        for (ThreadInfo info : threadInfo) {
            System.out.println("Thread ID: " + info.getThreadId() + ", State: " + info.getThreadState());
        }
    }

    // 程序入口
    public static void main(String[] args) {
        SystemMonitorService monitor = new SystemMonitorService();
        monitor.printSystemInfo();
    }
}
