// 代码生成时间: 2025-09-23 12:43:54
package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

// DataModel服务类
public class DataModelService {

    // 实体类：User
    @Entity
    public static class User extends PanacheEntity {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotNull
        private String name;
        @NotNull
        private String email;

        public User() {
            // 默认构造函数
        }

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }

        // Getter和Setter方法
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    // 服务方法：添加用户
    public void addUser(String name, String email) {
        try {
            User newUser = new User(name, email);
            newUser.persist();
        } catch (Exception e) {
            // 错误处理
            e.printStackTrace();
            throw new RuntimeException("Failed to add user", e);
        }
    }

    // 服务方法：获取所有用户
    public List<User> getAllUsers() {
        return User.listAll();
    }

    // 服务方法：根据ID获取用户
    public User getUserById(Long id) {
        return User.findById(id);
    }

    // 服务方法：更新用户信息
    public void updateUser(Long id, String name, String email) {
        User user = getUserById(id);
        if (user != null) {
            user.name = name;
            user.email = email;
            user.update();
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // 服务方法：删除用户
    public void deleteUser(Long id) {
        User user = getUserById(id);
        if (user != null) {
            user.delete();
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
