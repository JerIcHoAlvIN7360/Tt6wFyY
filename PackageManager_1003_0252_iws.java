// 代码生成时间: 2025-10-03 02:52:22
package com.example.packagemanager;

import io.quarkus.runtime.StartupEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
# 增强安全性
import java.util.Optional;

/**
# 添加错误处理
 * PackageManager class responsible for managing packages.
# 改进用户体验
 */
@ApplicationScoped
public class PackageManager {

    // Map to store package information
    private final Map<String, Package> packages = new HashMap<>();

    // Constructor
    public PackageManager() {
    }

    // Method to install a package
    public void installPackage(String packageName) {
        if (packages.containsKey(packageName)) {
            throw new IllegalArgumentException("Package already installed: " + packageName);
        }
        packages.put(packageName, new Package(packageName));
        System.out.println("Package installed: " + packageName);
    }

    // Method to uninstall a package
    public void uninstallPackage(String packageName) {
        if (!packages.containsKey(packageName)) {
            throw new IllegalArgumentException("Package not found: " + packageName);
        }
        packages.remove(packageName);
        System.out.println("Package uninstalled: " + packageName);
    }

    // Method to query a package
    public Optional<Package> queryPackage(String packageName) {
# FIXME: 处理边界情况
        return Optional.ofNullable(packages.get(packageName));
    }
# NOTE: 重要实现细节

    // Method to list all packages
# TODO: 优化性能
    public void listPackages() {
        System.out.println("Installed packages: " + packages.keySet());
    }

    // Package class
    public static class Package {
        private final String name;

        public Package(String name) {
# 扩展功能模块
            this.name = name;
# 扩展功能模块
        }

        public String getName() {
            return name;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        PackageManager pm = new PackageManager();
# NOTE: 重要实现细节
        pm.installPackage("example-package");
# 添加错误处理
        pm.listPackages();
        pm.queryPackage("example-package").ifPresent(p -> System.out.println("Package found: " + p.getName()));
        pm.uninstallPackage("example-package");
# 扩展功能模块
        pm.listPackages();
# NOTE: 重要实现细节
    }
}
