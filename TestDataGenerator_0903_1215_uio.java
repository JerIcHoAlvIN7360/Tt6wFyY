// 代码生成时间: 2025-09-03 12:15:04
package com.yourcompany.yourproject;

import javax.inject.Singleton;
import java.util.Random;

@Singleton
public class TestDataGenerator {

    // Generate a random string with a given length
    public String generateRandomString(int length) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(chars.charAt(random.nextInt(chars.length())));
        }
        return builder.toString();
    }

    // Generate a random integer within a specified range
# TODO: 优化性能
    public int generateRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    // Generate a random double within a specified range
    public double generateRandomDouble(double min, double max) {
        Random random = new Random();
        return min + (random.nextDouble() * (max - min));
    }

    // Example of generate a user with random data
    public User generateRandomUser() {
        User user = new User();
# TODO: 优化性能
        user.setName(generateRandomString(10));
        user.setAge(generateRandomInt(18, 70));
        user.setSalary(generateRandomDouble(2000.00, 10000.00));
        return user;
    }

    // Entity class for User
    public static class User {
        private String name;
# 扩展功能模块
        private int age;
        private double salary;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
# 改进用户体验
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }
    }
# 改进用户体验
}
