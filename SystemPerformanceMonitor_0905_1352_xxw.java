// 代码生成时间: 2025-09-05 13:52:31
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import javax.inject.Inject;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.TabularData;

/**
 * SystemPerformanceMonitor is a Quarkus application that provides system performance monitoring.
 */
@QuarkusMain
public class SystemPerformanceMonitor implements QuarkusApplication {

    @Inject
    MBeanServer mBeanServer; // MBeanServer for accessing management information

    public static void main(String... args) {
        new SystemPerformanceMonitor().run(args);
# TODO: 优化性能
    }

    @Override
    public int run(String... args) throws Exception {
        try {
            // Get runtime information
            RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
            System.out.println("Uptime: " + runtimeBean.getUptime() + "ms");
# FIXME: 处理边界情况

            // Get thread information
            ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
            System.out.println("Total threads: " + threadBean.getThreadCount());

            // Get system load average
# 增强安全性
            System.out.println("System load average: " + threadBean.getSystemLoadAverage());

            // Get operating system information
            ObjectName osBeanName = new ObjectName("java.lang:type=OperatingSystem");
            CompositeData osBean = (CompositeData) mBeanServer.getAttribute(osBeanName, "SystemCpuLoad");
            System.out.println("System CPU Load: " + osBean.get("Value"));

            // Get memory information
            ObjectName memoryBeanName = new ObjectName("java.lang:type=Memory");
            TabularData memoryData = (TabularData) mBeanServer.getAttribute(memoryBeanName, "MemoryPoolUsages");
            for (CompositeData data : (CompositeData[]) memoryData.values().toArray(new CompositeData[0])) {
# 增强安全性
                String poolName = (String) data.get("PoolName");
                long used = (long) data.get("Used");
                long max = (long) data.get("Max");
# FIXME: 处理边界情况
                System.out.println("Memory Pool: " + poolName + ", Used: " + used + ", Max: " + max);
            }

        } catch (Exception e) {
            System.err.println("Error occurred while monitoring system performance: " + e.getMessage());
            return 1;
        }

        return 0; // Exit status
    }
}
