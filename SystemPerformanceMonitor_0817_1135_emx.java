// 代码生成时间: 2025-08-17 11:35:34
// SystemPerformanceMonitor.java

import io.quarkus.runtime.annotations.RegisterForReflection;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统性能监控工具，用于监控系统的性能指标。
 */
@RegisterForReflection
public class SystemPerformanceMonitor {

    private OperatingSystemMXBean osBean;

    /**
     * 构造函数，初始化操作系统MXBean。
     */
    public SystemPerformanceMonitor() {
        this.osBean = ManagementFactory.getOperatingSystemMXBean();
    }

    /**
     * 获取系统性能指标的快照。
     *
     * @return 包含系统性能指标的Map对象。
     */
    public Map<String, Object> getPerformanceSnapshot() {
        Map<String, Object> snapshot = new HashMap<>();
        try {
            snapshot.put("SystemCpuLoad", osBean.getSystemCpuLoad() * 100);
            snapshot.put("ProcessCpuLoad", osBean.getProcessCpuLoad() * 100);
            snapshot.put("FreePhysicalMemorySize", osBean.getFreePhysicalMemorySize());
            snapshot.put("TotalPhysicalMemorySize\, osBean.getTotalPhysicalMemorySize());
            snapshot.put("FreeSwapSpaceSize", osBean.getFreeSwapSpaceSize());
            snapshot.put("TotalSwapSpaceSize", osBean.getTotalSwapSpaceSize());
        } catch (Exception e) {
            // 错误处理
            snapshot.put("Error", e.getMessage());
        }
        return snapshot;
    }

    /**
     * 主方法，用于测试系统性能监控工具。
     *
     * @param args 命令行参数。
     */
    public static void main(String[] args) {
        SystemPerformanceMonitor monitor = new SystemPerformanceMonitor();
        Map<String, Object> snapshot = monitor.getPerformanceSnapshot();
        snapshot.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
